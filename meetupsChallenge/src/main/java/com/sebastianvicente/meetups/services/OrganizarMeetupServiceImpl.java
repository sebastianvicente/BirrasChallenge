package com.sebastianvicente.meetups.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebastianvicente.meetups.constans.DescripcionRespuesta;
import com.sebastianvicente.meetups.constans.Rol;
import com.sebastianvicente.meetups.domain.Meetup;
import com.sebastianvicente.meetups.domain.Participante;
import com.sebastianvicente.meetups.repository.MeetupRepository;
import com.sebastianvicente.meetups.repository.ParticipanteRepository;
import com.sebastianvicente.meetups.repository.PersonaRepository;
import com.sebastianvicente.meetups.valueobjects.AltaMeetupInput;
import com.sebastianvicente.meetups.valueobjects.AltaResponse;
import com.sebastianvicente.meetups.valueobjects.InscripcionInput;
import com.sebastianvicente.meetups.valueobjects.InscripcionResponse;
import com.sebastianvicente.meetups.valueobjects.ListadoMeetupsResponse;
import com.sebastianvicente.meetups.valueobjects.MeetupResponse;
import com.sebastianvicente.meetups.valueobjects.ParticipanteVO;

@Service
public class OrganizarMeetupServiceImpl implements OrganizarMeetupService {

	@Autowired
	ValidationService validationService;

	@Autowired
	MeetupRepository meetupRepository;

	@Autowired
	PersonaRepository personaRepository;

	@Autowired
	ParticipanteRepository participanteRepository;

	@Override
	public AltaResponse crearMeetup(String cuilUsuario, AltaMeetupInput altaMeetupInput) {

		if (!validationService.validarRol(cuilUsuario, Rol.ROL_ADMINISTRADOR))
			return new AltaResponse(DescripcionRespuesta.ERROR_ROL);

		Optional<Meetup> opMeetup = meetupRepository.findByNombre(altaMeetupInput.getNombre());

		if (opMeetup.isPresent())
			return new AltaResponse(DescripcionRespuesta.MEETUP_EXISTENTE);

		Meetup meetup = crearMeetupEntityFromVO(cuilUsuario, altaMeetupInput);

		try {
			meetupRepository.save(meetup);
			return new AltaResponse(DescripcionRespuesta.GENERACION_MEETUP_EXITOSA);
		} catch (IllegalArgumentException e) {
			System.out.println(e);
			return new AltaResponse(DescripcionRespuesta.ERROR_GENERACION_MEETUP);
		}
	}

	@Override
	public ListadoMeetupsResponse obtenerProximasMeetups() {

		List<Meetup> meetups = meetupRepository.findFutureMeetups();

		List<MeetupResponse> listMeetupsResponse = new ArrayList<>();

		meetups.stream().forEach(meetup -> {
			MeetupResponse meetupResponse = transformMeetupToMeetupResponse(meetup);
			listMeetupsResponse.add(meetupResponse);
			
			List<Participante> participantes = participanteRepository.findByMeetup(meetup);
			
			List<ParticipanteVO> participantesVO = new ArrayList<>();
			
			participantes.stream().forEach(participante -> {
				ParticipanteVO participanteVO = transformParticipanteToParticipanteVO(participante);
				participantesVO.add(participanteVO);
			});
			
			meetupResponse.setParticipantes(participantesVO);
		});

		ListadoMeetupsResponse response = new ListadoMeetupsResponse();
		response.setMeetups(listMeetupsResponse);
		response.setRespuesta(DescripcionRespuesta.CONSULTA_EXITOSA);
		return response;
	}

	@Override
	public InscripcionResponse inscribirseEnMeetup(String cuilUsuario, InscripcionInput inscripcionInput) {

		if (!validationService.validarRol(cuilUsuario, Rol.ROL_USUARIO))
			return new InscripcionResponse(DescripcionRespuesta.ERROR_ROL);

		Optional<Meetup> opMeetup = meetupRepository.findByNombre(inscripcionInput.getNombreMeetup());

		if (!opMeetup.isPresent())
			return new InscripcionResponse(DescripcionRespuesta.MEETUP_INEXISTENTE);

		Meetup meetup = opMeetup.get();

		Participante participante = createParticipanteEntityFromVO(inscripcionInput, meetup);

		try {
			participanteRepository.save(participante);
		} catch (IllegalArgumentException e) {
			System.out.println(e);
			return new InscripcionResponse(DescripcionRespuesta.ERROR_GENERACION_INSCRIPCION);
		}

		InscripcionResponse inscripcionResponse = new InscripcionResponse();
		inscripcionResponse.setNombreMeetup(meetup.getNombre());
		inscripcionResponse.setOrganizador(meetup.getOrganizador());
		inscripcionResponse.setRespuesta(DescripcionRespuesta.INSCRIPCION_EXITOSA);

		return inscripcionResponse;
	}

	private Participante createParticipanteEntityFromVO(InscripcionInput inscripcionInput, Meetup meetup) {
		Participante participante = new Participante();
		participante.setMeetup(meetup);
		participante.setApellido(inscripcionInput.getApellidoParticipante());
		participante.setNombre(inscripcionInput.getNombreParticipante());
		return participante;
	}

	private MeetupResponse transformMeetupToMeetupResponse(Meetup meetup) {
		MeetupResponse meetupResponse = new MeetupResponse();
		meetupResponse.setDetalle(meetup.getDetalle());
		meetupResponse.setFechaRealizacion(meetup.getFechaRealizacion());
		meetupResponse.setNombre(meetup.getNombre());
		meetupResponse.setOrganizador(meetup.getOrganizador());
		return meetupResponse;
	}

	private Meetup crearMeetupEntityFromVO(String cuilUsuario, AltaMeetupInput altaMeetupInput) {
		Meetup meetup = new Meetup();
		meetup.setNombre(altaMeetupInput.getNombre());
		meetup.setFechaRealizacion(altaMeetupInput.getFechaRealizacion());
		meetup.setDetalle(altaMeetupInput.getDetalle());
		meetup.setOrganizador(obtenerNombreUsuario(cuilUsuario));
		return meetup;
	}

	private String obtenerNombreUsuario(String cuilUsuario) {
		return personaRepository.findPersonaByCuil(cuilUsuario).get().getNombre() + " "
				+ personaRepository.findPersonaByCuil(cuilUsuario).get().getApellido();
	}
	
	private ParticipanteVO transformParticipanteToParticipanteVO(Participante participante) {
		ParticipanteVO participanteVO = new ParticipanteVO();
		participanteVO.setApellido(participante.getApellido());
		participanteVO.setNombre(participante.getNombre());
		return participanteVO;
	}

}

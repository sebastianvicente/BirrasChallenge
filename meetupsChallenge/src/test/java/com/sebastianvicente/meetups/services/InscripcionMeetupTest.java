package com.sebastianvicente.meetups.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.sebastianvicente.meetups.constans.DescripcionRespuesta;
import com.sebastianvicente.meetups.constans.Rol;
import com.sebastianvicente.meetups.domain.Meetup;
import com.sebastianvicente.meetups.repository.MeetupRepository;
import com.sebastianvicente.meetups.repository.ParticipanteRepository;
import com.sebastianvicente.meetups.valueobjects.InscripcionInput;
import com.sebastianvicente.meetups.valueobjects.InscripcionResponse;

@RunWith(MockitoJUnitRunner.class)
public class InscripcionMeetupTest {

	private static final String CUIL_20379261745 = "20379261745";

	@InjectMocks
	@Spy
	OrganizarMeetupServiceImpl organizarMeetupServiceImpl;

	@Mock
	ValidationService validationService;

	@Mock
	InscripcionInput inscripcionInput;

	@Mock
	ParticipanteRepository participanteRepository;

	@Mock
	MeetupRepository meetupRepository;

	@Mock
	Meetup meetupExistente;
	
	@Mock
	Meetup meetupInexistente;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		setUpInscripcionInput();
		setUpMeetupExistente();
		meetupInexistente = null;
	}


	@Test
	public void testErrorRol() {

		when(validationService.validarRol(CUIL_20379261745, Rol.ROL_USUARIO)).thenReturn(false);

		InscripcionResponse response = organizarMeetupServiceImpl.inscribirseEnMeetup(CUIL_20379261745,
				inscripcionInput);

		assertEquals(response.getRespuesta(), DescripcionRespuesta.ERROR_ROL.getDescripcion());
	}
	
	@Test
	public void testMeetupInexistente() {

		when(validationService.validarRol(CUIL_20379261745, Rol.ROL_USUARIO)).thenReturn(true);
		
		when(meetupRepository.findByNombre(inscripcionInput.getNombreMeetup())).thenReturn(Optional.ofNullable(meetupInexistente));

		InscripcionResponse response = organizarMeetupServiceImpl.inscribirseEnMeetup(CUIL_20379261745,
				inscripcionInput);

		assertEquals(response.getRespuesta(), DescripcionRespuesta.MEETUP_INEXISTENTE.getDescripcion());
	}
	
	@Test
	public void testInscripcionMeetupExitosa() {

		when(validationService.validarRol(CUIL_20379261745, Rol.ROL_USUARIO)).thenReturn(true);
		
		when(meetupRepository.findByNombre(inscripcionInput.getNombreMeetup())).thenReturn(Optional.of(meetupExistente));

		InscripcionResponse response = organizarMeetupServiceImpl.inscribirseEnMeetup(CUIL_20379261745,
				inscripcionInput);

		assertEquals(response.getRespuesta(), DescripcionRespuesta.INSCRIPCION_EXITOSA.getDescripcion());
		assertEquals(response.getNombreMeetup(), "Nombre Meetup");
		assertEquals(response.getOrganizador(), "Organizador Meetup");
	}

	private void setUpInscripcionInput() {
		inscripcionInput = new InscripcionInput();
		inscripcionInput.setApellidoParticipante("Apellido Participante");
		inscripcionInput.setNombreMeetup("Nombre Meetup");
		inscripcionInput.setNombreParticipante("Nombre participante");
	}
	

	private void setUpMeetupExistente() {
		meetupExistente = new Meetup();
		meetupExistente.setDetalle("Detalle Meetup");
		meetupExistente.setFechaRealizacion(new Date());
		meetupExistente.setId_meetup(1L);
		meetupExistente.setNombre("Nombre Meetup");
		meetupExistente.setOrganizador("Organizador Meetup");
		
	}
}

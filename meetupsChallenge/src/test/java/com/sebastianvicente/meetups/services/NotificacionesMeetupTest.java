package com.sebastianvicente.meetups.services;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.sebastianvicente.meetups.constans.DescripcionRespuesta;
import com.sebastianvicente.meetups.domain.Meetup;
import com.sebastianvicente.meetups.domain.Participante;
import com.sebastianvicente.meetups.repository.MeetupRepository;
import com.sebastianvicente.meetups.repository.ParticipanteRepository;
import com.sebastianvicente.meetups.valueobjects.AltaMeetupInput;
import com.sebastianvicente.meetups.valueobjects.ListadoMeetupsResponse;

@RunWith(MockitoJUnitRunner.class)
public class NotificacionesMeetupTest {

	@InjectMocks
	@Spy
	OrganizarMeetupServiceImpl organizarMeetupServiceImpl;
	
	@Mock
	AltaMeetupInput altaMeetupInput;
	
	@Mock
	ParticipanteRepository participanteRepository;
	
	@Mock
	MeetupRepository meetupRepository;
	
	@Mock
	List<Meetup> meetupsDisponibles;
	
	@Mock
	List<Participante> listParticipantes;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		setUpMeetups();
		setUpParticipantes();
	}
	
	@Test
	public void testMeetupsConParticipantes() {

		when(meetupRepository.findFutureMeetups()).thenReturn(meetupsDisponibles);
		
		when(participanteRepository.findByMeetup(meetupsDisponibles.get(0))).thenReturn(listParticipantes);

		ListadoMeetupsResponse response = organizarMeetupServiceImpl.obtenerProximasMeetups();
		
		assertEquals(response.getRespuesta(), DescripcionRespuesta.CONSULTA_EXITOSA.getDescripcion());
		assertEquals(response.getMeetups().get(0).getDetalle(),  "Detalle Meetup1");
		assertEquals(response.getMeetups().get(0).getNombre(), "Nombre Meetup1");
		assertEquals(response.getMeetups().get(0).getOrganizador(), "Organizador Meetup1" );
		assertEquals(response.getMeetups().get(0).getParticipantes().get(0).getApellido(), "Apellido Participante1");
		assertEquals(response.getMeetups().get(0).getParticipantes().get(0).getNombre(), "Nombre Participante1");
		assertEquals(response.getMeetups().get(0).getParticipantes().get(1).getApellido(), "Apellido Participante2");
		assertEquals(response.getMeetups().get(0).getParticipantes().get(1).getNombre(), "Nombre Participante2");
		
		assertEquals(response.getMeetups().get(1).getDetalle(),  "Detalle Meetup2");
		assertEquals(response.getMeetups().get(1).getNombre(), "Nombre Meetup2");
		assertEquals(response.getMeetups().get(1).getOrganizador(), "Organizador Meetup2" );
		assertTrue(response.getMeetups().get(1).getParticipantes().isEmpty());
	}
	
	@Test
	public void testSinMeetupsDisponibles() {
		
		meetupsDisponibles.clear();

		when(meetupRepository.findFutureMeetups()).thenReturn(meetupsDisponibles);

		ListadoMeetupsResponse response = organizarMeetupServiceImpl.obtenerProximasMeetups();
		
		assertEquals(response.getRespuesta(), DescripcionRespuesta.CONSULTA_EXITOSA.getDescripcion());
		assertTrue(response.getMeetups().isEmpty());

	}
	
	private void setUpMeetups() {
		meetupsDisponibles = new ArrayList<Meetup>();
		Meetup meetup1 = new Meetup();
		meetup1.setDetalle("Detalle Meetup1");
		meetup1.setFechaRealizacion(new Date());
		meetup1.setId_meetup(1L);
		meetup1.setNombre("Nombre Meetup1");
		meetup1.setOrganizador("Organizador Meetup1");
		meetupsDisponibles.add(meetup1);
		
		Meetup meetup2 = new Meetup();
		meetup2.setDetalle("Detalle Meetup2");
		meetup2.setFechaRealizacion(new Date());
		meetup2.setId_meetup(1L);
		meetup2.setNombre("Nombre Meetup2");
		meetup2.setOrganizador("Organizador Meetup2");
		meetupsDisponibles.add(meetup2);
	}
	
	private void setUpParticipantes() {
		listParticipantes = new ArrayList<Participante>();
		Participante participante1 = new Participante();
		participante1.setApellido("Apellido Participante1");
		participante1.setNombre("Nombre Participante1");
		participante1.setMeetup(meetupsDisponibles.get(0));
		participante1.setIdParticipante(1L);
		listParticipantes.add(participante1);
		
		Participante participante2 = new Participante();
		participante2.setApellido("Apellido Participante2");
		participante2.setNombre("Nombre Participante2");
		participante2.setMeetup(meetupsDisponibles.get(0));
		participante2.setIdParticipante(2L);
		listParticipantes.add(participante2);
		
	}
}

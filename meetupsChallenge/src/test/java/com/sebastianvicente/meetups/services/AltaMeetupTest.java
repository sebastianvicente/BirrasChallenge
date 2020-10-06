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
import com.sebastianvicente.meetups.domain.Persona;
import com.sebastianvicente.meetups.repository.MeetupRepository;
import com.sebastianvicente.meetups.repository.PersonaRepository;
import com.sebastianvicente.meetups.valueobjects.AltaMeetupInput;
import com.sebastianvicente.meetups.valueobjects.AltaResponse;

@RunWith(MockitoJUnitRunner.class)
public class AltaMeetupTest {

	private static final String CUIL_20379261745 = "20379261745";

	@InjectMocks
	@Spy
	OrganizarMeetupServiceImpl organizarMeetupServiceImpl;

	@Mock
	ValidationService validationService;
	
	@Mock
	AltaMeetupInput altaMeetupInput;
	
	@Mock
	PersonaRepository personaRepository;
	
	@Mock
	MeetupRepository meetupRepository;
	
	@Mock
	Meetup meetupExistente;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		setUpMeetupInput();
	}

	@Test
	public void testErrorRol() {

		when(validationService.validarRol(CUIL_20379261745, Rol.ROL_ADMINISTRADOR)).thenReturn(false);

		AltaResponse response = organizarMeetupServiceImpl.crearMeetup(CUIL_20379261745, altaMeetupInput);

		assertEquals(response.getRespuesta(), DescripcionRespuesta.ERROR_ROL.getDescripcion());
	}
	
	@Test
	public void testGeneracionMeetupExitosa() {

		when(validationService.validarRol(CUIL_20379261745, Rol.ROL_ADMINISTRADOR)).thenReturn(true);
		
		when(personaRepository.findPersonaByCuil(CUIL_20379261745)).thenReturn(Optional.of(new Persona()));

		AltaResponse response = organizarMeetupServiceImpl.crearMeetup(CUIL_20379261745, altaMeetupInput);

		assertEquals(response.getRespuesta(), DescripcionRespuesta.GENERACION_MEETUP_EXITOSA.getDescripcion());
	}
	
	
	private void setUpMeetupInput() {
		altaMeetupInput = new AltaMeetupInput();
		altaMeetupInput.setDetalle("DETALLE NUEVA MEETUP");
		altaMeetupInput.setFechaRealizacion(new Date());
		altaMeetupInput.setNombre("NOMBRE NUEVA MEETUP");
	}
}

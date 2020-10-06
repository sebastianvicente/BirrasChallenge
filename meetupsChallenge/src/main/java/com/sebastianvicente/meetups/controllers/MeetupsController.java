package com.sebastianvicente.meetups.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sebastianvicente.meetups.services.ComprarBirrasService;
import com.sebastianvicente.meetups.services.ConocerTemperaturaService;
import com.sebastianvicente.meetups.services.OrganizarMeetupService;
import com.sebastianvicente.meetups.valueobjects.AltaMeetupInput;
import com.sebastianvicente.meetups.valueobjects.AltaResponse;
import com.sebastianvicente.meetups.valueobjects.CantBirrasParaMeetupsResponse;
import com.sebastianvicente.meetups.valueobjects.InscripcionInput;
import com.sebastianvicente.meetups.valueobjects.InscripcionResponse;
import com.sebastianvicente.meetups.valueobjects.ListadoMeetupsResponse;
import com.sebastianvicente.meetups.valueobjects.TemperaturaMeetupResponse;

@RestController
@RequestMapping("meetups")
public class MeetupsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MeetupsController.class);

	@Autowired
	ComprarBirrasService comprarBirrasService;

	@Autowired
	ConocerTemperaturaService conocerTemperaturaService;
	
	@Autowired
	OrganizarMeetupService organizarMeetupService;

	/*
	 * "Como admin quiero saber cuantas cajas de birras tengo que comprar para poder aprovisionar la meetup"
	 * 
	 */

	@GetMapping("/birras")
	public CantBirrasParaMeetupsResponse comprarBirras(
			@RequestParam(value = "cantidad_invitados", required = true) final int cantidadInvitados,
			@RequestParam(value = "cuil_usuario", required = true) final String cuilUsuario) {

		LOGGER.info("Meetups - Request GET birras - cantidad_invitados: {} - cuil_usuario: {}",
				cantidadInvitados, cuilUsuario);

		return comprarBirrasService.calcularCantBirrasAComprar(cantidadInvitados, cuilUsuario);
	}

	/*
	 * "Como admin y usuario quiero conocer la temperatura del dia de la meetup para saber si va a hacer calor o no"
	 * 
	 * El servicio esta preparado para que cuando un usuario consulte el mismo dia
	 * de la meetup, le devuelva la informacion.
	 */

	@GetMapping("/temperatura")
	public TemperaturaMeetupResponse obtenerTemperaturaMeetup(
			@RequestParam(value = "cuil_usuario", required = true) final String cuilUsuario) {

		LOGGER.info("Meetups - Request GET temperatura - cuil_usuario: {}", cuilUsuario);

		return conocerTemperaturaService.obtenerTemperaturaMeetup();
	}
	
	/*
	 * Como admin quiero armar meetup para poder invitar otras personas
	 */
	
	@PostMapping("/alta")
	public AltaResponse crearMeetup(@RequestParam(value = "cuil_usuario", required = true) final String cuilUsuario,
			@RequestBody @Validated AltaMeetupInput altaMeetupInput) {
		
		LOGGER.info("Meetups - Request POST alta - cuil_usuario: {} - altaMeetup: {}", cuilUsuario, altaMeetupInput);
		
		return organizarMeetupService.crearMeetup(cuilUsuario, altaMeetupInput);
	}
	
	
	/*
	 * Como usuario y como admin quiero poder recibir notificaciones para estar al tanto de las meetups
	 * 
	 */
	
	@GetMapping("/notificaciones")
	public ListadoMeetupsResponse obtenerMeetups(@RequestParam(value = "cuil_usuario", required = true) final String cuilUsuario) {
		
		LOGGER.info("Meetups - Request GET notificaciones - cuil_usuario: {}", cuilUsuario);
		
		return organizarMeetupService.obtenerProximasMeetups();
	}
	
	
	/*
	 * Como usuario quiero inscribirme en una meetup para poder asistir
	 * 
	 */
	
	@PostMapping("/inscripcion")
	public InscripcionResponse inscribirEnMeetup(@RequestParam(value = "cuil_usuario", required = true) final String cuilUsuario,
			@RequestBody @Validated InscripcionInput inscripcionInput) {
		
		LOGGER.info("Meetups - Request POST inscripcion - cuil_usuario: {} - inscripcionInput: {}", cuilUsuario, inscripcionInput);
		
		return organizarMeetupService.inscribirseEnMeetup(cuilUsuario, inscripcionInput);
	}
	
	
	

}

package com.sebastianvicente.meetups.services;

import com.sebastianvicente.meetups.valueobjects.AltaMeetupInput;
import com.sebastianvicente.meetups.valueobjects.AltaResponse;
import com.sebastianvicente.meetups.valueobjects.InscripcionInput;
import com.sebastianvicente.meetups.valueobjects.InscripcionResponse;
import com.sebastianvicente.meetups.valueobjects.ListadoMeetupsResponse;

public interface OrganizarMeetupService {

	AltaResponse crearMeetup(String cuilUsuario, AltaMeetupInput altaMeetupInput);

	ListadoMeetupsResponse obtenerProximasMeetups();

	InscripcionResponse inscribirseEnMeetup(String cuilUsuario, InscripcionInput inscripcionInput);

}

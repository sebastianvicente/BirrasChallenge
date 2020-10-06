package com.sebastianvicente.meetups.services;

import com.sebastianvicente.meetups.valueobjects.CantBirrasParaMeetupsResponse;

public interface ComprarBirrasService {

	CantBirrasParaMeetupsResponse calcularCantBirrasAComprar(int cantidadInvitados, String cuilUsuario);

	
}

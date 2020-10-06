package com.sebastianvicente.meetups.services;

import com.sebastianvicente.meetups.constans.Rol;

public interface ValidationService {

	boolean validarRol(String cuilUsuario, Rol rol);

}

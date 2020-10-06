package com.sebastianvicente.meetups.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebastianvicente.meetups.constans.Rol;
import com.sebastianvicente.meetups.domain.Persona;
import com.sebastianvicente.meetups.repository.PersonaRepository;

@Service
public class ValidationServiceImpl implements ValidationService {

	@Autowired
	PersonaRepository personaRepository;
	
	@Override
	public boolean validarRol(String cuilUsuario, Rol rol) {
		
		Optional<Persona> opPersona = personaRepository.findPersonaByCuil(cuilUsuario);
		
		Persona persona = opPersona.isPresent() ? opPersona.get() : new Persona();
		
		return rol.getDescripcion().equalsIgnoreCase(persona.getRol());
	}

}

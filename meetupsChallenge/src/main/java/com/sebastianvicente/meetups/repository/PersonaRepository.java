package com.sebastianvicente.meetups.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sebastianvicente.meetups.domain.Persona;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Long> {

	@Query("from Persona p where p.cuil=:cuilUsuario")
	Optional<Persona> findPersonaByCuil(@Param("cuilUsuario") String cuilUsuario);


}

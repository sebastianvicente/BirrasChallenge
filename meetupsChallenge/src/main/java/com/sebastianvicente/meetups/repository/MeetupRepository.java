package com.sebastianvicente.meetups.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sebastianvicente.meetups.domain.Meetup;

@Repository
public interface MeetupRepository extends CrudRepository<Meetup, Long> {

	@Query("from Meetup m where m.nombre=:nombre")
	Optional<Meetup> findByNombre(@Param("nombre")String nombre);

	@Query("from Meetup m where m.fechaRealizacion > CURDATE()")
	List<Meetup> findFutureMeetups();


}

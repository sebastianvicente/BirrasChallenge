package com.sebastianvicente.meetups.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sebastianvicente.meetups.domain.Meetup;
import com.sebastianvicente.meetups.domain.Participante;

@Repository
public interface ParticipanteRepository extends CrudRepository<Participante, Long> {

	@Query("from Participante p where p.meetup=:meetup")
	List<Participante> findByMeetup(@Param("meetup") Meetup meetup);

}

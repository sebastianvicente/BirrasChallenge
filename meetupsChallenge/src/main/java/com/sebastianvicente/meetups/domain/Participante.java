package com.sebastianvicente.meetups.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PARTICIPANTE")
public class Participante {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID_PARTICIPANTE")
	private Long idParticipante;
	
	@ManyToOne
	@JoinColumn(name = "ID_MEETUP")
	private Meetup meetup;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "APELLIDO")
	private String apellido;

	public Long getIdParticipante() {
		return idParticipante;
	}

	public void setIdParticipante(Long idParticipante) {
		this.idParticipante = idParticipante;
	}

	public Meetup getMeetup() {
		return meetup;
	}

	public void setMeetup(Meetup meetup) {
		this.meetup = meetup;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	

}

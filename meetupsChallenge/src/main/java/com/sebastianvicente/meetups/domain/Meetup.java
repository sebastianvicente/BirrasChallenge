package com.sebastianvicente.meetups.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "MEETUP")
public class Meetup {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID_MEETUP")
	private Long id_meetup;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "ORGANIZADOR")
	private String organizador;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_REALIZACION")
	private Date fechaRealizacion;
	
	@Column(name = "DETALLE")
	private String detalle;

	public Long getId_meetup() {
		return id_meetup;
	}

	public void setId_meetup(Long id_meetup) {
		this.id_meetup = id_meetup;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getOrganizador() {
		return organizador;
	}

	public void setOrganizador(String organizador) {
		this.organizador = organizador;
	}

	public Date getFechaRealizacion() {
		return fechaRealizacion;
	}

	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	
}

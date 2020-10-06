package com.sebastianvicente.meetups.valueobjects;

import java.util.Date;
import java.util.List;

public class MeetupResponse {
	
	private String nombre;
	
	private String organizador;
	
	private Date fechaRealizacion;
	
	private String detalle;
	
	private List<ParticipanteVO> participantes;

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

	public List<ParticipanteVO> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<ParticipanteVO> participantes) {
		this.participantes = participantes;
	}

	@Override
	public String toString() {
		return "MeetupResponse [" + (nombre != null ? "nombre=" + nombre + ", " : "")
				+ (organizador != null ? "organizador=" + organizador + ", " : "")
				+ (fechaRealizacion != null ? "fechaRealizacion=" + fechaRealizacion + ", " : "")
				+ (detalle != null ? "detalle=" + detalle + ", " : "")
				+ (participantes != null ? "participantes=" + participantes : "") + "]";
	}

}

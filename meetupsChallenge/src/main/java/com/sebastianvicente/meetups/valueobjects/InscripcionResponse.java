package com.sebastianvicente.meetups.valueobjects;

import com.sebastianvicente.meetups.constans.DescripcionRespuesta;

public class InscripcionResponse {
	
	private String respuesta;
	
	private String nombreMeetup;
	
	private String organizador;

	public InscripcionResponse() {
	}
	
	public InscripcionResponse(String respuesta, String nombreMeetup, String organizador) {
		this.respuesta = respuesta;
		this.nombreMeetup = nombreMeetup;
		this.organizador = organizador;
	}

	public InscripcionResponse(DescripcionRespuesta respuesta) {
		this.respuesta = respuesta.getDescripcion();
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
	public void setRespuesta(DescripcionRespuesta respuesta) {
		this.respuesta = respuesta.getDescripcion();
	}

	public String getNombreMeetup() {
		return nombreMeetup;
	}

	public void setNombreMeetup(String nombreMeetup) {
		this.nombreMeetup = nombreMeetup;
	}

	public String getOrganizador() {
		return organizador;
	}

	public void setOrganizador(String organizador) {
		this.organizador = organizador;
	}

	@Override
	public String toString() {
		return "InscripcionResponse [" + (respuesta != null ? "respuesta=" + respuesta + ", " : "")
				+ (nombreMeetup != null ? "nombreMeetup=" + nombreMeetup + ", " : "")
				+ (organizador != null ? "organizador=" + organizador : "") + "]";
	}
}

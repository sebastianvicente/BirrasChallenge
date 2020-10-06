package com.sebastianvicente.meetups.valueobjects;

public class InscripcionInput {
	
	private String nombreMeetup;
	
	private String nombreParticipante;
	
	private String apellidoParticipante;

	public String getNombreMeetup() {
		return nombreMeetup;
	}

	public void setNombreMeetup(String nombreMeetup) {
		this.nombreMeetup = nombreMeetup;
	}

	public String getNombreParticipante() {
		return nombreParticipante;
	}

	public void setNombreParticipante(String nombreParticipante) {
		this.nombreParticipante = nombreParticipante;
	}

	public String getApellidoParticipante() {
		return apellidoParticipante;
	}

	public void setApellidoParticipante(String apellidoParticipante) {
		this.apellidoParticipante = apellidoParticipante;
	}

	@Override
	public String toString() {
		return "InscripcionInput [" + (nombreMeetup != null ? "nombreMeetup=" + nombreMeetup + ", " : "")
				+ (nombreParticipante != null ? "nombreParticipante=" + nombreParticipante + ", " : "")
				+ (apellidoParticipante != null ? "apellidoParticipante=" + apellidoParticipante : "") + "]";
	}
}

package com.sebastianvicente.meetups.valueobjects;

import java.util.List;

import com.sebastianvicente.meetups.constans.DescripcionRespuesta;

public class ListadoMeetupsResponse {
	
	private String respuesta;
	
	private List<MeetupResponse> meetups;
	
	public ListadoMeetupsResponse() {
	}

	public ListadoMeetupsResponse(DescripcionRespuesta respuesta, List<MeetupResponse> meetups) {
		this.respuesta = respuesta.getDescripcion();
		this.meetups = meetups;
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

	public List<MeetupResponse> getMeetups() {
		return meetups;
	}

	public void setMeetups(List<MeetupResponse> meetups) {
		this.meetups = meetups;
	}

	@Override
	public String toString() {
		return "ListadoMeetupsResponse [" + (respuesta != null ? "respuesta=" + respuesta + ", " : "")
				+ (meetups != null ? "meetups=" + meetups : "") + "]";
	}
	
	
	
	

}

package com.sebastianvicente.meetups.valueobjects;

import com.sebastianvicente.meetups.constans.DescripcionRespuesta;

public class AltaResponse {
	
	private String respuesta;
	
	public AltaResponse() {
	}

	public AltaResponse(DescripcionRespuesta respuesta) {
		this.respuesta = respuesta.getDescripcion();
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	@Override
	public String toString() {
		return "AltaResponse [" + (respuesta != null ? "respuesta=" + respuesta : "") + "]";
	}

}

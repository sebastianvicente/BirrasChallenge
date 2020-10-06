package com.sebastianvicente.meetups.valueobjects;

import com.sebastianvicente.meetups.constans.DescripcionRespuesta;

public class TemperaturaMeetupResponse {
	
	private String respuesta;
	
	private double temperatura;
	
	private String infoClima;
	
	public TemperaturaMeetupResponse() {
	}
	
	public TemperaturaMeetupResponse(DescripcionRespuesta respuesta, double temperatura) {
		this.respuesta = respuesta.getDescripcion();
		this.temperatura = temperatura;
		if(temperatura < 20d) {
			this.infoClima = "Frio";
		} else if (temperatura >= 20d && temperatura <= 24d) {
			this.infoClima = "Templado";
		} else {
			this.infoClima = "Calor";
		}
	}

	public TemperaturaMeetupResponse(DescripcionRespuesta respuesta) {
		this.respuesta = respuesta.getDescripcion();
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}

	public String getInfoClima() {
		return infoClima;
	}

	public void setInfoClima(String infoClima) {
		this.infoClima = infoClima;
	}

	@Override
	public String toString() {
		return "TemperaturaMeetupResponse [" + (respuesta != null ? "respuesta=" + respuesta + ", " : "")
				+ "temperatura=" + temperatura + ", " + (infoClima != null ? "infoClima=" + infoClima : "") + "]";
	}
	
	

}

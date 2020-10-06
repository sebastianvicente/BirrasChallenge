package com.sebastianvicente.meetups.valueobjects;

import com.sebastianvicente.meetups.constans.DescripcionRespuesta;

public class CantBirrasParaMeetupsResponse {
	
	private String respuesta;
	
	private int invitados;
	
	private double cantBirrasQueNecesitamos;
	
	private int cantCajas;
	
	private int cantBirrasQueDeberiamosComprar;
	
	private double temperatura;
	
	public CantBirrasParaMeetupsResponse() {
	}

	public CantBirrasParaMeetupsResponse(DescripcionRespuesta respuesta) {
		this.respuesta = respuesta.getDescripcion();
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public int getInvitados() {
		return invitados;
	}

	public void setInvitados(int invitados) {
		this.invitados = invitados;
	}

	public double getCantBirrasQueNecesitamos() {
		return cantBirrasQueNecesitamos;
	}

	public void setCantBirrasQueNecesitamos(double cantBirras) {
		this.cantBirrasQueNecesitamos = cantBirras;
	}

	public int getCantCajas() {
		return cantCajas;
	}

	public void setCantCajas(int cantCajas) {
		this.cantCajas = cantCajas;
	}

	public double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}

	public int getCantBirrasQueDeberiamosComprar() {
		return cantBirrasQueDeberiamosComprar;
	}

	public void setCantBirrasQueDeberiamosComprar(int cantBirrasQueDeberiamosComprar) {
		this.cantBirrasQueDeberiamosComprar = cantBirrasQueDeberiamosComprar;
	}

	@Override
	public String toString() {
		return "CantBirrasParaMeetupsResponse [" + (respuesta != null ? "respuesta=" + respuesta + ", " : "")
				+ "invitados=" + invitados + ", cantBirrasQueNecesitamos=" + cantBirrasQueNecesitamos + ", cantCajas="
				+ cantCajas + ", cantBirrasQueDeberiamosComprar=" + cantBirrasQueDeberiamosComprar + ", temperatura="
				+ temperatura + "]";
	}

}

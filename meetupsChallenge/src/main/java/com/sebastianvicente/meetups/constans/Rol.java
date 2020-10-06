package com.sebastianvicente.meetups.constans;

public enum Rol {

	ROL_ADMINISTRADOR("administrador"),
	
	ROL_USUARIO("usuario");
	
	private String descripcion;
	
	private Rol(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
}

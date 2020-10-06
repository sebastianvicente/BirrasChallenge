package com.sebastianvicente.meetups.valueobjects;

public class Respuesta {
	
	private String codigo;
	
	private String descripcion;
	
	public Respuesta() {
	}
	
	public Respuesta(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Respuesta [" + (codigo != null ? "codigo=" + codigo + ", " : "")
				+ (descripcion != null ? "descripcion=" + descripcion : "") + "]";
	}
}

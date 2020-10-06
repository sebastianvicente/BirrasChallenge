package com.sebastianvicente.meetups.valueobjects;

import java.util.Date;

import com.sun.istack.NotNull;

public class AltaMeetupInput {
	
	@NotNull
	private String nombre;
	
	@NotNull
	private Date fechaRealizacion;
	
	private String detalle;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Date getFechaRealizacion() {
		return fechaRealizacion;
	}

	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

	@Override
	public String toString() {
		return "AltaMeetupInput [" + (nombre != null ? "nombre=" + nombre + ", " : "")
				+ (fechaRealizacion != null ? "fechaRealizacion=" + fechaRealizacion + ", " : "")
				+ (detalle != null ? "detalle=" + detalle : "") + "]";
	}
	
}

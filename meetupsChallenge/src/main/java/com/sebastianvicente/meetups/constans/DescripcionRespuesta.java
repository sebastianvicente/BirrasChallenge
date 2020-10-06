package com.sebastianvicente.meetups.constans;

public enum DescripcionRespuesta {

	CONSULTA_EXITOSA("Consulta Exitosa"),
	ERROR_ROL("Error de Rol"), 
	ERROR_CONEXION_WEATHER("Error de conexion con la API Weather"),
	MEETUP_EXISTENTE("Ya existe una meetup con dicho nombre, por favor ingrese otro"),
	ERROR_GENERACION_MEETUP("No se pudo generar la meetup correctamente"),
	GENERACION_MEETUP_EXITOSA("Se genero la meetup de forma exitosa"),
	MEETUP_INEXISTENTE("La meetup no existe"), 
	INSCRIPCION_EXITOSA("La inscripcion a la meetup se realizo de forma exitosa"),
	ERROR_GENERACION_INSCRIPCION("La inscripcion a la meetup no pudo realizarse de forma correcta");
	
	private String descripcion;
	
	private DescripcionRespuesta(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
}

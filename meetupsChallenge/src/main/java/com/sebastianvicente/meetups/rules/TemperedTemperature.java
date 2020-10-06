package com.sebastianvicente.meetups.rules;

public class TemperedTemperature implements TemperatureStrategy {

	@Override
	public double calcularCantBirras(int personas) {
		/*
		 * "Si hace entre 20 y 24 grados se toma una birra por persona
		 */
		return personas;
	}

}

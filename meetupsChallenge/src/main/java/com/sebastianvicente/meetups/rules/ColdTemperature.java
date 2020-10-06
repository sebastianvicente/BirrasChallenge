package com.sebastianvicente.meetups.rules;

public class ColdTemperature implements TemperatureStrategy {

	@Override
	public double calcularCantBirras(int personas) {
		/*
		 * "Si hace menos de 20 grados, se toma 0.75.
		 */
		return personas*0.75;
	}

}

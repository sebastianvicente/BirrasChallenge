package com.sebastianvicente.meetups.rules;

public class HotTemperature implements TemperatureStrategy {

	@Override
	public double calcularCantBirras(int personas) {
		
		/*
		 * "Y si hace mucho calor (mas de 24 grados) se toman 2 birras mas por persona,
		 * osea serian 3 en total
		 */
		
		return personas*3;
	}

}

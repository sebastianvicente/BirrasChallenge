package com.sebastianvicente.meetups.rules;

public class ContextTemperature {
	
    private TemperatureStrategy temperatureStrategy;

    public ContextTemperature(TemperatureStrategy temperatureStrategy){
        this.temperatureStrategy = temperatureStrategy;
    }

    public double executeStrategy(int personas){
        return temperatureStrategy.calcularCantBirras(personas);
    }

}

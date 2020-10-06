package com.sebastianvicente.meetups.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebastianvicente.meetups.constans.DescripcionRespuesta;
import com.sebastianvicente.meetups.constans.Rol;
import com.sebastianvicente.meetups.restclients.RestClientApiWeather;
import com.sebastianvicente.meetups.rules.ColdTemperature;
import com.sebastianvicente.meetups.rules.ContextTemperature;
import com.sebastianvicente.meetups.rules.HotTemperature;
import com.sebastianvicente.meetups.rules.TemperatureStrategy;
import com.sebastianvicente.meetups.rules.TemperedTemperature;
import com.sebastianvicente.meetups.valueobjects.CantBirrasParaMeetupsResponse;
import com.sebastianvicente.meetups.valueobjects.CurrentWeatherDataInfo;

@Service
public class ComprarBirrasServiceImpl implements ComprarBirrasService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComprarBirrasServiceImpl.class);

	@Autowired
	ValidationService validationService;
	
	@Autowired
	RestClientApiWeather restClientApiWeather;
	
	@Override
	public CantBirrasParaMeetupsResponse calcularCantBirrasAComprar(int cantidadInvitados, String cuilUsuario) {
		
		if(!validationService.validarRol(cuilUsuario, Rol.ROL_ADMINISTRADOR)) 
			return new CantBirrasParaMeetupsResponse(DescripcionRespuesta.ERROR_ROL);

		CurrentWeatherDataInfo currentWeatherDataInfoResponse = restClientApiWeather.consultarClima();
		
		if(currentWeatherDataInfoResponse == null)
			return new CantBirrasParaMeetupsResponse(DescripcionRespuesta.ERROR_CONEXION_WEATHER);
		
		TemperatureStrategy temperatureStrategy = getStrategy(currentWeatherDataInfoResponse.getMain().getTemp());
		ContextTemperature contextTemperature = new ContextTemperature(temperatureStrategy);
				
		CantBirrasParaMeetupsResponse cantBirrasParaMeetupsResponse = armarResponseCantBirrasParaMeetups(
				cantidadInvitados, currentWeatherDataInfoResponse, contextTemperature);
		
		LOGGER.info("Meetups - Response GET comprarBirras - Response {}", cantBirrasParaMeetupsResponse);
		
		return cantBirrasParaMeetupsResponse;
	}

	private CantBirrasParaMeetupsResponse armarResponseCantBirrasParaMeetups(int cantidadInvitados,
			CurrentWeatherDataInfo currentWeatherDataInfoResponse, ContextTemperature contextTemperature) {
		CantBirrasParaMeetupsResponse cantBirrasParaMeetupsResponse = new CantBirrasParaMeetupsResponse();
		cantBirrasParaMeetupsResponse.setRespuesta(DescripcionRespuesta.CONSULTA_EXITOSA.getDescripcion());
		cantBirrasParaMeetupsResponse.setInvitados(cantidadInvitados);
		cantBirrasParaMeetupsResponse.setCantBirrasQueNecesitamos(calcularCantBirras(cantidadInvitados, contextTemperature));
		cantBirrasParaMeetupsResponse.setCantBirrasQueDeberiamosComprar(calcularCantBirrasQueDeberiamosComprar(cantBirrasParaMeetupsResponse));
		cantBirrasParaMeetupsResponse.setCantCajas(calcularCantCajas(cantBirrasParaMeetupsResponse.getCantBirrasQueDeberiamosComprar()));
		cantBirrasParaMeetupsResponse.setTemperatura(currentWeatherDataInfoResponse.getMain().getTemp());
		return cantBirrasParaMeetupsResponse;
	}

	private int calcularCantBirrasQueDeberiamosComprar(CantBirrasParaMeetupsResponse cantBirrasParaMeetupsResponse) {
		return (int) Math.round(cantBirrasParaMeetupsResponse.getCantBirrasQueNecesitamos());
	}

	private double calcularCantBirras(int cantidadInvitados, ContextTemperature contextTemperature) {
		return contextTemperature.executeStrategy(cantidadInvitados);
	}

	private int calcularCantCajas(double cantBirras) {
		return (int) (cantBirras + 5) / 6;
	}
	
    private static TemperatureStrategy getStrategy(double temperatura) {
    	TemperatureStrategy temperatureStrategy;
        if (temperatura > 24d) {
        	temperatureStrategy = new HotTemperature();
        } else if (temperatura >= 20d && temperatura <= 24d) {
        	temperatureStrategy = new TemperedTemperature();
        } else {
        	temperatureStrategy = new ColdTemperature();
        }
        return temperatureStrategy;
    }

}

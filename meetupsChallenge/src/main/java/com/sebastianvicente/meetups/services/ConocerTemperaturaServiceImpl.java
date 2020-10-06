package com.sebastianvicente.meetups.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebastianvicente.meetups.constans.DescripcionRespuesta;
import com.sebastianvicente.meetups.restclients.RestClientApiWeather;
import com.sebastianvicente.meetups.valueobjects.CurrentWeatherDataInfo;
import com.sebastianvicente.meetups.valueobjects.TemperaturaMeetupResponse;

@Service
public class ConocerTemperaturaServiceImpl implements ConocerTemperaturaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConocerTemperaturaServiceImpl.class);

	@Autowired
	RestClientApiWeather restClientApiWeather;

	@Override
	public TemperaturaMeetupResponse obtenerTemperaturaMeetup() {

		CurrentWeatherDataInfo currentWeatherDataInfoResponse = restClientApiWeather.consultarClima();

		if (currentWeatherDataInfoResponse == null)
			return new TemperaturaMeetupResponse(DescripcionRespuesta.ERROR_CONEXION_WEATHER);

		TemperaturaMeetupResponse temperaturaMeetupResponse = new TemperaturaMeetupResponse(
				DescripcionRespuesta.CONSULTA_EXITOSA, currentWeatherDataInfoResponse.getMain().getTemp());

		LOGGER.info("Meetups - Response GET temperaturameetup - Response {}", temperaturaMeetupResponse);
		
		return temperaturaMeetupResponse;
	}

}

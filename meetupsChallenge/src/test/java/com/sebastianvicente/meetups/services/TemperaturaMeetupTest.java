package com.sebastianvicente.meetups.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.sebastianvicente.meetups.constans.DescripcionRespuesta;
import com.sebastianvicente.meetups.restclients.RestClientApiWeather;
import com.sebastianvicente.meetups.valueobjects.Clouds;
import com.sebastianvicente.meetups.valueobjects.Coord;
import com.sebastianvicente.meetups.valueobjects.CurrentWeatherDataInfo;
import com.sebastianvicente.meetups.valueobjects.DataWeather;
import com.sebastianvicente.meetups.valueobjects.MainData;
import com.sebastianvicente.meetups.valueobjects.Sys;
import com.sebastianvicente.meetups.valueobjects.TemperaturaMeetupResponse;
import com.sebastianvicente.meetups.valueobjects.Wind;

@RunWith(MockitoJUnitRunner.class)
public class TemperaturaMeetupTest {

	@InjectMocks
	@Spy
	ConocerTemperaturaServiceImpl conocerTemperaturaServiceImpl;

	@Mock
	RestClientApiWeather restClientApiWeather;

	@Mock
	CurrentWeatherDataInfo weatherDataInfo;

	@Mock
	MainData mainData;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		this.setupCurrentWeatherDataInfo();
	}

	@Test
	public void testErrorConexionWeather() {

		when(restClientApiWeather.consultarClima()).thenReturn(null);

		TemperaturaMeetupResponse response = conocerTemperaturaServiceImpl.obtenerTemperaturaMeetup();

		assertEquals(response.getRespuesta(), DescripcionRespuesta.ERROR_CONEXION_WEATHER.getDescripcion());
	}

	@Test
	public void testConsultarTemperaturaFria() {

		when(restClientApiWeather.consultarClima()).thenReturn(weatherDataInfo);

		TemperaturaMeetupResponse response = conocerTemperaturaServiceImpl.obtenerTemperaturaMeetup();

		assertEquals(response.getRespuesta(), DescripcionRespuesta.CONSULTA_EXITOSA.getDescripcion());
		assertEquals(response.getTemperatura(), 14.46D);
		assertEquals(response.getInfoClima(), "Frio");

	}

	@Test
	public void testConsultarTemperaturaTemplada() {

		mainData.setTemp(21.2D);
		weatherDataInfo.setMain(mainData);

		when(restClientApiWeather.consultarClima()).thenReturn(weatherDataInfo);

		TemperaturaMeetupResponse response = conocerTemperaturaServiceImpl.obtenerTemperaturaMeetup();

		assertEquals(response.getRespuesta(), DescripcionRespuesta.CONSULTA_EXITOSA.getDescripcion());
		assertEquals(response.getTemperatura(), 21.2D);
		assertEquals(response.getInfoClima(), "Templado");

	}

	@Test
	public void testConsultarTemperaturaAlta() {

		mainData.setTemp(29.2D);
		weatherDataInfo.setMain(mainData);

		when(restClientApiWeather.consultarClima()).thenReturn(weatherDataInfo);

		TemperaturaMeetupResponse response = conocerTemperaturaServiceImpl.obtenerTemperaturaMeetup();

		assertEquals(response.getRespuesta(), DescripcionRespuesta.CONSULTA_EXITOSA.getDescripcion());
		assertEquals(response.getTemperatura(), 29.2D);
		assertEquals(response.getInfoClima(), "Calor");

	}

	private void setupCurrentWeatherDataInfo() {
		weatherDataInfo = new CurrentWeatherDataInfo();
		Coord coord = new Coord();
		coord.setLat(-34.6d);
		coord.setLon(-58.45d);
		weatherDataInfo.setCoord(coord);
		List<DataWeather> listDataWeather = new ArrayList<>();
		DataWeather dataWeather = new DataWeather();
		dataWeather.setId(802L);
		dataWeather.setMain("Clouds");
		dataWeather.setDescription("nubes dispersas");
		dataWeather.setIcon("03d");
		listDataWeather.add(dataWeather);
		weatherDataInfo.setWeather(listDataWeather);
		weatherDataInfo.setBase("stations");
		mainData = new MainData();
		mainData.setTemp(14.46D);
		mainData.setFeels_like(8.92D);
		mainData.setTemp_min(14D);
		mainData.setTemp_max(15D);
		mainData.setPressure(1023L);
		mainData.setHumidity(71L);
		weatherDataInfo.setMain(mainData);
		weatherDataInfo.setVisibility(10000L);
		Wind wind = new Wind();
		wind.setDeg(100L);
		wind.setSpeed(7.7D);
		weatherDataInfo.setWind(wind);
		Clouds clouds = new Clouds();
		clouds.setAll(40L);
		weatherDataInfo.setClouds(clouds);
		weatherDataInfo.setDt(1601825811L);
		Sys sys = new Sys();
		sys.setType(1L);
		sys.setId(8224L);
		sys.setCountry("AR");
		sys.setSunrise(1601803531L);
		sys.setSunset(1601848739L);
		weatherDataInfo.setSys(sys);
		weatherDataInfo.setTimezone(-10800L);
		weatherDataInfo.setId(3433955L);
		weatherDataInfo.setName("Buenos Aires F.D.");
		weatherDataInfo.setCod(200L);
	}
}

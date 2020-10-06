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
import com.sebastianvicente.meetups.constans.Rol;
import com.sebastianvicente.meetups.restclients.RestClientApiWeather;
import com.sebastianvicente.meetups.valueobjects.CantBirrasParaMeetupsResponse;
import com.sebastianvicente.meetups.valueobjects.Clouds;
import com.sebastianvicente.meetups.valueobjects.Coord;
import com.sebastianvicente.meetups.valueobjects.CurrentWeatherDataInfo;
import com.sebastianvicente.meetups.valueobjects.DataWeather;
import com.sebastianvicente.meetups.valueobjects.MainData;
import com.sebastianvicente.meetups.valueobjects.Sys;
import com.sebastianvicente.meetups.valueobjects.Wind;

@RunWith(MockitoJUnitRunner.class)
public class ComprarBirrasTest {

	private static final int CANT_INVITADOS_UNO = 1;
	private static final int CANT_INVITADOS_NUEVE = 9;
	private static final String CUIL_20379261745 = "20379261745";

	@InjectMocks
	@Spy
	ComprarBirrasServiceImpl comprarBirrasServiceImpl;

	@Mock
	ValidationService validationService;

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
	public void testErrorRol() {

		when(validationService.validarRol(CUIL_20379261745, Rol.ROL_ADMINISTRADOR)).thenReturn(false);

		CantBirrasParaMeetupsResponse response = comprarBirrasServiceImpl.calcularCantBirrasAComprar(CANT_INVITADOS_UNO,
				CUIL_20379261745);

		assertEquals(response.getRespuesta(), DescripcionRespuesta.ERROR_ROL.getDescripcion());
	}
	
	@Test
	public void testErrorConexionWeather() {

		when(validationService.validarRol(CUIL_20379261745, Rol.ROL_ADMINISTRADOR)).thenReturn(true);
		
		when(restClientApiWeather.consultarClima()).thenReturn(null);

		CantBirrasParaMeetupsResponse response = comprarBirrasServiceImpl.calcularCantBirrasAComprar(CANT_INVITADOS_UNO,
				CUIL_20379261745);

		assertEquals(response.getRespuesta(), DescripcionRespuesta.ERROR_CONEXION_WEATHER.getDescripcion());
	}
	
	@Test
	public void testUnInvitadoConTemperaturaFria() {

		when(validationService.validarRol(CUIL_20379261745, Rol.ROL_ADMINISTRADOR)).thenReturn(true);
		
		when(restClientApiWeather.consultarClima()).thenReturn(weatherDataInfo);

		CantBirrasParaMeetupsResponse response = comprarBirrasServiceImpl.calcularCantBirrasAComprar(CANT_INVITADOS_UNO,
				CUIL_20379261745);

		assertEquals(response.getRespuesta(), DescripcionRespuesta.CONSULTA_EXITOSA.getDescripcion());
		assertEquals(response.getInvitados(), CANT_INVITADOS_UNO);
		assertEquals(response.getCantBirrasQueNecesitamos(), Double.valueOf(0.75));
		assertEquals(response.getCantCajas(), 1);
		assertEquals(response.getCantBirrasQueDeberiamosComprar(), 1);	
	}
	
	@Test
	public void testNueveInvitadosConTemperaturaFria() {

		when(validationService.validarRol(CUIL_20379261745, Rol.ROL_ADMINISTRADOR)).thenReturn(true);
		
		when(restClientApiWeather.consultarClima()).thenReturn(weatherDataInfo);

		CantBirrasParaMeetupsResponse response = comprarBirrasServiceImpl.calcularCantBirrasAComprar(CANT_INVITADOS_NUEVE,
				CUIL_20379261745);

		assertEquals(response.getRespuesta(), DescripcionRespuesta.CONSULTA_EXITOSA.getDescripcion());
		assertEquals(response.getInvitados(), CANT_INVITADOS_NUEVE);
		assertEquals(response.getCantBirrasQueNecesitamos(), Double.valueOf(6.75));
		assertEquals(response.getCantCajas(), 2);
		assertEquals(response.getCantBirrasQueDeberiamosComprar(), 7);	
	}
	
	@Test
	public void testUnInvitadoConTemperaturaTemplada() {

		when(validationService.validarRol(CUIL_20379261745, Rol.ROL_ADMINISTRADOR)).thenReturn(true);
		
		mainData.setTemp(21.2D);
		weatherDataInfo.setMain(mainData);
		
		when(restClientApiWeather.consultarClima()).thenReturn(weatherDataInfo);

		CantBirrasParaMeetupsResponse response = comprarBirrasServiceImpl.calcularCantBirrasAComprar(CANT_INVITADOS_UNO,
				CUIL_20379261745);

		assertEquals(response.getRespuesta(), DescripcionRespuesta.CONSULTA_EXITOSA.getDescripcion());
		assertEquals(response.getInvitados(), CANT_INVITADOS_UNO);
		assertEquals(response.getCantBirrasQueNecesitamos(), Double.valueOf(1));
		assertEquals(response.getCantCajas(), 1);
		assertEquals(response.getCantBirrasQueDeberiamosComprar(), 1);	
	}
	
	@Test
	public void testNueveInvitadosConTemperaturaTemplada() {

		when(validationService.validarRol(CUIL_20379261745, Rol.ROL_ADMINISTRADOR)).thenReturn(true);
		
		mainData.setTemp(21.2D);
		weatherDataInfo.setMain(mainData);
		
		when(restClientApiWeather.consultarClima()).thenReturn(weatherDataInfo);

		CantBirrasParaMeetupsResponse response = comprarBirrasServiceImpl.calcularCantBirrasAComprar(CANT_INVITADOS_NUEVE,
				CUIL_20379261745);

		assertEquals(response.getRespuesta(), DescripcionRespuesta.CONSULTA_EXITOSA.getDescripcion());
		assertEquals(response.getInvitados(), CANT_INVITADOS_NUEVE);
		assertEquals(response.getCantBirrasQueNecesitamos(), Double.valueOf(9));
		assertEquals(response.getCantCajas(), 2);
		assertEquals(response.getCantBirrasQueDeberiamosComprar(), 9);	
	}
	
	@Test
	public void testUnInvitadoConTemperaturalta() {

		when(validationService.validarRol(CUIL_20379261745, Rol.ROL_ADMINISTRADOR)).thenReturn(true);
		
		mainData.setTemp(28.3D);
		weatherDataInfo.setMain(mainData);
		
		when(restClientApiWeather.consultarClima()).thenReturn(weatherDataInfo);

		CantBirrasParaMeetupsResponse response = comprarBirrasServiceImpl.calcularCantBirrasAComprar(CANT_INVITADOS_UNO,
				CUIL_20379261745);

		assertEquals(response.getRespuesta(), DescripcionRespuesta.CONSULTA_EXITOSA.getDescripcion());
		assertEquals(response.getInvitados(), CANT_INVITADOS_UNO);
		assertEquals(response.getCantBirrasQueNecesitamos(), Double.valueOf(3));
		assertEquals(response.getCantCajas(), 1);
		assertEquals(response.getCantBirrasQueDeberiamosComprar(), 3);	
	}
	
	@Test
	public void testNueveInvitadosConTemperaturalta() {

		when(validationService.validarRol(CUIL_20379261745, Rol.ROL_ADMINISTRADOR)).thenReturn(true);
		
		mainData.setTemp(28.3D);
		weatherDataInfo.setMain(mainData);
		
		when(restClientApiWeather.consultarClima()).thenReturn(weatherDataInfo);

		CantBirrasParaMeetupsResponse response = comprarBirrasServiceImpl.calcularCantBirrasAComprar(CANT_INVITADOS_NUEVE,
				CUIL_20379261745);

		assertEquals(response.getRespuesta(), DescripcionRespuesta.CONSULTA_EXITOSA.getDescripcion());
		assertEquals(response.getInvitados(), CANT_INVITADOS_NUEVE);
		assertEquals(response.getCantBirrasQueNecesitamos(), Double.valueOf(27));
		assertEquals(response.getCantCajas(), 5);
		assertEquals(response.getCantBirrasQueDeberiamosComprar(), 27);	
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

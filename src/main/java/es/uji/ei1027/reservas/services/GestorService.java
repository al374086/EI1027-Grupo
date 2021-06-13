package es.uji.ei1027.reservas.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.reservas.dao.AreaDao;
import es.uji.ei1027.reservas.dao.MunicipalityDao;
import es.uji.ei1027.reservas.dao.ReserveDao;
import es.uji.ei1027.reservas.dao.ZonasReservadasDao;
import es.uji.ei1027.reservas.modelo.Municipality;

@Repository
public class GestorService {
	
	@Autowired
	MunicipalityDao municipality;
	
	@Autowired
	ReserveDao reservas;
	
	@Autowired
	ZonasReservadasDao zonasreservadas;
	
	@Autowired
	AreaDao areaDao;
	
	public List<String> getProvincias() {
		List<String> provincias = new ArrayList<String>();
		List<Municipality> municipios = municipality.getMunicipality();
		for (Municipality municipio : municipios) {
			if (!provincias.contains(municipio.getProvince()))
			provincias.add(municipio.getProvince()); //De momento dejo PAIS como provincia..
		}
		return provincias;
	}
	
	public List<String> getLocalidades(String provincia) {
		List<String> localidades = new ArrayList<String>();
		List<Municipality> municipios = municipality.getMunicipality();
		for (Municipality municipio : municipios) {
			if (municipio.getProvince().equals(provincia))
				localidades.add(municipio.getName());
		}
		return localidades;
	}
	
	public List<String> getNivelOcupacion(String localidad, LocalDate date) {
		List<String> ocupacion = new ArrayList<String>();
		
		return ocupacion;
	}

}

package es.uji.ei1027.reservas.services;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.reservas.dao.AreaDao;
import es.uji.ei1027.reservas.dao.MunicipalityDao;
import es.uji.ei1027.reservas.modelo.Area;
import es.uji.ei1027.reservas.modelo.Municipality;

@Repository
public class PantallaReservarService {
	
	@Autowired
	MunicipalityDao municipality;
	
	@Autowired
	AreaDao area;
	
	
	public List<String> getProvincias() {
		List<String> provincias = new ArrayList<String>();
		List<Municipality> municipios = municipality.getMunicipality();
		for (Municipality municipio : municipios) {
			if (!provincias.contains(municipio.getCountry()))
			provincias.add(municipio.getCountry()); //De momento dejo PAIS como provincia..
		}
		return provincias;
	}
	
	public List<String> getLocalidades(String provincia) {
		List<String> localidades = new ArrayList<String>();
		List<Municipality> municipios = municipality.getMunicipality();
		for (Municipality municipio : municipios) {
			if (municipio.getCountry().equals(provincia))
				localidades.add(municipio.getName());
		}
		return localidades;
	}
	
	public List<Area> getAreas(String localidad) { //Proximamente, modificar funcion a un municipio solo
		List<Area> areas = new ArrayList<Area>();
		Municipality municipio = new Municipality();
		for (Municipality busqueda : municipality.getMunicipality()) {
			if (busqueda.getName().equals(localidad)) {
				municipio = busqueda;
				break;
			}
		}
		for (Area area: area.getAreas()) {
			if (area.getCodeMunicipality() == municipio.getCode())
				areas.add(area);
		}
		return areas;
	}

}

package es.uji.ei1027.reservas.services;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.reservas.dao.MunicipalityDao;
import es.uji.ei1027.reservas.modelo.Municipality;

@Repository
public class PantallaReservarService {
	
	@Autowired
	MunicipalityDao municipality;

	
	public List<String> getLocalidades() {
		List<String> localidades = new ArrayList<String>();
		List<Municipality> municipios = municipality.getMunicipality();
		for (Municipality municipio : municipios) {
			localidades.add(municipio.getName()); //De momento lo dejo asi a modo de prueba
		}
		return localidades;
	}

}

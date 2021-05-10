package es.uji.ei1027.reservas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.reservas.modelo.Municipality;

@Repository
public class PantallaReservarDao {
	
	private JdbcTemplate jdbcTemplate;
	private MunicipalityDao municipality;

	@Autowired
	public void setDataSource(DataSource dataSource) {
	   jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<String> getLocalidades() {
		List<String> localidades = new ArrayList<String>();
		List<Municipality> municipios = this.getMunicipality();
		for (Municipality municipio : municipios) {
			localidades.add(municipio.getName()); //De momento lo dejo asi a modo de prueba
		}
		return localidades;
	}
	
	public List<Municipality> getMunicipality() { //Se ha copiado y pegado de municipality, queria no copiarlo pero no funcionaba.
	     try {
	         return jdbcTemplate.query("SELECT * from municipality",
	                 new MunicipalityRowMapper());
	     } catch (EmptyResultDataAccessException e) {
	         return new ArrayList<Municipality>();
	     }
	}

}

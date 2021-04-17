package es.uji.ei1027.reservas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.reservas.modelo.Municipality;
import es.uji.ei1027.reservas.modelo.Plan;
import es.uji.ei1027.reservas.modelo.PlanAsignado;

@Repository
public class MunicipalityDao {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
	   jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/* Afegeix un municipality a la base de dades */
	public void addMunicipality(Municipality municipality) {
		jdbcTemplate.update("INSERT INTO municipality VALUES(?, ?)",
	    municipality.getName(), municipality.getCountry());
	}
	
	/* Esborra un municipality de la base de dades */
    public void deleteMunicipality(int code, String name) {
		jdbcTemplate.update("DELETE from municipality where code=? AND name=?",
		                           code, name);
	}
    
    /* Obté el municipality amb el nom donat. Torna null si no existeix. */
 	public Municipality getMunicipality(String name) {
 	     try {
 	         return jdbcTemplate.queryForObject("SELECT * from Municipality WHERE name=? ",
 	                 new MunicipalityRowMapper(), name);
 	     }
 	     catch(EmptyResultDataAccessException e) {
 	           return null;
 	     }
 	}
 	
    /* Obté tots els municipalities. Torna una llista buida si no n'hi ha cap. */
 	public List<Municipality> getMunicipality() {
 	     try {
 	         return jdbcTemplate.query("SELECT * from municipality",
 	                 new MunicipalityRowMapper());
 	     } catch (EmptyResultDataAccessException e) {
 	         return new ArrayList<Municipality>();
 	     }
 	}
}

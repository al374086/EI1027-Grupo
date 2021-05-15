package es.uji.ei1027.reservas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.reservas.modelo.Municipality;
import es.uji.ei1027.reservas.modelo.ServicesArea;


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
		//System.out.println("add municipality");
	}
	
	public void updateMunicipality(Municipality municipality) {
	       jdbcTemplate.update("UPDATE municipality set name='"+municipality.getName()+"', country='"+municipality.getCountry()+"' WHERE code="+municipality.getCode()+"");
	}
	
	/* Esborra un municipality de la base de dades */
    public void deleteMunicipality(int code) {
		jdbcTemplate.update("DELETE from municipality where code=? ",
		                           code);
	}
    
    /* Obté el municipality amb el codi donat. Torna null si no existeix. */
 	public Municipality getMunicipality(int code) {
 	     try {
 	         return jdbcTemplate.queryForObject("SELECT * from Municipality WHERE code=? ",
 	                 new MunicipalityRowMapper(), code);
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
 	/*Prueba commit*/
}

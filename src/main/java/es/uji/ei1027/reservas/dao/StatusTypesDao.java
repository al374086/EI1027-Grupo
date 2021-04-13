package es.uji.ei1027.reservas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.reservas.modelo.Reserve;
import es.uji.ei1027.reservas.modelo.StatusTypes;

@Repository
public class StatusTypesDao {

	private JdbcTemplate jdbcTemplate;

	   @Autowired
	   public void setDataSource(DataSource dataSource) {
	       jdbcTemplate = new JdbcTemplate(dataSource);
	   }

	   /* Afegeix un reserve a la base de dades */
	   public void addStatusTypes(StatusTypes statusTypes) {
	       jdbcTemplate.update("INSERT INTO statustypes VALUES(?)",
	    		   statusTypes.getStatus());
	   }

	   /* Esborra un temporalService de la base de dades */
	   public void deleteStatusTypes(String status) {
	       jdbcTemplate.update("DELETE from statustypes where status=?",
	    		   status);
	   }
	   public void deleteStatusTypes(StatusTypes status) {
	       jdbcTemplate.update("DELETE from statustypes where status=?",
	    		   status.getStatus());
	   }

	   /* Obté el Citizen amb el dni donat. Torna null si no existeix. */
	   public StatusTypes getReserve (String status) {
	       try {
	           return jdbcTemplate.queryForObject("SELECT * from statustypes WHERE status=?",
	                   new StatusTypesRowMapper(), status);
	       }
	       catch(EmptyResultDataAccessException e) {
	           return null;
	       }
	   }

	   /* Obté tots els plans. Torna una llista buida si no n'hi ha cap. */
	   public List<StatusTypes> getStatusTypes() {
	       try {
	           return jdbcTemplate.query("SELECT * from statustypes",
	                   new StatusTypesRowMapper());
	       } catch (EmptyResultDataAccessException e) {
	           return new ArrayList<StatusTypes>();
	       }
	   }
	   
	   
	   @SuppressWarnings("deprecation")
	public List<StatusTypes> getStatusTypes(String status) {
		   try {
		     return this.jdbcTemplate.query(
		           "SELECT * FROM statustypes WHERE status=?",
		           new Object[] {status}, new StatusTypesRowMapper());
		  }
		  catch(EmptyResultDataAccessException e) {
		      return new ArrayList<StatusTypes>();
		  }
		}
	
	
}

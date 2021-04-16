package es.uji.ei1027.reservas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.reservas.modelo.Citizen;
import es.uji.ei1027.reservas.modelo.Zone;

@Repository
public class ZoneDao {
	
	private JdbcTemplate jdbcTemplate;

	   @Autowired
	   public void setDataSource(DataSource dataSource) {
	       jdbcTemplate = new JdbcTemplate(dataSource);
	   }

	   /* Afegeix un temporalService a la base de dades */
	   public void addZone(Zone zone) {
	       jdbcTemplate.update("INSERT INTO zone VALUES(?, ?, ?)",
	    		   zone.getCapacity(), zone.getLetterAndNumber(), zone.getNameArea());
	   }

	   /* Esborra un temporalService de la base de dades */
	   public void deleteZone(String letterAndNumber, String nameArea) {
	       jdbcTemplate.update("DELETE from zone where letterAndNumber=? AND nameArea=?",
	    		   letterAndNumber, nameArea);
	   }
	   public void deleteZone(Zone zone) {
	       jdbcTemplate.update("DELETE from zone where letterAndNumber=? AND nameArea=?",
	    		   zone.getLetterAndNumber(),zone.getNameArea());
	   }

	   /* Actualitza els atributs del citizen
	      (excepte el dni  que és la clau primària) */
	   public void updateZone(Zone zone) {
	       jdbcTemplate.update("UPDATE zone SET capacity=? where letterAndNumber=? AND nameArea=?",
	    		   zone.getCapacity(), zone.getLetterAndNumber(), zone.getNameArea());
	   }

	   /* Obté el Citizen amb el dni donat. Torna null si no existeix. */
	   public Zone getZone (String letterAndNumber, String nameArea) {
	       try {
	           return jdbcTemplate.queryForObject("SELECT * from zone WHERE letterAndNumber=? AND nameArea=?",
	                   new ZoneRowMapper(), letterAndNumber, nameArea);
	       }
	       catch(EmptyResultDataAccessException e) {
	           return null;
	       }
	   }

	   /* Obté tots els plans. Torna una llista buida si no n'hi ha cap. */
	   public List<Zone> getZones() {
	       try {
	           return jdbcTemplate.query("SELECT * from zone",
	                   new ZoneRowMapper());
	       } catch (EmptyResultDataAccessException e) {
	           return new ArrayList<Zone>();
	       }
	   }
	   
	   
	/*   public List<Zone> getCitizens(String letterAndNumber, String nameArea) {
		   try {
		     return this.jdbcTemplate.query(
		           "SELECT * FROM zone WHERE letterAndNumber=? AND nameArea=?",
		           new Object[] {letterAndNumber,nameArea}, new ZoneRowMapper());
		  }
		  catch(EmptyResultDataAccessException e) {
		      return new ArrayList<Zone>();
		  }
		} */

}

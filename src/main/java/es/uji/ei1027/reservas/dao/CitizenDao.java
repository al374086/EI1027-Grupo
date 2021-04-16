package es.uji.ei1027.reservas.dao;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.reservas.modelo.Citizen;
import es.uji.ei1027.reservas.modelo.TemporalService;

@Repository
public class CitizenDao {
	
	
   private JdbcTemplate jdbcTemplate;

   @Autowired
   public void setDataSource(DataSource dataSource) {
       jdbcTemplate = new JdbcTemplate(dataSource);
   }

   /* Afegeix un temporalService a la base de dades */
   public void addCitizen(Citizen citizen) {
       jdbcTemplate.update("INSERT INTO citizen VALUES(?, ?, ?, ?, ?, ?, ?)",
    		   citizen.getDni(), citizen.getName(), citizen.getAddress(), citizen.getTown(), citizen.getCountry(),
    		   citizen.getCp(), citizen.getPin());
   }

   /* Esborra un temporalService de la base de dades */
   public void deleteCitizen(int dni) {
       jdbcTemplate.update("DELETE from citizen where dni=?",
    		   dni);
   }
   public void deleteCitizen(Citizen citizen) {
       jdbcTemplate.update("DELETE from citizen where dni=?",
    		   citizen.getDni());
   }

   /* Actualitza els atributs del citizen
      (excepte el dni  que és la clau primària) */
   public void updateCitizen(Citizen citizen) {
       jdbcTemplate.update("UPDATE citizen SET name=?, address=?, town=?, country=?, cp=?, pin=? where dni=?",
    		   citizen.getName(), citizen.getAddress(), citizen.getTown(), citizen.getCountry(), citizen.getCp(),
    		   citizen.getPin(), citizen.getDni());
   }

   /* Obté el Citizen amb el dni donat. Torna null si no existeix. */
   public Citizen getCiziten (int dni) {
       try {
           return jdbcTemplate.queryForObject("SELECT * from citizen WHERE dni=?",
                   new CitizenRowMapper(), dni);
       }
       catch(EmptyResultDataAccessException e) {
           return null;
       }
   }

   /* Obté tots els plans. Torna una llista buida si no n'hi ha cap. */
   public List<Citizen> getCitizens() {
       try {
           return jdbcTemplate.query("SELECT * from citizen",
                   new CitizenRowMapper());
       } catch (EmptyResultDataAccessException e) {
           return new ArrayList<Citizen>();
       }
   }
   
   
   public List<Citizen> getCitizens(String dni) {
	   try {
	     return this.jdbcTemplate.query(
	           "SELECT * FROM citizen WHERE dni=?",
	           new Object[] {dni}, new CitizenRowMapper());
	  }
	  catch(EmptyResultDataAccessException e) {
	      return new ArrayList<Citizen>();
	  }
	}

}

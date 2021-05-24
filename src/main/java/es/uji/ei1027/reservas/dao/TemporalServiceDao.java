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

import es.uji.ei1027.reservas.modelo.TemporalService;

@Repository
public class TemporalServiceDao {

	
   private JdbcTemplate jdbcTemplate;

   @Autowired
   public void setDataSource(DataSource dataSource) {
       jdbcTemplate = new JdbcTemplate(dataSource);
   }

   /* Afegeix un temporalService a la base de dades */
   public void addTemporalService(TemporalService temporalService) {
       jdbcTemplate.update("INSERT INTO temporalservice VALUES(?, ?, ?, ?, ?, ?)",
    		   temporalService.getInitialdate(), temporalService.getEnddate(), temporalService.getStarttime(), temporalService.getEndtime(),
    		   temporalService.getName_area(), temporalService.getIdservice());
   }

   /* Esborra un temporalService de la base de dades */
   public void deleteTemporalService(int idtemporalservice) {
       jdbcTemplate.update("DELETE from temporalservice where idtemporalservice=?",
    		   idtemporalservice);
   }
   public void deleteTemporalservice(TemporalService temporalservice) {
       jdbcTemplate.update("DELETE from temporalservice where idtemporalservice=?",
    		   temporalservice.getIdtemporalservice());
   }

   /* Actualitza els atributs del temporalservice.getStarttime()
      (excepte el idtemporalservice  que és la clau primària) */
   public void updateTemporalservice(TemporalService temporalservice) {
       jdbcTemplate.update("UPDATE temporalservice SET initialdate=?, enddate=?, starttime=?, endtime=?, name_area=?, idservice=? where idtemporalservice=?",
    		   temporalservice.getInitialdate(), temporalservice.getEnddate(), temporalservice.getStarttime(), temporalservice.getEndtime(),
    		   temporalservice.getName_area(), temporalservice.getIdservice(), temporalservice.getIdtemporalservice());
   }

   /* Obté el TemporalService amb el id donat. Torna null si no existeix. */
   public TemporalService getTemporalservice (int idtemporalservice) {
       try {
           return jdbcTemplate.queryForObject("SELECT * from temporalservice WHERE idtemporalservice=?",
                   new TemporalServiceRowMapper(), idtemporalservice);
       }
       catch(EmptyResultDataAccessException e) {
           return null;
       }
   }

   /* Obté tots els plans. Torna una llista buida si no n'hi ha cap. */
   public List<TemporalService> getTemporalServices() {
       try {
           return jdbcTemplate.query("SELECT * from temporalservice",
                   new TemporalServiceRowMapper());
       } catch (EmptyResultDataAccessException e) {
           return new ArrayList<TemporalService>();
       }
   }
   
   
   public List<TemporalService> getTemporalServices(String name_area) {
	   try {
	     return this.jdbcTemplate.query(
	           "SELECT * FROM temporalservice WHERE name_area=?",
	           new Object[] {name_area}, new TemporalServiceRowMapper());
	  }
	  catch(EmptyResultDataAccessException e) {
	      return new ArrayList<TemporalService>();
	  }
	}

}

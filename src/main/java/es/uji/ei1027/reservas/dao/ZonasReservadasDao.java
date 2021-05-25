package es.uji.ei1027.reservas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.reservas.modelo.ZonasReservadas;
import es.uji.ei1027.reservas.modelo.Zone;

@Repository
public class ZonasReservadasDao {
	
	private JdbcTemplate jdbcTemplate;

	   @Autowired
	   public void setDataSource(DataSource dataSource) {
	       jdbcTemplate = new JdbcTemplate(dataSource);
	   }

	   /* Afegeix un temporalService a la base de dades */
	   public void addZonaReservadas(ZonasReservadas zone) {
	       jdbcTemplate.update("INSERT INTO zonasReservadas VALUES(?, ?, ?)",
	    		   zone.getNumberofreserve(), zone.getLetterAndNumber(), zone.getNameArea());
	   }

	   /* Esborra un temporalService de la base de dades */
	   public void deleteZonaReservadas(int numberofreserve,String letterAndNumber, String nameArea) {
	       jdbcTemplate.update("DELETE from zonasreservadas where numberofreserve=? letterAndNumber=? AND nameArea=?",
	    		   numberofreserve,letterAndNumber, nameArea);
	   }
	   public void deleteZonaReservadas(ZonasReservadas zone) {
	       jdbcTemplate.update("DELETE from zonasreservadas where numberofreserve=? letterAndNumber=? AND nameArea=?",
	    		   zone.getNumberofreserve(),zone.getLetterAndNumber(),zone.getNameArea());
	   }

	   /* Obté tots els plans. Torna una llista buida si no n'hi ha cap. */
	   public List<ZonasReservadas> getZonasReservadas() {
	       try {
	           return jdbcTemplate.query("SELECT * from zonasreservadas",
	                   new ZonasReservadasRowMapper());
	       } catch (EmptyResultDataAccessException e) {
	           return new ArrayList<ZonasReservadas>();
	       }
	   }


}

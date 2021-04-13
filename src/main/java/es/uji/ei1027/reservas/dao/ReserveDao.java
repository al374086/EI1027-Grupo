package es.uji.ei1027.reservas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.reservas.modelo.Reserve;

@Repository
public class ReserveDao {
	
	private JdbcTemplate jdbcTemplate;

	   @Autowired
	   public void setDataSource(DataSource dataSource) {
	       jdbcTemplate = new JdbcTemplate(dataSource);
	   }

	   /* Afegeix un reserve a la base de dades */
	   public void addReserve(Reserve reserve) {
	       jdbcTemplate.update("INSERT INTO reserve VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
	    		   reserve.getNumberOfReserve(), reserve.getDateOfReservation(), reserve.getDateOfTheReserve(), reserve.getStatus(), reserve.getNumberOfPeople(),
	    		   reserve.getQrCode(), reserve.getDni(), reserve.getTimeID());
	   }

	   /* Esborra un temporalService de la base de dades */
	   public void deleteReserve(int numberOfReserve) {
	       jdbcTemplate.update("DELETE from reserve where numberOfReserve=?",
	    		   numberOfReserve);
	   }
	   public void deleteReserve(Reserve reserve) {
	       jdbcTemplate.update("DELETE from reserve where numberOfReserve=?",
	    		   reserve.getNumberOfReserve());
	   }

	   /* Actualitza els atributs del citizen
	      (excepte el dni  que és la clau primària) */
	   public void updateReserve(Reserve reserve) {
	       jdbcTemplate.update("UPDATE reserve SET dateOfReservation=?, dateOfTheReserve=?, status=?, numberOfPeople=?, qrCode=?, dni=?, timeID=? where numberOfReserve=?",
	    		   reserve.getDateOfReservation(), reserve.getDateOfTheReserve(), reserve.getStatus(), reserve.getNumberOfPeople(), reserve.getQrCode(),
	    		   reserve.getDni(), reserve.getTimeID(), reserve.getNumberOfReserve());
	   }

	   /* Obté el Citizen amb el dni donat. Torna null si no existeix. */
	   public Reserve getReserve (int nuberOfReserve) {
	       try {
	           return jdbcTemplate.queryForObject("SELECT * from reserve WHERE numberOfReserve=?",
	                   new ReserveRowMapper(), nuberOfReserve);
	       }
	       catch(EmptyResultDataAccessException e) {
	           return null;
	       }
	   }

	   /* Obté tots els plans. Torna una llista buida si no n'hi ha cap. */
	   public List<Reserve> getReserves() {
	       try {
	           return jdbcTemplate.query("SELECT * from reserve",
	                   new ReserveRowMapper());
	       } catch (EmptyResultDataAccessException e) {
	           return new ArrayList<Reserve>();
	       }
	   }
	   
	   
	   @SuppressWarnings("deprecation")
	public List<Reserve> getReserves(int numberOfReserve) {
		   try {
		     return this.jdbcTemplate.query(
		           "SELECT * FROM reserve WHERE numberOfReserve=?",
		           new Object[] {numberOfReserve}, new ReserveRowMapper());
		  }
		  catch(EmptyResultDataAccessException e) {
		      return new ArrayList<Reserve>();
		  }
		}


}

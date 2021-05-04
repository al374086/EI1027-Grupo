package es.uji.ei1027.reservas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei1027.reservas.modelo.Reserve;


public class ReserveRowMapper implements RowMapper<Reserve> {
	
	public Reserve mapRow(ResultSet rs, int rowNum) throws SQLException    
	  {
		
		
		Reserve reserve = new Reserve();
		reserve.setNumberOfReserve(rs.getInt("numberOfReserve"));
		reserve.setDateOfReservation(rs.getObject("dateOfReservation",LocalDate.class));
		reserve.setDateOfTheReserve(rs.getObject("dateOfTheReserve",LocalDate.class));
		reserve.setStatus(rs.getString("status"));
		reserve.setNumberOfPeople(rs.getInt("numberOfPeople"));
		reserve.setQrCode(rs.getString("qrCode"));
		reserve.setDni(rs.getString("dni"));
		reserve.setTimeID(rs.getInt("timeID"));
	      
	      	       
	      return reserve;
	  }

}

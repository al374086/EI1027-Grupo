package es.uji.ei1027.reservas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei1027.reservas.modelo.ZonasReservadas;

public class ZonasReservadasRowMapper implements RowMapper<ZonasReservadas> {
	
	public ZonasReservadas mapRow(ResultSet rs, int rowNum) throws SQLException    
	  {
		ZonasReservadas zonasReservadas = new ZonasReservadas();
		zonasReservadas.setNumberofreserve(rs.getInt("numberofreserve"));
		zonasReservadas.setLetterAndNumber(rs.getString("letterAndNumber"));
		zonasReservadas.setNameArea(rs.getString("namearea"));
	      
	    return zonasReservadas;
	
	}
}

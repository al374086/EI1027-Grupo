package es.uji.ei1027.reservas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei1027.reservas.modelo.Zone;

public class ZoneRowMapper implements RowMapper<Zone> {
	
	public Zone mapRow(ResultSet rs, int rowNum) throws SQLException    
	  {
		Zone zone = new Zone();
		zone.setCapacity(rs.getInt("capacity"));
		zone.setLetterAndNumber(rs.getString("letterAndNumber"));
		zone.setNameArea(rs.getString("nameArea"));
	      
	      	       
	      return zone;
	  }

}

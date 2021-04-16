package es.uji.ei1027.reservas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei1027.reservas.modelo.Reserve;
import es.uji.ei1027.reservas.modelo.StatusTypes;

public class StatusTypesRowMapper implements RowMapper<StatusTypes> {
	
	public StatusTypes mapRow(ResultSet rs, int rowNum) throws SQLException    
	  {
	
		StatusTypes statusTypes = new StatusTypes();
		statusTypes.setStatus(rs.getString("status"));
		
		return statusTypes;
	
	  }

}

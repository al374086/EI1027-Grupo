package es.uji.ei1027.reservas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei1027.reservas.modelo.Service;

public final class ServiceRowMapper implements RowMapper<Service> {

	public Service mapRow(ResultSet rs, int rowNum) throws SQLException    
	  {
		
		
	      Service service = new Service();
	      service.setIdservice(rs.getInt("idservice"));
	      service.setName(rs.getString("name"));
	      service.setType(rs.getString("type"));
	      service.setDescription(rs.getString("description"));
	      
	      
	      	       
	      return service;
	  }

}
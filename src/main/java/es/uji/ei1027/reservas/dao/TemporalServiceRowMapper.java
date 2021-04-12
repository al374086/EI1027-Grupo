package es.uji.ei1027.reservas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei1027.reservas.modelo.TemporalService;

public class TemporalServiceRowMapper implements RowMapper<TemporalService> {

	public TemporalService mapRow(ResultSet rs, int rowNum) throws SQLException    
	  {
		
		
		TemporalService temporalservice = new TemporalService();
		temporalservice.setIdtemporalservice(rs.getInt("idtemporalservice"));
		temporalservice.setInitialdate(rs.getDate("initialdate"));
		temporalservice.setEnddate(rs.getDate("enddate"));
		temporalservice.setStarttime(rs.getTime("starttime"));
		temporalservice.setEndtime(rs.getTime("endtime"));
		temporalservice.setName_area(rs.getString("name_area"));
		temporalservice.setIdservice(rs.getInt("idservice"));
	      
	      	       
	      return temporalservice;
	  }
}
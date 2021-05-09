package es.uji.ei1027.reservas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei1027.reservas.modelo.TemporalService;

public class TemporalServiceRowMapper implements RowMapper<TemporalService> {

	public TemporalService mapRow(ResultSet rs, int rowNum) throws SQLException    
	  {
		
		
		TemporalService temporalservice = new TemporalService();
		temporalservice.setIdtemporalservice(rs.getInt("idtemporalservice"));
		
		temporalservice.setInitialdate(rs.getObject("initialdate",LocalDate.class));
		temporalservice.setEnddate(rs.getObject("enddate",LocalDate.class));
		
		Time t = rs.getTime("starttime");
		temporalservice.setStarttime(t != null ? t.toLocalTime() : null); 
		
		Time tend = rs.getTime("endtime");
		temporalservice.setEndtime(tend != null ? tend.toLocalTime() : null); 
		
		temporalservice.setName_area(rs.getString("name_area"));
		temporalservice.setIdservice(rs.getInt("idservice"));
	      
	      	       
	      return temporalservice;
	  }
}
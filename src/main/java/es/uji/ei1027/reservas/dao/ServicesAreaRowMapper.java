package es.uji.ei1027.reservas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei1027.reservas.modelo.ServicesArea;


public final class ServicesAreaRowMapper implements RowMapper<ServicesArea> {

	public ServicesArea mapRow(ResultSet rs, int rowNum) throws SQLException    
	  {
	      ServicesArea servicesarea = new ServicesArea();
	      servicesarea.setIdplan(rs.getInt("idplan"));
	      servicesarea.setName_area(rs.getString("name_area"));
	      servicesarea.setComments(rs.getString("comments"));
	      servicesarea.setInitialDate(rs.getObject("initialDate",LocalDate.class));
	      servicesarea.setEndDate(rs.getObject("endDate",LocalDate.class));
	      servicesarea.setService(rs.getInt("service"));
	       
	      return servicesarea;
	  }

}


package es.uji.ei1027.clubesportiu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei1027.clubesportiu.modelo.Plan;


public final class PlanRowMapper implements RowMapper<Plan> {

	public Plan mapRow(ResultSet rs, int rowNum) throws SQLException    
	  {
	      Plan plan = new Plan();
	      plan.setIdplan(rs.getInt("idplan"));
	      plan.setName_area(rs.getString("name_area"));
	      plan.setComments(rs.getString("comments"));
	      plan.setInitialDate(rs.getDate("initialDate"));
	      plan.setEndDate(rs.getDate("endDate"));
	      
	       
	      return plan;
	  }

}


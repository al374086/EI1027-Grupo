package es.uji.ei1027.clubesportiu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;


import es.uji.ei1027.clubesportiu.modelo.PlanAsignado;


public final class PlanAsignadoRowMapper implements RowMapper<PlanAsignado> {

	public PlanAsignado mapRow(ResultSet rs, int rowNum) throws SQLException    
	  {
	      PlanAsignado planAsignado = new PlanAsignado();
	      planAsignado.setIdplan(rs.getInt("idplan"));
	      planAsignado.setIdservicio(rs.getInt("idservicio"));
	    
	      return planAsignado;
	  }

}
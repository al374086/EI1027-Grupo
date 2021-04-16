package es.uji.ei1027.reservas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei1027.reservas.modelo.Citizen;

public class CitizenRowMapper implements RowMapper<Citizen> {
	public Citizen mapRow(ResultSet rs, int rowNum) throws SQLException    
	  {
		Citizen citizen = new Citizen();
		citizen.setDni(rs.getString("dni"));
		citizen.setName(rs.getString("initialdate"));
		citizen.setAddress(rs.getString("enddate"));
		citizen.setTown(rs.getString("starttime"));
		citizen.setCountry(rs.getString("endtime"));
		citizen.setCp(rs.getInt("name_area"));
		citizen.setPin(rs.getInt("idservice"));
	      
	      	       
	      return citizen;
	  }

}

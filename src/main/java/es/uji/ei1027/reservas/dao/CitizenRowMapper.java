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
		citizen.setName(rs.getString("name"));
		citizen.setAddress(rs.getString("address"));
		citizen.setTown(rs.getString("town"));
		citizen.setCountry(rs.getString("country"));
		citizen.setCp(rs.getInt("cp"));
		citizen.setPin(rs.getString("pin"));
	      
	      	       
	      return citizen;
	  }

}

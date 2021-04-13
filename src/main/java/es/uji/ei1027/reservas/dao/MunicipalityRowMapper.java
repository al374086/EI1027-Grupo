package es.uji.ei1027.reservas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei1027.reservas.modelo.Municipality;

public final class MunicipalityRowMapper implements RowMapper<Municipality> {
	
	public Municipality mapRow(ResultSet rs, int rowNum) throws SQLException{
		
		Municipality municipality = new Municipality();
		municipality.setCode(rs.getInt("code"));
		
		return municipality;
	}

}


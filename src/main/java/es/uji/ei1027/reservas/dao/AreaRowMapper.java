package es.uji.ei1027.reservas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei1027.reservas.modelo.Area;

public final class AreaRowMapper implements RowMapper<Area> {
	
public Area mapRow(ResultSet rs, int rowNum) throws SQLException{
		
		Area area = new Area();
		area.setName(rs.getString("name"));
		
		return area;
	}

}


package es.uji.ei1027.reservas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei1027.reservas.modelo.Area;

public final class AreaRowMapper implements RowMapper<Area> {
	
public Area mapRow(ResultSet rs, int rowNum) throws SQLException{
		
		Area area = new Area();
		area.setName(rs.getString("name"));
		area.setRestricted(rs.getBoolean("isRestricted"));
		area.setGeographicalLocation(rs.getString("geographicalLocation"));
		area.setTypeOfArea(rs.getString("typeOfArea"));
		area.setPhysicalCharacteristics(rs.getString("physicalCharacteristics"));
		area.setDescription(rs.getString("description"));
		area.setLengthAndWidth(rs.getString("lengthAndWidth"));
		area.setOrientation(rs.getString("Orientation"));
		area.setCodeMunicipality(rs.getInt("codeMunicipality"));
		area.setImagen(rs.getString("image"));
		return area;
	}

}


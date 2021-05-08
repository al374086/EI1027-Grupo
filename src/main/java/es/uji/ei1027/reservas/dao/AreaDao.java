package es.uji.ei1027.reservas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.reservas.modelo.Area;

@Repository
public class AreaDao {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
	   jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/* Afegeix un AREA a la base de dades */
	public void addArea(Area area) {
		jdbcTemplate.update("INSERT INTO area VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)",
	    area.getName(), area.isRestricted(), area.getGeographicalLocation(), area.getTypeOfArea(), area.getPhysicalCharacteristics(), area.getDescription(), area.getLengthAndWidth(), area.getOrientation(), area.getCodeMunicipality());
	}

	/* Esborra un Area de la base de dades */
    public void deleteArea(String name) {
		jdbcTemplate.update("DELETE from area where name=?", name);
	}
    
    /* Actualiza un Area en la bbdd. */
    public void updateArea(Area area) {
        jdbcTemplate.update("UPDATE area SET isrestricted=?, geographicallocation=?, typeofarea=?, physicalcharacteristics=?, description=?, lengthandwidth=?, orientation=?  where name=? ",
        		area.isRestricted(), area.getGeographicalLocation(), area.getTypeOfArea(), area.getPhysicalCharacteristics(), area.getDescription(), area.getLengthAndWidth(), area.getOrientation(), area.getName());
    }
    
    /* Obté el AREA amb el nom donat. Torna null si no existeix. */
    public Area getArea(String name) {
        try {
            return jdbcTemplate.queryForObject("SELECT * from area WHERE name=?",
                    new AreaRowMapper(), name);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    /* Obté totes les Areas. Torna una llista buida si no n'hi ha cap. */
    public List<Area> getAreas() {
        try {
            return jdbcTemplate.query("SELECT * from area",
                    new AreaRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Area>();
        }
    }
    /*Prueba commit*/
}

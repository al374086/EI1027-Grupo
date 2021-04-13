package es.uji.ei1027.reservas.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import es.uji.ei1027.reservas.modelo.Municipality;
import es.uji.ei1027.reservas.modelo.PlanAsignado;

public class MunicipalityDao {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
	   jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/* Afegeix un municipality a la base de dades */
	public void addMunicipality(Municipality municipality) {
		jdbcTemplate.update("INSERT INTO municipality VALUES(?, ?)",
	    municipality.getCode(), municipality.getName());
	}
	
	/* Esborra un planAsignado de la base de dades */
    public void deleteMunicipality(int code, String name) {
		jdbcTemplate.update("DELETE from municipality where code=? AND name=?",
		                           code, name);
	}
}

package es.uji.ei1027.reservas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import es.uji.ei1027.reservas.modelo.Plan;
import es.uji.ei1027.reservas.modelo.Service;

public class ServiceDao {



	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/* Afegeix un servei a la base de dades */
	public void addService(Service service) {
		jdbcTemplate.update("INSERT INTO plan VALUES(?, ?, ?, ?,?)", service.getIdservice(), service.getName(),
				service.getDescription(), service.getName_service(), service.getName_area());
	}

	/* Esborra un servei de la base de dades */
	public void deleteService(String idservice, String name_area) {
		jdbcTemplate.update("DELETE from plan where idservice=? AND name_area=?", idservice, name_area);
	}

	public void deletePlan(Service service) {
		jdbcTemplate.update("DELETE from plan where idservice=? AND name_area=?", service.getIdservice(), service.getName_area());
	}

	/*
	 * Actualitza els atributs del servei (excepte el idservei que és la
	 * clau primària)
	 */
	public void updateService(Service service) {
		jdbcTemplate.update("UPDATE plan SET name=?, description=?, name_service=?, name_area=?, where idservice=?",
				service.getName(), service.getDescription(), service.getName_service(), service.getName_area(), service.getIdservice());
	}

	/* Obté el Servei amb el nom donat. Torna null si no existeix. */
	public Service getService(int idservice) {
		try {
			return jdbcTemplate.queryForObject("SELECT * from service WHERE idservice=?", new ServiceRowMapper(),
					idservice);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/* Obté tots els plans. Torna una llista buida si no n'hi ha cap. */
	public List<Service> getServices() {
		try {
			return jdbcTemplate.query("SELECT * from service", new ServiceRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<Service>();
		}
	}

	public List<Service> getPlanArea(String name_area) {
		try {
			return this.jdbcTemplate.query("SELECT * FROM service WHERE name_area=?", new Object[] { name_area },
					new ServiceRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<Service>();
		}
	}

}
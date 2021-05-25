package es.uji.ei1027.reservas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.reservas.modelo.ServicesArea;
import es.uji.ei1027.reservas.modelo.Service;
@Repository
public class ServiceDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/* Afegeix un servei a la base de dades */
	public void addService(Service service) {
		
		jdbcTemplate.update("INSERT INTO service VALUES(?, ?, ?)", service.getName(), service.getType(),
				service.getDescription());
		
	}

	/* Esborra un servei de la base de dades */
	public void deleteService(int idservice) {
		jdbcTemplate.update("DELETE from service where idservice=?", idservice);
		
	}

	public void deleteService(Service service) {
		jdbcTemplate.update("DELETE from service where idservice=?", service.getIdservice());
		
	}

	/*
	 * Actualitza els atributs del servei (excepte el idservei que és la
	 * clau primària)
	 */
	public void updateService(Service service) {
		jdbcTemplate.update("UPDATE service SET name=?, type=?, description=? where idservice=?",
				service.getName(), service.getType(), service.getDescription(), service.getIdservice());
		
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

	 /* Obté tots els plans. Torna una llista buida si no n'hi ha cap. */
		public List<Service> getServices(String area) {
			try {
				return jdbcTemplate.query("SELECT * from service join servicesarea ON service.idservice=servicesarea.service WHERE servicesarea.name_area=? ", new ServiceRowMapper(), area);
			} catch (EmptyResultDataAccessException e) {
				return new ArrayList<Service>();
			}
		}
		
		/* Obté tots els plans. Torna una llista buida si no n'hi ha cap. */
		public List<Service> getServicesCombo() {
			try {
				return jdbcTemplate.query("SELECT * from service ", new ServiceRowMapper());
			} catch (EmptyResultDataAccessException e) {
				return new ArrayList<Service>();
			}
		}

}
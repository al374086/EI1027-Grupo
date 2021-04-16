package es.uji.ei1027.reservas.dao;

import es.uji.ei1027.reservas.modelo.Plan;
import es.uji.ei1027.reservas.modelo.PlanAsignado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository // En Spring els DAOs van anotats amb @Repository

public class PlanAsignadoDao {
	

	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
	   jdbcTemplate = new JdbcTemplate(dataSource);
	}

	   /* Afegeix un planAsignado a la base de dades */
	public void addPlanAsignado(PlanAsignado planAsignado) {
	   jdbcTemplate.update("INSERT INTO Plan VALUES(?, ?)",
	               planAsignado.getIdplan(), planAsignado.getIdservicio());
	}

	   /* Esborra un planAsignado de la base de dades */
	public void deletePlanAsignado(String idplan, String name_area) {
	     jdbcTemplate.update("DELETE from Plan where idplan=? AND idservicio=?",
	                           idplan, name_area);
	}
	public void deletePlanAsignado(PlanAsignado planAsignado) {
	     jdbcTemplate.update("DELETE from Plan where idplan=? AND name_area=?",
	                           planAsignado.getIdplan(), planAsignado.getIdservicio());
	}
	
	

	   /*No es pot actualitzar els atributs del planAsignado perque els 2 atributs son clau primaria
	      

	   /* Obté el PlanAsignado amb el nom donat. Torna null si no existeix. */
	public Plan getPlanAsignado(int idplan, int idservicio) {
	     try {
	         return jdbcTemplate.queryForObject("SELECT * from Plan WHERE idplan=? and idservicio=?",
	                 new PlanRowMapper(), idplan, idservicio);
	     }
	     catch(EmptyResultDataAccessException e) {
	           return null;
	     }
	}

	   /* Obté tots els plans. Torna una llista buida si no n'hi ha cap. */
	public List<PlanAsignado> getPlanesAsignados() {
	     try {
	         return jdbcTemplate.query("SELECT * from planasignado",
	                 new PlanAsignadoRowMapper());
	     } catch (EmptyResultDataAccessException e) {
	         return new ArrayList<PlanAsignado>();
	     }
	}


}

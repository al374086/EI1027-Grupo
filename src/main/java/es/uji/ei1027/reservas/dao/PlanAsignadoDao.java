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
	   jdbcTemplate.update("INSERT INTO planasignado VALUES(?, ?)",
	               planAsignado.getIdservicio(), planAsignado.getIdplan());
	}
	
	 /* Esborra un plan de la base de dades */
	   public void deletePlanAsignado(int idservicio, int idplan) {
	       jdbcTemplate.update("DELETE from planasignado where idservicio=? AND idplan=?",
	                           idservicio, idplan);
	   }


	public void deletePlanAsignado(PlanAsignado planAsignado) {
	     jdbcTemplate.update("DELETE from planasignado where idplan=?",
	                           planAsignado.getIdplan());
	}
	
	
	   /* Actualitza els atributs del planasignado
    (no es pot modificar cap atribut per que son la clau primària)
    
 public void updatePlan(PlanAsignado planasignado) {
     jdbcTemplate.update("UPDATE planasignado SET idservicio=? where idplan=? ",
    		 planasignado.getIdservicio(), planasignado.getIdplan());
   
 }
	 */
	     

	   /* Obté el PlanAsignado amb el nom donat. Torna null si no existeix. */
	public Plan getPlanAsignado(int idplan, int idservicio) {
	     try {
	         return jdbcTemplate.queryForObject("SELECT * from planasignado WHERE idplan=? and idservicio=?",
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

package es.uji.ei1027.reservas.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.reservas.modelo.Plan;

@Repository
public class PlanDao {

   private JdbcTemplate jdbcTemplate;

   @Autowired
   public void setDataSource(DataSource dataSource) {
       jdbcTemplate = new JdbcTemplate(dataSource);
   }

   /* Afegeix un plan a la base de dades */
   public void addPlan(Plan plan) {
       jdbcTemplate.update("INSERT INTO plan VALUES(?, ?, ?, ?,?)",
               plan.getIdplan(), plan.getComments(), plan.getInitialDate(), plan.getEndDate(), plan.getName_area());
   }

   /* Esborra un plan de la base de dades */
   public void deletePlan(int idplan, String name_area) {
       jdbcTemplate.update("DELETE from plan where idplan=? AND name_area=?",
                           idplan, name_area);
   }
   public void deletePlan(Plan plan) {
       jdbcTemplate.update("DELETE from plan where idplan=? AND name_area=?",
                           plan.getIdplan(), plan.getName_area());
   }

   /* Actualitza els atributs del plan
      (excepte el idplan y name_area, que és la clau primària) */
   public void updatePlan(Plan plan) {
       jdbcTemplate.update("UPDATE plan SET comments=?, initialDate=?, endDate=?, where idplan=? and name_area=?",
               plan.getComments(), plan.getInitialDate(), plan.getEndDate(), plan.getIdplan(), plan.getName_area());
   }

   /* Obté el Plan amb el nom donat. Torna null si no existeix. */
   public Plan getPlan(int idplan, String name_area) {
       try {
           return jdbcTemplate.queryForObject("SELECT * from plan WHERE idplan=? and name_area=?",
                   new PlanRowMapper(), idplan, name_area);
       }
       catch(EmptyResultDataAccessException e) {
           return null;
       }
   }

   /* Obté tots els plans. Torna una llista buida si no n'hi ha cap. */
   public List<Plan> getPlans() {
       try {
           return jdbcTemplate.query("SELECT * from plan",
                   new PlanRowMapper());
       } catch (EmptyResultDataAccessException e) {
           return new ArrayList<Plan>();
       }
   }
   
   
   public List<Plan> getPlanArea(String name_area) {
	   try {
	     return this.jdbcTemplate.query(
	           "SELECT * FROM plan WHERE name_area=?",
	           new Object[] {name_area}, new PlanRowMapper());
	  }
	  catch(EmptyResultDataAccessException e) {
	      return new ArrayList<Plan>();
	  }
	}

}


package es.uji.ei1027.reservas.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import es.uji.ei1027.reservas.modelo.ServicesArea;

@Repository
public class ServicesAreaDao {

   private JdbcTemplate jdbcTemplate;

   @Autowired
   public void setDataSource(DataSource dataSource) {
       jdbcTemplate = new JdbcTemplate(dataSource);
   }

   /* Afegeix un plan a la base de dades */
   public void addServicesArea(ServicesArea servicesarea) {
	   System.out.println(servicesarea.toString()+ "rtyyttttttttttttttttttttttttttttttttttttttttttttttttttt");
	  // jdbcTemplate.update("INSERT INTO servicesarea (comments,initialdate,enddate,name_area,service")VALUES(servicesarea.getComments(),servicesarea.getInitialDate(),servicesarea.getEndDate(), servicesarea.getName_area(), servicesarea.getService());
			   
       jdbcTemplate.update("INSERT INTO servicesarea(Comments,initialdate, enddate, name_area,service) VALUES( ?, ?, ?, ? ,?)",
    	  servicesarea.getComments(),servicesarea.getInitialDate(), servicesarea.getEndDate(), servicesarea.getName_area(), servicesarea.getService());
      
   }
  


/* Esborra un plan de la base de dades */
   public void deleteServicesArea(int idplan, String name_area) {
       jdbcTemplate.update("DELETE from servicesarea where idplan=? AND name_area=?",
                           idplan, name_area);
   }
   public void deleteServicesArea(ServicesArea servicesarea) {
       jdbcTemplate.update("DELETE from servicesarea where idplan=? AND name_area=?",
    		   servicesarea.getIdplan(), servicesarea.getName_area());
   }

   /* Actualitza els atributs del plan
      (excepte el idplan y name_area, que és la clau primària) */
   public void updateServicesArea(ServicesArea servicesarea ) {
	   System.out.println(servicesarea.toString()+ "rtyytdddddddddddddddddddddddddddddddddddddddddddddddddd");
	  jdbcTemplate.update("UPDATE servicesarea SET comments=?, initialDate=?, endDate=?, service=? where idplan=? and name_area=?",
    		   servicesarea.getComments(), servicesarea.getInitialDate(), servicesarea.getEndDate(),servicesarea.getService(), servicesarea.getIdplan(), servicesarea.getName_area());
     
   }
   
  

   /* Obté el Plan amb el nom donat. Torna null si no existeix. */
   public ServicesArea getServicesArea(int idplan, String name_area) {
	   System.out.println("rtyyteeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
       try {
           return jdbcTemplate.queryForObject("SELECT * from servicesarea WHERE idplan=? and name_area=?",
                   new ServicesAreaRowMapper(), idplan, name_area);
       }
       catch(EmptyResultDataAccessException e) {
           return null;
       }
   }

   /* Obté tots els plans. Torna una llista buida si no n'hi ha cap. */
   public List<ServicesArea> getServicesAreas() {
       try {
           return jdbcTemplate.query("SELECT * from servicesarea",
                   new ServicesAreaRowMapper());
       } catch (EmptyResultDataAccessException e) {
           return new ArrayList<ServicesArea>();
       }
   }
   
   
   public List<ServicesArea> getServicesArea(String name_area) {
	   try {
	     return this.jdbcTemplate.query(
	           "SELECT * FROM servicesarea WHERE name_area=?",
	           new Object[] {name_area}, new ServicesAreaRowMapper());
	  }
	  catch(EmptyResultDataAccessException e) {
	      return new ArrayList<ServicesArea>();
	  }
	}

}


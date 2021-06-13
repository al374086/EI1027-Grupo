package es.uji.ei1027.reservas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import es.uji.ei1027.reservas.modelo.Municipality;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.reservas.modelo.Citizen;
import es.uji.ei1027.reservas.modelo.MunicipalManager;


@Repository // En Spring els DAOs van anotats amb @Repository

public class MunicipalManagerDao{


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegeix un municipalManager a la base de dades */
    public void addMunicipalManager(MunicipalManager municipalManager) {
    	
       System.out.println("antes encriptacion" +municipalManager.getPassword());
  	   BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor(); 
  	   
  	   municipalManager.setPassword(passwordEncryptor.encryptPassword(municipalManager.getPassword())); 
  	   System.out.println("despues encriptacion" +municipalManager.getPassword());
    	System.out.println("introduciendo datos");
        jdbcTemplate.update("INSERT INTO municipalmanager VALUES(?, ?,?,?,?,?,?)",
                municipalManager.getDni(), municipalManager.getName(),municipalManager.getUser(),municipalManager.getPassword(),municipalManager.getInitialDate(),
                municipalManager.getEndDate(),municipalManager.getCode());
    }
    
    
    /* Esborra un temporalService de la base de dades */
    public void deleteMunicipalManagerDNI(String dni) {
        jdbcTemplate.update("DELETE from municipalmanager where dni=?",
                dni);
    }

    /* Esborra un MunicipalManager de la base de dades */
    public void deleteMunicipalManager(MunicipalManager municipalManager) {
        jdbcTemplate.update("DELETE from municipalmanager where dni=?",
                municipalManager.getDni());
    }

    /* Obté el MunicipalManager amb el dni donat. Torna null si no existeix. */
    public MunicipalManager getMunicipalManager(String dni) {
        try {
            return jdbcTemplate.queryForObject("SELECT * from municipalmanager WHERE dni=? ",
                    new MunicipalManagerRowMapper(), dni);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    
    /* Obté el Citizen amb el nom donat. Torna null si no existeix. */
    public MunicipalManager getMunicipalManagerNombre (String name) {
        try {
            return jdbcTemplate.queryForObject("SELECT * from municipalmanager WHERE name=?",
                    new MunicipalManagerRowMapper(), name);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    /* Actualitza els atributs del citizen
    (excepte el dni  que és la clau primària) */
    public void updateMunicipalManager(MunicipalManager municipalManager) {
    	
    	   System.out.println("antes encriptacion" +municipalManager.getPassword());
      	   BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor(); 
      	   
      	   municipalManager.setPassword(passwordEncryptor.encryptPassword(municipalManager.getPassword())); 
      	   System.out.println("despues encriptacion" +municipalManager.getPassword());
        	System.out.println("introduciendo datos");
        jdbcTemplate.update("UPDATE municipalmanager SET name=?, usuario=?, password=?, initialDate=?, endDate=?, code=? where dni=?",
        		 municipalManager.getName(),municipalManager.getUser(),municipalManager.getPassword(),municipalManager.getInitialDate(),
        		 municipalManager.getEndDate(),municipalManager.getCode(),municipalManager.getDni());
    }
    
    
    public List<MunicipalManager> getMunicipalManagers() {
        try {
            return jdbcTemplate.query("SELECT * from municipalmanager",
                    new MunicipalManagerRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<MunicipalManager>();
        }
    }






}

package es.uji.ei1027.reservas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import es.uji.ei1027.reservas.modelo.Citizen;
import es.uji.ei1027.reservas.modelo.MunicipalManager;
import es.uji.ei1027.reservas.modelo.Municipality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.reservas.modelo.Staff;

@Repository // En Spring els DAOs van anotats amb @Repository

public class StaffDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegeix un Staff a la base de dades */
    public void addStaff(Staff staff) {
        jdbcTemplate.update("INSERT INTO staff VALUES(?, ?)",
                staff.getDni(), staff.getName());
    }

    /* Esborra un Staff de la base de dades */
    public void deleteStaff(Staff staff) {
        jdbcTemplate.update("DELETE from staff where dni=?",
                staff.getDni());
    }

    /* Obté el MunicipalManager amb el dni donat. Torna null si no existeix. */
    public Staff getStaff(String dni) {
        try {
            return jdbcTemplate.queryForObject("SELECT * from staff WHERE dni=? ",
                    new StaffRowMapper(), dni);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }
    
    /* Obté el Citizen amb el nom donat. Torna null si no existeix. 
    public Staff getStaffNombre (String name) {
        try {
            return jdbcTemplate.queryForObject("SELECT * from staff WHERE name=?",
                    new StaffRowMapper(), name);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }
    */
    
}

package es.uji.ei1027.reservas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import es.uji.ei1027.reservas.modelo.Municipality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
        jdbcTemplate.update("INSERT INTO municipalManager VALUES(?, ?,?,?,?,?)",
                municipalManager.getDni(), municipalManager.getName(),municipalManager.getUser(),municipalManager.getPassword(),municipalManager.getInitialDate(),municipalManager.getCode());
    }

    /* Esborra un MunicipalManager de la base de dades */
    public void deleteMunicipalManager(MunicipalManager municipalManager) {
        jdbcTemplate.update("DELETE from municipalManager where dni=?",
                municipalManager.getDni());
    }

    /* Obté el MunicipalManager amb el dni donat. Torna null si no existeix. */
    public MunicipalManager getMunicipalManager(String dni) {
        try {
            return jdbcTemplate.queryForObject("SELECT * from municipalManager WHERE dni=? ",
                    new MunicipalManagerRowMapper(), dni);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }






}

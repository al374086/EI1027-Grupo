package es.uji.ei1027.reservas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import es.uji.ei1027.reservas.modelo.MunicipalManager;
import es.uji.ei1027.reservas.modelo.Workplan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.reservas.modelo.Workplan;


@Repository // En Spring els DAOs van anotats amb @Repository

public class WorkPlanDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegeix un workplan a la base de dades */
    public void addWorkplan(Workplan workplan) {
        jdbcTemplate.update("INSERT INTO workplan VALUES(?, ?,?,?)",
                workplan.getInitialDate(), workplan.getEndDate(),workplan.getNameArea(),workplan.getDni());
    }

    /* Esborra un workplan de la base de dades */
    public void deleteWorkplan(Workplan workplan) {
        jdbcTemplate.update("DELETE from workplan where initialDate=? AND endDate=? AND nameArea=? AND dni=?",
                workplan.getInitialDate(),workplan.getEndDate(),workplan.getNameArea(),workplan.getDni());
    }

    /* Obté tots el Worplans amb el dni donat. Torna null si no existeix. */
    public Workplan getWorkplans(String dni) {
        try {
            return jdbcTemplate.queryForObject("SELECT * from workplan WHERE dni=? ",
                    new WorkplanRowMapper(), dni);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }

    }
}

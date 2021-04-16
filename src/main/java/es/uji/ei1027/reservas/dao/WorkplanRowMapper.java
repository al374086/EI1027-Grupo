package es.uji.ei1027.reservas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;



import org.springframework.jdbc.core.RowMapper;

import es.uji.ei1027.reservas.modelo.Workplan;

public class WorkplanRowMapper implements RowMapper<Workplan>{
    @Override
    public Workplan mapRow(ResultSet rs, int rowNum) throws SQLException {
        Workplan workplan= new Workplan();
        workplan.setDni(rs.getString("dni"));
        return workplan;
    }
}

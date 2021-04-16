package es.uji.ei1027.reservas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;


import es.uji.ei1027.reservas.modelo.Staff;
import org.springframework.jdbc.core.RowMapper;
import es.uji.ei1027.reservas.modelo.Staff;

public class StaffRowMapper implements RowMapper<Staff>{
    @Override
    public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {
        Staff staff=new Staff();
        staff.setDni(rs.getString("dni"));
        return staff;
    }
}

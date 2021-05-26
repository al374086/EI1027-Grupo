package es.uji.ei1027.reservas.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei1027.reservas.modelo.MunicipalManager;

public final class MunicipalManagerRowMapper implements RowMapper<MunicipalManager>{


   
    public MunicipalManager mapRow(ResultSet rs, int rowNum) throws SQLException {
        MunicipalManager municipalmanager= new MunicipalManager();
        municipalmanager.setDni(rs.getString("dni"));
        municipalmanager.setName(rs.getString("name"));
        municipalmanager.setUser(rs.getString("usuario"));
        municipalmanager.setPassword(rs.getString("password"));
        municipalmanager.setInitialDate(rs.getObject("initialdate",LocalDate.class));
        municipalmanager.setEndDate(rs.getObject("enddate",LocalDate.class));
        municipalmanager.setCode(rs.getInt("code"));
        
        return municipalmanager;
    }



}

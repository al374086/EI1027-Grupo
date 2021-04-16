package es.uji.ei1027.reservas.dao;


import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;

import es.uji.ei1027.reservas.modelo.MunicipalManager;

public final class MunicipalManagerRowMapper implements RowMapper<MunicipalManager>{


    @Override
    public MunicipalManager mapRow(ResultSet rs, int rowNum) throws SQLException {
        MunicipalManager municipalManager= new MunicipalManager();
        municipalManager.setDni(rs.getString("dni"));
        return municipalManager;
    }



}

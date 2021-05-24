package es.uji.ei1027.reservas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei1027.reservas.modelo.TimeSlot;

public final class TimeSlotRowMapper implements RowMapper<TimeSlot> {
	
	public TimeSlot mapRow(ResultSet rs, int rowNum) throws SQLException{
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setTimeId(rs.getInt("timeId"));
		timeSlot.setStartTime(rs.getTime("starttime"));
		timeSlot.setEndTime(rs.getTime("endtime"));
		timeSlot.setNameArea(rs.getString("namearea"));
		System.out.println("dentro del row mapper");
		return timeSlot;
	}
}
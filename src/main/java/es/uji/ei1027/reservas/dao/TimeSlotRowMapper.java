package es.uji.ei1027.reservas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei1027.reservas.modelo.TimeSlot;

public final class TimeSlotRowMapper implements RowMapper<TimeSlot> {
	
	public TimeSlot mapRow(ResultSet rs, int rowNum) throws SQLException{
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setTimeId(rs.getInt("timeId"));
		timeSlot.setStartTime(rs.getObject("startTime", LocalTime.class));
		timeSlot.setEndTime(rs.getObject("endTime", LocalTime.class));
		timeSlot.setNameArea(rs.getString("namearea"));
		return timeSlot;
	}
}
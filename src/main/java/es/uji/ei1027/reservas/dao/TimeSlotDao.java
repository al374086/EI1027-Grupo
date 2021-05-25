package es.uji.ei1027.reservas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.reservas.modelo.TimeSlot;

@Repository
public class TimeSlotDao {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
	   jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/* Afegeix un TIMESLOT a la base de dades */
	public void addTimeSlot(TimeSlot timeSlot) {
		System.out.println(timeSlot.getStartTime());
		jdbcTemplate.update("INSERT INTO timeslot (timeId, startTime, endTime, namearea) VALUES(?, ?, ?, ?)",
	    timeSlot.getTimeId(), timeSlot.getStartTime(), timeSlot.getEndTime(), timeSlot.getNameArea());
	}
	
	/* Esborra un TIMESLOT de la base de dades */
    public void deleteTimeSlot(int timeId) {
		jdbcTemplate.update("DELETE from TimeSlot where timeid=?", timeId);
	}
    
    /* Actualiza un TIMESLOT en la bbdd. */
    public void updateTimeSlot(TimeSlot timeSlot) {
        jdbcTemplate.update("UPDATE timeslot SET starttime=?, endtime=? where timeid=? ",
        		timeSlot.getStartTime(), timeSlot.getEndTime(), timeSlot.getTimeId());
    }
    
    /* Obté el TIMESLOT amb el timeid donat. Torna null si no existeix. */
    public TimeSlot getTimeSlot(int timeid) {
        try {
            return jdbcTemplate.queryForObject("SELECT * from timeSlot WHERE timeid=?",
                    new TimeSlotRowMapper(), timeid);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    /* Obté tots els TIMESLOTS. Torna una llista buida si no n'hi ha cap. */
    public List<TimeSlot> getTimeSlots() {
        try {
        	//System.out.println("dentro de gettimeslots");
            return jdbcTemplate.query("SELECT * from timeslot",
                    new TimeSlotRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<TimeSlot>();
        }
    }
    /*Prueba commit*/
}

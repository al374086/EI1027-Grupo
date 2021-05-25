package es.uji.ei1027.reservas.modelo;

import java.sql.Time;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

public class TimeSlot {
	
	private int timeId;
<<<<<<< HEAD
	@DateTimeFormat(iso=DateTimeFormat.ISO.TIME)
	private Time startTime;
	@DateTimeFormat(iso=DateTimeFormat.ISO.TIME)
	private Time endTime;
=======
	private LocalTime startTime;
	private LocalTime endTime;
>>>>>>> branch 'main' of https://github.com/al374086/EI1027-Grupo.git
	private String nameArea;
	
	public int getTimeId() {
		return timeId;
	}
	public void setTimeId(int timeId) {
		this.timeId = timeId;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime localTime) {
		this.startTime = localTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	public String getNameArea() {
		return nameArea;
	}
	public void setNameArea(String nameArea) {
		this.nameArea = nameArea;
	}
	@Override
	public String toString() {
		return "TimeSlot [timeId=" + timeId + ", startTime=" + startTime + ", endTime=" + endTime + ", nameArea="
				+ nameArea + "]";
	} 
	
	

}

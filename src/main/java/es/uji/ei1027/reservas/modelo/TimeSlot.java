package es.uji.ei1027.reservas.modelo;

import java.sql.Time;

import org.springframework.format.annotation.DateTimeFormat;

public class TimeSlot {
	
	private int timeId;
	@DateTimeFormat(iso=DateTimeFormat.ISO.TIME)
	private Time startTime;
	@DateTimeFormat(iso=DateTimeFormat.ISO.TIME)
	private Time endTime;
	private String nameArea;
	
	public int getTimeId() {
		return timeId;
	}
	public void setTimeId(int timeId) {
		this.timeId = timeId;
	}
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
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

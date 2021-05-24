package es.uji.ei1027.reservas.modelo;

import java.sql.Time;
import java.time.LocalTime;

public class TimeSlot {
	
	private int timeId;
	private LocalTime startTime;
	private LocalTime endTime;
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

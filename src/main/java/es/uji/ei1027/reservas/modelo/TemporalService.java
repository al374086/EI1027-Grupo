package es.uji.ei1027.reservas.modelo;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class TemporalService {
	
	private int idtemporalservice;
	
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate initialdate;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate enddate;
	
	@DateTimeFormat(iso=DateTimeFormat.ISO.TIME)
	private LocalTime starttime;
	@DateTimeFormat(iso=DateTimeFormat.ISO.TIME)
	private LocalTime endtime;
	 
	 
	private String name_area;
	private int idservice;
	
	
	
	public TemporalService() {
		
	}


	public int getIdtemporalservice() {
		return idtemporalservice;
	}


	public void setIdtemporalservice(int idtemporalservice) {
		this.idtemporalservice = idtemporalservice;
	}


	public LocalDate getInitialdate() {
		return initialdate;
	}


	public void setInitialdate(LocalDate initialdate) {
		this.initialdate = initialdate;
	}


	public LocalDate getEnddate() {
		return enddate;
	}


	public void setEnddate(LocalDate enddate) {
		this.enddate = enddate;
	}


	public LocalTime getStarttime() {
		return starttime;
	}


	public void setStarttime(LocalTime starttime) {
		this.starttime = starttime;
	}


	public LocalTime getEndtime() {
		return endtime;
	}


	public void setEndtime(LocalTime endtime) {
		this.endtime = endtime;
	}


	public String getName_area() {
		return name_area;
	}


	public void setName_area(String name_area) {
		this.name_area = name_area;
	}


	public int getIdservice() {
		return idservice;
	}


	public void setIdservice(int idservice) {
		this.idservice = idservice;
	}
	
	
	@Override
	public String toString() {
		return "TemporalService {" + " idtemporalservice='" + idtemporalservice + "\'" + ", initialdate='" + initialdate + "\'" + ", enddate='" + enddate + "\'" + ",starttime='"
				+ starttime+ "\'" + ", endtime='" + endtime + "\'" + ", name_area='" + name_area + "\'" + ", idservice='" + idservice + "\'" + "}";
	}

}

package es.uji.ei1027.clubesportiu.modelo;

import java.sql.Time;
import java.util.Date;

public class TemporalService {
	
	private int idtemporalservice;
	private Date initialdate;
	private Date enddate;
	private Time starttime;
	private Time endtime;
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


	public Date getInitialdate() {
		return initialdate;
	}


	public void setInitialdate(Date initialdate) {
		this.initialdate = initialdate;
	}


	public Date getEnddate() {
		return enddate;
	}


	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}


	public Time getStarttime() {
		return starttime;
	}


	public void setStarttime(Time starttime) {
		this.starttime = starttime;
	}


	public Time getEndtime() {
		return endtime;
	}


	public void setEndtime(Time endtime) {
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
		return "Plan {" + "idtemporalservice='" + idtemporalservice + "\'" + ", initialdate='" + initialdate + "\'" + ", enddate='" + enddate + "\'" + ",starttime='"
				+ starttime+ "\'" + ", endtime='" + endtime + "\'" + ", name_area='" + name_area + "\'" + ", idservice='" + idservice + "\'" + "}";
	}

}

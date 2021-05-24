package es.uji.ei1027.reservas.modelo;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ServicesArea {
	
	private int idplan;
	private String comments;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate initialDate;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate endDate;
	private String name_area;
	private int service;
	
	

	public ServicesArea() {
		
	}



	public int getIdplan() {
		return idplan;
	}



	public void setIdplan(int idplan) {
		this.idplan = idplan;
	}



	public String getComments() {
		return comments;
	}



	public void setComments(String comments) {
		this.comments = comments;
	}



	public LocalDate getInitialDate() {
		return initialDate;
	}



	public void setInitialDate(LocalDate initialDate) {
		this.initialDate = initialDate;
	}



	public LocalDate getEndDate() {
		return endDate;
	}



	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}



	public String getName_area() {
		return name_area;
	}

	public void setName_area(String name_area) {
		this.name_area = name_area;
	}
	
	
	public void setService(int service) {
		this.service = service;
	}
	
	public int getService() {
		return service;
	}


	@Override
	public String toString() {
		return "ServicesArea {" + "idplan='" + idplan + "\'" + ", comments='" + comments + "\'" + ", initialDate='" + initialDate + "\'" + ",endDate='"
				+ endDate + "\'" + ", name_area='" + name_area + "\'" + ", service='" + service +"\'" + "}";
	}                                                                
	
	
	

}

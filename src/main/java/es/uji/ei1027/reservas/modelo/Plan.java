package es.uji.ei1027.reservas.modelo;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Plan {
	
	private int idplan;
	private String comments;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate initialDate;
	private LocalDate endDate;
	private String name_area;
	
	

	public Plan() {
		
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



	@Override
	public String toString() {
		return "Plan {" + "idplan='" + idplan + "\'" + ", comments='" + comments + "\'" + ", initialDate='" + initialDate + "\'" + ",endDate='"
				+ endDate + "\'" + ", name_area='" + name_area + "\'" + "}";
	}
	
	
	

}

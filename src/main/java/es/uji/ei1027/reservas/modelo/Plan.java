package es.uji.ei1027.reservas.modelo;

import java.util.Date;

public class Plan {
	
	private int idplan;
	private String comments;
	private Date initialDate;
	private Date endDate;
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



	public Date getInitialDate() {
		return initialDate;
	}



	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}



	public Date getEndDate() {
		return endDate;
	}



	public void setEndDate(Date endDate) {
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

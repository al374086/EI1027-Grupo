package es.uji.ei1027.reservas.modelo;

public class PlanAsignado {
	
	private int idservicio;
	private int idplan;
	
	
	
	public PlanAsignado() {
		
	}



	public int getIdservicio() {
		return idservicio;
	}



	public void setIdservicio(int idservicio) {
		this.idservicio = idservicio;
	}



	public int getIdplan() {
		return idplan;
	}



	public void setIdplan(int idplan) {
		this.idplan = idplan;
	}
	
	
	@Override
	public String toString() {
		return "Service {" + "idservicio='" + idservicio + "\'" + ", idplan='" + idplan + "\'" + "}";
	}
	
	
	

}

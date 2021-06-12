package es.uji.ei1027.reservas.modelo;

import java.util.List;

public class MostrarReserva {
	private Reserve reserve;
	private String area;
	private List<String> zonas;
	private TimeSlot timeSlot;
	private String imagen;
	
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public TimeSlot getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}
	public Reserve getReserve() {
		return reserve;
	}
	public void setReserve(Reserve reserve) {
		this.reserve = reserve;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public List<String> getZonas() {
		return zonas;
	}
	public void setZonas(List<String> zonas) {
		this.zonas = zonas;
	}
	
	
}

package es.uji.ei1027.reservas.modelo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class FormularioReservarArea {

	private String provincia;
	private String localidad;
	private String area;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate fecha;
	private String time;
	
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	} 
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
}

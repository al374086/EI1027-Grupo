package es.uji.ei1027.reservas.modelo;

public class OcupacionHora {
	private TimeSlot time;
	private int capacidad;
	private int ocupado;
	private double porcentaje;
	
	
	public TimeSlot getTime() {
		return time;
	}
	public void setTime(TimeSlot time) {
		this.time = time;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public int getOcupado() {
		return ocupado;
	}
	public void setOcupado(int ocupado) {
		this.ocupado = ocupado;
	}
	public double getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	
	
}

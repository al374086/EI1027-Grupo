package es.uji.ei1027.reservas.modelo;

import java.util.List;

public class OcupacionArea {
	private String Area;
	private List<OcupacionDia> ocupacion;
	public String getArea() {
		return Area;
	}
	public void setArea(String area) {
		Area = area;
	}
	public List<OcupacionDia> getOcupacion() {
		return ocupacion;
	}
	public void setOcupacion(List<OcupacionDia> ocupacion) {
		this.ocupacion = ocupacion;
	}
	
	
}

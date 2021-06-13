package es.uji.ei1027.reservas.modelo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class OcupacionDia {
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	private List<OcupacionHora> ocupacionHora;
	
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public List<OcupacionHora> getOcupacionHora() {
		return ocupacionHora;
	}
	public void setOcupacionHora(List<OcupacionHora> ocupacionHora) {
		this.ocupacionHora = ocupacionHora;
	}
	
	
	
	

}

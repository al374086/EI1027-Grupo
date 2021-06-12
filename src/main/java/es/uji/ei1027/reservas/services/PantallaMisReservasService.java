package es.uji.ei1027.reservas.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.reservas.dao.AreaDao;
import es.uji.ei1027.reservas.dao.ReserveDao;
import es.uji.ei1027.reservas.dao.TimeSlotDao;
import es.uji.ei1027.reservas.dao.ZonasReservadasDao;
import es.uji.ei1027.reservas.modelo.MostrarReserva;
import es.uji.ei1027.reservas.modelo.Reserve;
import es.uji.ei1027.reservas.modelo.ZonasReservadas;
import es.uji.ei1027.reservas.modelo.Zone;

@Repository
public class PantallaMisReservasService {
	
	@Autowired
	ReserveDao reservas;
	
	@Autowired
	ZonasReservadasDao zonasreservadas;
	
	@Autowired
	AreaDao areaDao;
	
	@Autowired
	TimeSlotDao timeSlot;
	
	public List<MostrarReserva> getMisReservas(String dni) {
		List<MostrarReserva> misReservas = new ArrayList<MostrarReserva>();
		LocalDate ayer = LocalDate.now().plusDays(-1);
		for (Reserve reserva :reservas.getReserves()) {
			if (reserva.getDni().equals(dni) && reserva.getDateOfTheReserve().isAfter(ayer) && (reserva.getStatus().equals("Reserved") || reserva.getStatus().equals("Active"))) {
				MostrarReserva unaReserva = new MostrarReserva();
				unaReserva.setReserve(reserva);
				List<String> zonas = new ArrayList<String>();
				String area = "Area desconozida ERROR";
				for (ZonasReservadas zonaReservada: zonasreservadas.getZonasReservadas()) {
					if ( zonaReservada.getNumberofreserve() == reserva.getNumberOfReserve()) {
						zonas.add(zonaReservada.getLetterAndNumber());
						area = zonaReservada.getNameArea();
					}
				}
				String imagen = null;
				if ( !area.equals("Area desconozida ERROR"))
					imagen = areaDao.getArea(area).getImagen();
				if (imagen == null) {
					imagen = "https://bit.ly/2TfGaWR";
				}
				
				unaReserva.setArea(area);
				unaReserva.setZonas(zonas);
				unaReserva.setTimeSlot(timeSlot.getTimeSlot(reserva.getTimeID()));
				unaReserva.setImagen(imagen);
				misReservas.add(unaReserva);
			}
		}
		
		return misReservas;
		
	}
	
	public MostrarReserva getMisReservas(String dni, int id) {
		List<MostrarReserva> misReservas = getMisReservas(dni);
		for ( MostrarReserva reserva : misReservas) {
			if (reserva.getReserve().getNumberOfReserve() == id) {
				return reserva;
			}
		}
		return null;
	}
	
	public void cancelarReserva(String dni, int id) {
		List<MostrarReserva> misReservas = getMisReservas(dni);		
		for ( MostrarReserva reserva : misReservas) {
			if (reserva.getReserve().getNumberOfReserve() == id) {
				reserva.getReserve().setStatus("Cancelled");
				reservas.updateReserve(reserva.getReserve());
				break;
			}
		}
	}

}

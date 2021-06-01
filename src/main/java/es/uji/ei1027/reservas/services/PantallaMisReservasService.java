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
	AreaDao area;
	
	@Autowired
	TimeSlotDao timeSlot;
	
	public List<MostrarReserva> getMisReservas(String dni) {
		List<MostrarReserva> misReservas = new ArrayList<MostrarReserva>();
		LocalDate ayer = LocalDate.now().plusDays(-1);
		for (Reserve reserva :reservas.getReserves()) {
			if (reserva.getDni().equals(dni) && reserva.getDateOfTheReserve().isBefore(ayer)) {
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
				unaReserva.setArea(area);
				unaReserva.setZonas(zonas);
				unaReserva.setTimeSlot(timeSlot.getTimeSlot(reserva.getTimeID()));
				misReservas.add(unaReserva);
			}
		}
		
		return misReservas;
		
	}

}

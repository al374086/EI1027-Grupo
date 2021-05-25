package es.uji.ei1027.reservas.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.reservas.dao.AreaDao;
import es.uji.ei1027.reservas.dao.MunicipalityDao;
import es.uji.ei1027.reservas.dao.ReserveDao;
import es.uji.ei1027.reservas.dao.TimeSlotDao;
import es.uji.ei1027.reservas.dao.ZonasReservadasDao;
import es.uji.ei1027.reservas.dao.ZoneDao;
import es.uji.ei1027.reservas.modelo.Area;
import es.uji.ei1027.reservas.modelo.Municipality;
import es.uji.ei1027.reservas.modelo.Reserve;
import es.uji.ei1027.reservas.modelo.TimeSlot;
import es.uji.ei1027.reservas.modelo.ZonasReservadas;
import es.uji.ei1027.reservas.modelo.Zone;

@Repository
public class PantallaReservarService {
	
	@Autowired
	MunicipalityDao municipality;
	
	@Autowired
	AreaDao area;
	
	@Autowired
	TimeSlotDao timeslot;
	
	@Autowired
	ZonasReservadasDao zonasreservadas;
	
	@Autowired
	ZoneDao zone;
	
	@Autowired
	ReserveDao reservas;
	
	public List<String> getProvincias() {
		List<String> provincias = new ArrayList<String>();
		List<Municipality> municipios = municipality.getMunicipality();
		for (Municipality municipio : municipios) {
			if (!provincias.contains(municipio.getProvince()))
			provincias.add(municipio.getProvince()); //De momento dejo PAIS como provincia..
		}
		return provincias;
	}
	
	public List<String> getLocalidades(String provincia) {
		List<String> localidades = new ArrayList<String>();
		List<Municipality> municipios = municipality.getMunicipality();
		for (Municipality municipio : municipios) {
			if (municipio.getProvince().equals(provincia))
				localidades.add(municipio.getName());
		}
		return localidades;
	}
	
	public List<Area> getAreas(String localidad) { //Proximamente, modificar funcion a un municipio solo
		List<Area> areas = new ArrayList<Area>();
		Municipality municipio = new Municipality();
		for (Municipality busqueda : municipality.getMunicipality()) {
			if (busqueda.getName().equals(localidad)) {
				municipio = busqueda;
				break;
			}
		}
		for (Area area: area.getAreas()) {
			if (area.getCodeMunicipality() == municipio.getCode())
				areas.add(area);
		}
		return areas;
	}
	
	public List<TimeSlot> getTimeSlots(String area) {
		List<TimeSlot> timeslots = new ArrayList<TimeSlot>();
		for (TimeSlot busqueda : timeslot.getTimeSlots()) {
			if (busqueda.getNameArea().equals(area))
				timeslots.add(busqueda);
		}
		return timeslots;
	}
	
	
	public List<Zone> getZonasDisponibles(String area, LocalDate fecha, TimeSlot time) {
		List<Zone> listaDeZonas = new ArrayList<Zone>();
		List<String> reservasDeUnArea = new ArrayList<String>();
		for (ZonasReservadas busqueda : zonasreservadas.getZonasReservadas()) {
			if (busqueda.getNameArea().equals(area)) {
				Reserve reserva = reservas.getReserve(busqueda.getNumberofreserve());
				if (reserva.getTimeID() == time.getTimeId() && reserva.getStatus().equals("Reserved") && reserva.getDateOfTheReserve().equals(fecha))
					reservasDeUnArea.add(busqueda.getLetterAndNumber());
			}
		}
		for (Zone busqueda : zone.getZones()) {
			if (busqueda.getNameArea().equals(area)) { 
				if (!reservasDeUnArea.contains(busqueda.getLetterAndNumber()))
					listaDeZonas.add(busqueda);
			}
		}
		return listaDeZonas;
		
	}
	
	public TimeSlot getTimeSlot(int id) {
		return timeslot.getTimeSlo(id);
	}
	
	
	//Pruebas
	public void reservar(String area, LocalDate fecha, int timeId, String letterAndNumber) {
		Reserve reserva = new Reserve();
		reserva.setDateOfReservation(LocalDate.now());
		reserva.setDateOfTheReserve(fecha);
		reserva.setDni("73404595");
		reserva.setNumberOfPeople(5);
		reserva.setQrCode("qr");
		reserva.setStatus("Reserved");
		reserva.setTimeID(timeId);
		
		reservas.addReserve(reserva);
		
		//Falta conocer el numero de reserva
		int maxId  = 0;
		for (Reserve busqueda : reservas.getReserves()) {
			if (busqueda.getNumberOfReserve() > maxId)
				maxId = busqueda.getNumberOfReserve();
		}
		
		
		
		ZonasReservadas zonaReserva = new ZonasReservadas();
		zonaReserva.setLetterAndNumber(letterAndNumber);
		zonaReserva.setNameArea(area);
		zonaReserva.setNumberofreserve(maxId);
		
		zonasreservadas.addZonaReservadas(zonaReserva);
		// reserve.getDateOfReservation(), reserve.getDateOfTheReserve(), reserve.getStatus(), reserve.getNumberOfPeople(),
		//   reserve.getQrCode(), reserve.getDni(), reserve.getTimeID()
	}

}

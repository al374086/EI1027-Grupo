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
import es.uji.ei1027.reservas.modelo.MostrarAreas;
import es.uji.ei1027.reservas.modelo.MostrarReserva;
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
	
	public List<MostrarAreas> getAreasv2(String localidad) { //Proximamente, modificar funcion a un municipio solo
		List<MostrarAreas> areas = new ArrayList<MostrarAreas>();
		Municipality municipio = new Municipality();
		for (Municipality busqueda : municipality.getMunicipality()) {
			if (busqueda.getName().equals(localidad)) {
				municipio = busqueda;
				break;
			}
		}
		for (Area area: area.getAreas()) {
			if (area.getCodeMunicipality() == municipio.getCode()) {
				MostrarAreas mostrar = new MostrarAreas();
				
				String imagen = area.getImagen();
				if (imagen == null || imagen.length() == 0) {
					imagen = "https://bit.ly/2TfGaWR";
				}
				area.setImagen(imagen);
				mostrar.setArea(area);
				mostrar.setTimeSlot(this.getTimeSlots(area.getName()));
				
				areas.add(mostrar);
			}
		}
		return areas;
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
	
	
	public List<Zone> getZonasDisponibles(String area, LocalDate fecha, TimeSlot time, List<String> seleccionado) {
		List<Zone> listaDeZonas = new ArrayList<Zone>();
		List<String> reservasDeUnArea = new ArrayList<String>();
		for (ZonasReservadas busqueda : zonasreservadas.getZonasReservadas()) {
			if (busqueda.getNameArea().equals(area)) {
				Reserve reserva = reservas.getReserve(busqueda.getNumberofreserve());
				if (reserva.getTimeID() == time.getTimeId() && reserva.getStatus().equals("Reserved") && reserva.getDateOfTheReserve().equals(fecha))
					reservasDeUnArea.add(busqueda.getLetterAndNumber());
			}
		}
		for (String zona : seleccionado) {
			reservasDeUnArea.add(zona);
		}
		for (Zone busqueda : zone.getZones()) {
			if (busqueda.getNameArea().equals(area)) { 
				if (!reservasDeUnArea.contains(busqueda.getLetterAndNumber()))
					listaDeZonas.add(busqueda);
			}
		}
		return listaDeZonas;
		
	}
	
	public List<Zone> getZones(List<String> letras, String area) {
		List<Zone> zonas = new ArrayList<Zone>();
		for (String letra : letras)
			zonas.add(zone.getZone(letra, area));
		return zonas;
	}
	
	public TimeSlot getTimeSlot(int id) {
		return timeslot.getTimeSlot(id);
	}
	
	public String getImagen(String nameArea) {
		String imagen = area.getArea(nameArea).getImagen();
		if (imagen == null) {
			imagen = "https://bit.ly/2TfGaWR";
		}
		return imagen;
	}
	
	
	//Pruebas
	public void reservar(String area, LocalDate fecha, int timeId, List<String> ListletterAndNumber, String dni) {
		Reserve reserva = new Reserve();
		reserva.setDateOfReservation(LocalDate.now());
		reserva.setDateOfTheReserve(fecha);
		reserva.setDni(dni);
		reserva.setQrCode("QR not aviable");
		reserva.setStatus("Reserved");
		reserva.setTimeID(timeId);
		
		int NumberOfPeople = 0;
		for (String letterAndNumber : ListletterAndNumber) 
			NumberOfPeople += zone.getZone(letterAndNumber, area).getCapacity();
		reserva.setNumberOfPeople(NumberOfPeople);
		
		reservas.addReserve(reserva);
		
		//Falta conocer el numero de reserva
		int maxId  = 0;
		for (Reserve busqueda : reservas.getReserves()) {
			if (busqueda.getNumberOfReserve() > maxId)
				maxId = busqueda.getNumberOfReserve();
		}
		
		
		for (String letterAndNumber : ListletterAndNumber) {
			ZonasReservadas zonaReserva = new ZonasReservadas();
			zonaReserva.setLetterAndNumber(letterAndNumber);
			zonaReserva.setNameArea(area);
			zonaReserva.setNumberofreserve(maxId);
			zonasreservadas.addZonaReservadas(zonaReserva);
			
		}
		
		;
		// reserve.getDateOfReservation(), reserve.getDateOfTheReserve(), reserve.getStatus(), reserve.getNumberOfPeople(),
		//   reserve.getQrCode(), reserve.getDni(), reserve.getTimeID()
	}

}

package es.uji.ei1027.reservas.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.reservas.dao.AreaDao;
import es.uji.ei1027.reservas.dao.MunicipalityDao;
import es.uji.ei1027.reservas.dao.ReserveDao;
import es.uji.ei1027.reservas.dao.TimeSlotDao;
import es.uji.ei1027.reservas.dao.ZonasReservadasDao;
import es.uji.ei1027.reservas.dao.ZoneDao;
import es.uji.ei1027.reservas.modelo.Area;
import es.uji.ei1027.reservas.modelo.Municipality;
import es.uji.ei1027.reservas.modelo.OcupacionArea;
import es.uji.ei1027.reservas.modelo.OcupacionDia;
import es.uji.ei1027.reservas.modelo.OcupacionHora;
import es.uji.ei1027.reservas.modelo.Reserve;
import es.uji.ei1027.reservas.modelo.TimeSlot;
import es.uji.ei1027.reservas.modelo.Zone;

@Repository
public class GestorService {
	
	@Autowired
	MunicipalityDao municipality;
	
	@Autowired
	ReserveDao reservas;
	
	@Autowired
	ZonasReservadasDao zonasreservadas;
	
	@Autowired
	AreaDao areaDao;
	
	@Autowired
	TimeSlotDao timeDao;
	
	@Autowired
	ZoneDao zoneDao;
	
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
	
	public List<OcupacionArea> getNivelOcupacion(String municipio) {
		//Buscar codigo municipio
		int codigo = -1;
		for (Municipality mun : municipality.getMunicipality()) {
			if (mun.getName().equals(municipio)) {
				codigo = mun.getCode();
				break;
			}
		}
		
		
		
		//Buscar reservas dependiendo del dia
		List<Reserve> reservasHoy = new ArrayList<Reserve>();
		List<Reserve> reservasMañana = new ArrayList<Reserve>();
		List<Reserve> reservasPasadoMañana = new ArrayList<Reserve>();
		LocalDate hoy = LocalDate.now();
		LocalDate mañana = hoy.plusDays(1);
		LocalDate pasadoMañana = hoy.plusDays(2);
		for (Reserve reserva : reservas.getReserves()) {
			if (!reserva.getStatus().equals("Cancelled")) {
				if (reserva.getDateOfTheReserve().equals(hoy)) {
					reservasHoy.add(reserva);
				}
				else if ( reserva.getDateOfTheReserve().equals(mañana)) {
					reservasMañana.add(reserva);
				}
				else if ( reserva.getDateOfTheReserve().equals(pasadoMañana)) {
					reservasPasadoMañana.add(reserva);
				}
			}
		}
		
		
		List<OcupacionArea> ocupacion = new ArrayList<OcupacionArea>();
		for(Area area : areaDao.getAreas()) {
			if ( area.getCodeMunicipality() == codigo && area.isRestricted()) {
				int capacidad = 0;
				for ( Zone zone : zoneDao.getZones()) {
					if (zone.getNameArea().equals(area.getName()))
						capacidad += zone.getCapacity();
				}
				
				
				
				OcupacionArea ocupacionEnArea = new OcupacionArea();
				ocupacionEnArea.setArea(area.getName());
				List<OcupacionDia> ocupacionPorDia = new ArrayList<OcupacionDia>();
				ocupacionPorDia.add(this.obtenerOcupacionDia(area, hoy, reservasHoy, capacidad));
				ocupacionPorDia.add(this.obtenerOcupacionDia(area, mañana, reservasMañana, capacidad));
				ocupacionPorDia.add(this.obtenerOcupacionDia(area, pasadoMañana, reservasPasadoMañana, capacidad));
				
				ocupacionEnArea.setOcupacion(ocupacionPorDia);
				ocupacion.add(ocupacionEnArea);
				
			}
		}
		return ocupacion;
	}
	
	private OcupacionDia obtenerOcupacionDia(Area area, LocalDate date, List<Reserve> reservas, int capacidad) {
		OcupacionDia ocupado = new OcupacionDia();
		ocupado.setDate(date);
		List<OcupacionHora> listOcupadoHora = new ArrayList<OcupacionHora>();
		for (TimeSlot time :timeDao.getTimeSlots()) {
			if ( time.getNameArea().equals(area.getName())) {
				OcupacionHora ocupadoHora = new OcupacionHora();
				ocupadoHora.setTime(time);
				ocupadoHora.setCapacidad(capacidad);
				int cantidadOcupada = 0;
				for ( Reserve reserva : reservas) {
					if ( reserva.getTimeID() == time.getTimeId() )
						cantidadOcupada += reserva.getNumberOfPeople();
				}
				ocupadoHora.setOcupado(cantidadOcupada);
				ocupadoHora.setPorcentaje(Math.round(cantidadOcupada*100.0/capacidad));
				
				listOcupadoHora.add(ocupadoHora);
			}
		}
		ocupado.setOcupacionHora(listOcupadoHora);
		return ocupado;
	}

}

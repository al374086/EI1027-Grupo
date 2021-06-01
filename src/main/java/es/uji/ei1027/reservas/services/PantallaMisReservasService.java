package es.uji.ei1027.reservas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.reservas.dao.AreaDao;
import es.uji.ei1027.reservas.dao.ReserveDao;
import es.uji.ei1027.reservas.dao.ZonasReservadasDao;

@Repository
public class PantallaMisReservasService {
	
	@Autowired
	ReserveDao reservas;
	
	@Autowired
	ZonasReservadasDao zonasreservadas;
	
	@Autowired
	AreaDao area;

}

package es.uji.ei1027.reservas.services;

import org.springframework.beans.factory.annotation.Autowired;
import es.uji.ei1027.reservas.dao.AreaDao;
//import es.uji.ei1027.reservas.dao.PlanDao;
import es.uji.ei1027.reservas.modelo.Area;
import es.uji.ei1027.reservas.modelo.Municipality;


class GestionAreaSvc implements GestionAreaService{
	
	@Autowired
	AreaDao areaDao;

	@Autowired
	//PlanDao planDao;

	@Override
	public void addArea() {
		
	}
}

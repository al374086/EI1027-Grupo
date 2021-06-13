package es.uji.ei1027.reservas.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.reservas.dao.ServiceDao;
import es.uji.ei1027.reservas.modelo.Service;

@Repository
public class GestionAreaService{
	
	@Autowired
	ServiceDao servicedao;

	public List<Service> getservices(String area) {
		//List<String> services = new ArrayList<String>();
		List<Service> servicios = servicedao.getServices(area);
		//System.out.println(servicios.size());
		//for (Service servicio : servicios ) {
		//	services.add(servicio.getName());
		//}
		return servicios;
	}
	
	public List<String> getservicesCombo(List<Service> serviciosInc) {
		List<Integer> serviciosInt = new ArrayList<Integer>();
		for (Service servicio : serviciosInc) {
			serviciosInt.add(servicio.getIdservice());
		}
		List<String> services = new ArrayList<String>();
		List<Service> servicios = servicedao.getServicesCombo();
		//System.out.println(servicios.size());
		for (Service servicio : servicios ) {
			if(!serviciosInt.contains(servicio.getIdservice()))
				services.add(servicio.getName());
		}
		return services;
	}
}

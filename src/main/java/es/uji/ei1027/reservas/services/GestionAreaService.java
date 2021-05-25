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

	public List<String> getservices(String area) {
		List<String> services = new ArrayList<String>();
		List<Service> servicios = servicedao.getServices(area);
		//System.out.println(servicios.size());
		for (Service servicio : servicios ) {
			services.add(servicio.getName());
		}
		return services;
	}
	
	public List<String> getservicesCombo() {
		List<String> services = new ArrayList<String>();
		List<Service> servicios = servicedao.getServicesCombo();
		//System.out.println(servicios.size());
		for (Service servicio : servicios ) {
			services.add(servicio.getName());
		}
		return services;
	}
}

package es.uji.ei1027.reservas.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.reservas.dao.AreaDao;
import es.uji.ei1027.reservas.dao.MunicipalManagerDao;
import es.uji.ei1027.reservas.dao.MunicipalityDao;
import es.uji.ei1027.reservas.dao.ReserveDao;
import es.uji.ei1027.reservas.dao.TimeSlotDao;
import es.uji.ei1027.reservas.modelo.Area;
import es.uji.ei1027.reservas.modelo.FormularioReservarArea;
import es.uji.ei1027.reservas.modelo.MunicipalManager;
import es.uji.ei1027.reservas.modelo.Municipality;
import es.uji.ei1027.reservas.modelo.Reserve;
import es.uji.ei1027.reservas.modelo.TimeSlot;
import es.uji.ei1027.reservas.modelo.Usuario;
import es.uji.ei1027.reservas.services.GestorService;

@Controller
@RequestMapping("/gestormunicipal")
public class GestorMunicipalController {
	
	private GestorService gestorService;
	private MunicipalManagerDao municipalManagerDao;
	private MunicipalityDao municipioDao;
	private ReserveDao reserveDao;
	private TimeSlotDao timeSlotDao;
	private AreaDao areaDao;

	   @Autowired
	   public void setAreaDao(AreaDao areaDao) {
	       this.areaDao=areaDao;
	   }
	
	    @Autowired
	   public void setTimeSlot(TimeSlotDao timeSlotDao) {
	       this.timeSlotDao=timeSlotDao;
	   }
	
	   @Autowired
	   public void setReserveDao(ReserveDao reserveDao) {
	       this.reserveDao=reserveDao;
	   }

	   @Autowired 
	   public void setSociDao(GestorService gestorService) {
	       this.gestorService = gestorService;
	   }
	   
	   @Autowired 
	   public void setDaoMunicipalManager(MunicipalManagerDao municipalManagerDao) {
	       this.municipalManagerDao = municipalManagerDao;
	   }
	   
	   @Autowired 
	   public void setMunicipioDao(MunicipalityDao municipioDao) {
	       this.municipioDao = municipioDao;
	   }
	
	@RequestMapping(value="/index")
	public String index(HttpSession session, Model model) {
		return "/gestormunicipal/index"; 
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String listaReservas(HttpSession session, Model model) {
		if (session.getAttribute("user") == null || ((Usuario) session.getAttribute("user")).getRol().equals("ciudadanos")) 
	       { 
	          return "redirect:/user/login";
	       } 
		MunicipalManager manager = municipalManagerDao.getMunicipalManager(((Usuario) session.getAttribute("user")).getUsername());
		Municipality municipio = municipioDao.getMunicipality(manager.getCode());
		
		
		List<Reserve> reserves = new ArrayList<Reserve>();
		for (Reserve reserva : reserveDao.getReserves()) {
			TimeSlot time = timeSlotDao.getTimeSlot(reserva.getTimeID());
			Area area = areaDao.getArea(time.getNameArea());
			if (area.getCodeMunicipality() == municipio.getCode() && reserva.getStatus().equals("Reserved")) {
				reserva.setArea(area.getName());
				reserva.setTime(time);
				reserves.add(reserva);
				
			}
			
		}
		model.addAttribute("reserves", reserves );
		return "/gestormunicipal/list"; 
	}
	
	
	@RequestMapping(value="/ocupacion")
	public String ocupacion(HttpSession session, Model model) {
		if (session.getAttribute("user") == null || ((Usuario) session.getAttribute("user")).getRol().equals("ciudadanos")) 
	       { 
	          return "redirect:/user/login";
	       } 
		FormularioReservarArea datos = new FormularioReservarArea();
		MunicipalManager manager = municipalManagerDao.getMunicipalManager(((Usuario) session.getAttribute("user")).getUsername());
		Municipality municipio = municipioDao.getMunicipality(manager.getCode());
		datos.setProvincia(municipio.getProvince());
		datos.setLocalidad(municipio.getName());
		model.addAttribute("provinciasList", datos.getProvincia());
		model.addAttribute("localidadesList", datos.getLocalidad());
		model.addAttribute("ocupado", gestorService.getNivelOcupacion(datos.getLocalidad()));
		
		model.addAttribute("formulario", datos);
		return "/gestormunicipal/ocupacion"; 
	}
}

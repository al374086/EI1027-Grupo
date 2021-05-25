package es.uji.ei1027.reservas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import es.uji.ei1027.reservas.dao.ZonasReservadasDao;


@Controller
@RequestMapping("/zonareservada")

public class ZonaReservadaController {
	
	private ZonasReservadasDao zonasreservadasDao;
	
	 @Autowired
	   public void setServicesAreaDao(ZonasReservadasDao zonareservadasDao) {
	       this.zonasreservadasDao=zonareservadasDao;
	   }
	 
	// Operacions: Crear, llistar, actualitzar, esborrar
	   // ...
	 
	 
	  @RequestMapping("/list")
	    public String listServicesAreaDao(Model model) {
	        model.addAttribute("zonasreservadas", zonasreservadasDao.getZonasReservadas());
	        return "zonasreservadas/list";
	    }
	  
}
	 
	 

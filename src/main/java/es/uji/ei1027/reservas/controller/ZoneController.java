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

import es.uji.ei1027.reservas.dao.ZoneDao;
import es.uji.ei1027.reservas.modelo.Zone;

@Controller
@RequestMapping("/zone")
public class ZoneController {
	
	private ZoneDao zoneDao;

	   @Autowired
	   public void setZoneDao(ZoneDao zoneDao) {
	       this.zoneDao=zoneDao;
	   }

	   //Service
 /*   	private ZoneService zoneService;

		@Autowired
		public void setZoneService(ZoneService zoneService) {
			this.zoneService = zoneService;
		} */
	   
	   // Operacions: Crear, llistar, actualitzar, esborrar
	   
	   @RequestMapping("/list")
	   public String listNadadors(Model model) {
	      model.addAttribute("zones", zoneDao.getZones());
	      return "zone/list";
	   }
	   
	   @RequestMapping(value="/add") 
		public String addZone(Model model) {
			model.addAttribute("zone", new Zone());
			return "zone/add";
		}
	   /*
	   @RequestMapping(value="/add", method=RequestMethod.POST)
	   public String processAddSubmit(@ModelAttribute("zone") Zone zone,
	                                   BindingResult bindingResult) { 
	   	 if (bindingResult.hasErrors())
	   			return "nadador/add";
	   	zoneDao.addZone(zone);
	   	 return "redirect:list";
	    } */
	   @RequestMapping(value="/add", method=RequestMethod.POST)
	   public String processAddClassif(
	                     @ModelAttribute("zone") Zone zone,
	                     BindingResult bindingResult) {
	      if (bindingResult.hasErrors())
	          return "zone/add";
	      try { 
	          zoneDao.addZone(zone); 
	      } catch (DuplicateKeyException e) { 
	        //  throw new ClubesportiuException(  
	        //        "Ja existeix una zone d'aquest nadador en " 
	        //        +zone.getNomProva(), "CPduplicada"); 
	      } catch (DataAccessException e) {
	        //  throw new ClubesportiuException(  
	        //        "Error en l'accés a la base de dades", "ErrorAccedintDades"); 
	      }
	      return "redirect:list";
	   }


	   
	   
	   @RequestMapping(value="/update/{letterAndNumber}/{nameArea}", method = RequestMethod.GET)
		public String editZone(Model model, @PathVariable String letterAndNumber,
                @PathVariable String nameArea) {
			model.addAttribute("zone", zoneDao.getZone(letterAndNumber,nameArea));
			return "zone/update"; 
		}
	   
	   @RequestMapping(value="/update", method = RequestMethod.POST) 
		public String processUpdateSubmit(
	                           @ModelAttribute("zone") Zone zone, 
	                           BindingResult bindingResult) {
			 if (bindingResult.hasErrors()) 
				 return "zone/update";
			 zoneDao.updateZone(zone);
			 return "redirect:list"; 
		}
	   
	   
	   
	   @RequestMapping(value = "/delete/{letterAndNumber}/{nameArea}")
	   public String processDeleteClassif(@PathVariable String letterAndNumber,
	                                      @PathVariable String nameArea) {
	      zoneDao.deleteZone(letterAndNumber, nameArea);
	      return "redirect:../../list";
	   }


}
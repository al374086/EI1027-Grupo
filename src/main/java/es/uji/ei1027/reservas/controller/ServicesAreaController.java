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


import es.uji.ei1027.reservas.dao.ServicesAreaDao;
import es.uji.ei1027.reservas.modelo.ServicesArea;

@Controller
@RequestMapping("/servicesarea")

public class ServicesAreaController {
	
	private ServicesAreaDao servicesareaDao;
	
	 @Autowired
	   public void setServicesAreaDao(ServicesAreaDao servicesareaDao) {
	       this.servicesareaDao=servicesareaDao;
	   }
	 
	// Operacions: Crear, llistar, actualitzar, esborrar
	   // ...
	 
	 
	  @RequestMapping("/list")
	    public String listServicesAreaDao(Model model) {
	        model.addAttribute("servicesareas", servicesareaDao.getServicesAreas());
	        return "servicesarea/list";
	    }
	  
	  
	 @RequestMapping(value="/add") 
		public String addServicesAreaDao(Model model) {
			model.addAttribute("servicesarea", new ServicesArea());
			return "servicesarea/add";
		}
	 
	 
	 
	 @RequestMapping(value="/add", method= RequestMethod.POST)
	    public String processAddSubmit(@ModelAttribute("servicesarea") ServicesArea servicesarea,
	                                   BindingResult bindingResult) {
	        if (bindingResult.hasErrors())
	        	return "servicesarea/add";
	       /*try {	        	
	        planDao.addPlan(plan);
	        } catch (DuplicateKeyException e) { 
	        	throw new ClubesportiuException(
	        			"Ja existeix un plan d'aquest area en " 
	        	                 +plan.getIdplan(), "CPduplicada"); 
	        }catch (DataAccessException e) { 
	            throw new ClubesportiuException(  
	                    "Error en l'accés a la base de dades", "ErrorAccedintDades"); 
	          }*/
	        
	        
	        servicesareaDao.addServicesArea(servicesarea);
	        return "redirect:list";
	 }
	 
	 
	 
	 @RequestMapping(value="/update/{idplan}/{name_area}", method = RequestMethod.GET)
	    public String editServicesArea(Model model, @PathVariable int idplan,@PathVariable String name_area) {
	        model.addAttribute("servicesarea", servicesareaDao.getServicesArea(idplan, name_area));
	        return "servicesarea/update";
	    }

	    @RequestMapping(value="/update", method = RequestMethod.POST)
	    public String processUpdateSubmit(
	            @ModelAttribute("servicesarea") ServicesArea servicesarea,
	            BindingResult bindingResult) {
	        if (bindingResult.hasErrors())
	            return "servicesarea/update";
	        servicesareaDao.updateServicesArea(servicesarea);
	        return "redirect:list";
	    }
	    

	    @RequestMapping(value="/delete/{idplan}/{name_area}")
	    public String processDelete(@PathVariable int idplan,@PathVariable String name_area) {
	    	servicesareaDao.deleteServicesArea(idplan, name_area);
	        return "redirect:../../list";
	    }
	 
	 
	    
	 

}

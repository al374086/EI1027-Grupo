package es.uji.ei1027.reservas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import es.uji.ei1027.reservas.dao.ServiceDao;
import es.uji.ei1027.reservas.modelo.Service;


@Controller
@RequestMapping("/service")


public class ServiceController {
	
	private ServiceDao serviceDao;
	
	 @Autowired
	   public void setServiceDao(ServiceDao serviceDao) {
	       this.serviceDao=serviceDao;
	   }
	 
	// Operacions: Crear, llistar, actualitzar, esborrar
	   // ...
	 
	 @RequestMapping("/list")
	    public String listServices(Model model) {
	        model.addAttribute("services", serviceDao.getServices());
	        return "service/list";
	    }
	 
	 @RequestMapping(value="/add") 
		public String addService(Model model) {
		 	
			model.addAttribute("service", new Service());
			return "service/add";
		}
	 
	 
	 @RequestMapping(value="/add", method= RequestMethod.POST)
	    public String processAddSubmit(@ModelAttribute("service") Service service,
	                                   BindingResult bindingResult) {
		 	
	        if (bindingResult.hasErrors()) 
	        	return "service/add";
	        	
	        serviceDao.addService(service);
	        return "redirect:list";
	 }
	 
	 
	 @RequestMapping(value="/update/{idservice}", method = RequestMethod.GET)
	    public String editService(Model model, @PathVariable int idservice) {
		 
	        model.addAttribute("service", serviceDao.getService(idservice));
	        
	        return "service/update";
	    }

	    @RequestMapping(value="/update", method = RequestMethod.POST)
	    public String processUpdateSubmit(
	            @ModelAttribute("service") Service service,
	            BindingResult bindingResult) {
	        if (bindingResult.hasErrors())
	            return "service/update";
	        serviceDao.updateService(service);
	        
	        return "redirect:list";
	    }
	    
	    
	    
	    @RequestMapping(value="/delete/{idservice}")
	    public String processDelete(@PathVariable int idservice) {
	    	serviceDao.deleteService(idservice);
	        return "redirect:../list";
	    }
	 
	 

}
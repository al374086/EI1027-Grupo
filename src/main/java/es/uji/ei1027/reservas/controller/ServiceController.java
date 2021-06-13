package es.uji.ei1027.reservas.controller;

import javax.servlet.http.HttpSession;

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
import es.uji.ei1027.reservas.modelo.Usuario;


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
	    public String listServices(HttpSession session, Model model) {
		 if (session.getAttribute("user") == null || !((Usuario) session.getAttribute("user")).getUsername().equals("generalitat")) 
	       { 
	          return "redirect:/user/login";
	       } 
		 
		 
	        model.addAttribute("services", serviceDao.getServices());
	        return "service/list";
	    }
	 
	 @RequestMapping(value="/add") 
		public String addService(HttpSession session, Model model) {
		 
		 if (session.getAttribute("user") == null || !((Usuario) session.getAttribute("user")).getUsername().equals("generalitat")) 
	       { 
	          return "redirect:/user/login";
	       } 
		 	
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
	    public String editService(HttpSession session, Model model, @PathVariable int idservice) {
		 if (session.getAttribute("user") == null || !((Usuario) session.getAttribute("user")).getUsername().equals("generalitat")) 
	       { 
	          return "redirect:/user/login";
	       } 
		 
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
	    public String processDelete(HttpSession session, @PathVariable int idservice) {
	    	if (session.getAttribute("user") == null || !((Usuario) session.getAttribute("user")).getUsername().equals("generalitat")) 
	        { 
	           return "redirect:/user/login";
	        } 
	    	
	    	serviceDao.deleteService(idservice);
	        return "redirect:../list";
	    }
	 
	 

}
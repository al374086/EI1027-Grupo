package es.uji.ei1027.reservas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import es.uji.ei1027.reservas.dao.TemporalServiceDao;

import es.uji.ei1027.reservas.modelo.TemporalService;


@Controller
@RequestMapping("/temporalservice")


public class TemporalServiceController {
	
	private TemporalServiceDao temporalserviceDao;
	
	 @Autowired
	   public void setTemporalService(TemporalServiceDao temporalserviceDao) {
	       this.temporalserviceDao=temporalserviceDao;
	   }
	 
	// Operacions: Crear, llistar, actualitzar, esborrar
	   // ...
	 
	 @RequestMapping("/list")
	    public String listPlanes(Model model) {
	        model.addAttribute("temporalservices", temporalserviceDao.getTemporalServices());
	        return "temporalservice/list";
	    }
	 
	 @RequestMapping(value="/add") 
		public String addTemporalService(Model model) {
			model.addAttribute("temporalservice", new TemporalService());
			return "temporalservice/add";
		}
	 
	 
	 @RequestMapping(value="/add", method= RequestMethod.POST)
	    public String processAddSubmit(@ModelAttribute("temporalservice") TemporalService temporalservice,
	                                   BindingResult bindingResult) {
	        if (bindingResult.hasErrors())
	        	return "temporalservice/add";
	       
	        return "redirect:list";
	 }
	 
	 
	 @RequestMapping(value="/update/{idtemporalservice}", method = RequestMethod.GET)
	    public String editTemporalService(Model model, @PathVariable int idtemporalservice) {
	        model.addAttribute("temporalservice", temporalserviceDao.getTemporalservice(idtemporalservice));
	        return "temporalservice/update";
	    }

	    @RequestMapping(value="/update", method = RequestMethod.POST)
	    public String processUpdateSubmit(
	            @ModelAttribute("temporalservice") TemporalService temporalservice,
	            BindingResult bindingResult) {
	        if (bindingResult.hasErrors())
	            return "temporalservice/update";
	        temporalserviceDao.updateTemporalservice(temporalservice);
	        return "redirect:list";
	    }
	    
	    
	    
	    @RequestMapping(value="/delete/{idtemporalservice}")
	    public String processDelete(@PathVariable int idtemporalservice) {
	    	temporalserviceDao.deleteTemporalService(idtemporalservice);
	        return "redirect:../list";
	    }
	 
	 

}

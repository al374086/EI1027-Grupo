package es.uji.ei1027.reservas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import es.uji.ei1027.reservas.dao.MunicipalManagerDao;
import es.uji.ei1027.reservas.modelo.MunicipalManager;


@Controller
@RequestMapping("/municipalmanager")
public class MunicipalManagerController {

   private MunicipalManagerDao municipalmanagerDao;

   @Autowired
   public void setMunicipalManager(MunicipalManagerDao municipalmanagerDao) {
       this.municipalmanagerDao=municipalmanagerDao;
   }

   // Operacions: Crear, llistar, actualitzar, esborrar
   
   @RequestMapping("/list")
   public String listMunicipalManager(Model model) {
	  
      model.addAttribute("municipalmanagers", municipalmanagerDao.getMunicipalManagers());
     
      return "municipalmanager/list";
   }
   
   
   @RequestMapping(value="/add") 
	public String addMunicipalManager(Model model) {
		model.addAttribute("municipalmanager", new MunicipalManager());
		return "municipalmanager/add";
	}
   
   
   @RequestMapping(value="/add", method=RequestMethod.POST)
   public String processAddSubmit(@ModelAttribute("municipalmanager") MunicipalManager municipalmanager,
                                   BindingResult bindingResult) { 
	
   	 if (bindingResult.hasErrors())
   			return "municipalmanager/add";
  
   	municipalmanagerDao.addMunicipalManager(municipalmanager);
   	return "redirect:list";
    } 
   
   
   @RequestMapping(value="/update/{dni}", method = RequestMethod.GET)
	public String editMunicipalManager(Model model, @PathVariable String dni) {
	   
		model.addAttribute("municipalmanager", municipalmanagerDao.getMunicipalManager(dni));
		return "municipalmanager/update"; 
	}
   
   
   @RequestMapping(value="/update", method = RequestMethod.POST) 
	public String processUpdateSubmit(
                           @ModelAttribute("municipalmanager") MunicipalManager municipalManager, 
                           BindingResult bindingResult) {
		 if (bindingResult.hasErrors()) 
			 return "municipalmanager/update";
		 
		 municipalmanagerDao.updateMunicipalManager(municipalManager);
		 return "redirect:list"; 
	}
   
   
   @RequestMapping(value="/delete/{dni}")
	public String processDelete(@PathVariable String dni) {
	   municipalmanagerDao.deleteMunicipalManagerDNI(dni);
          return "redirect:../list"; 
	}
}

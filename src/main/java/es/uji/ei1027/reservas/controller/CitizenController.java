package es.uji.ei1027.reservas.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.reservas.dao.CitizenDao;
import es.uji.ei1027.reservas.modelo.Citizen;
import es.uji.ei1027.reservas.modelo.Usuario;


@Controller
@RequestMapping("/citizen")
public class CitizenController {

   private CitizenDao citizenDao;

   @Autowired
   public void setCitizenDao(CitizenDao citizenDao) {
       this.citizenDao=citizenDao;
   }

   // Operacions: Crear, llistar, actualitzar, esborrar
   
   @RequestMapping("/list")
   public String listCitizens(Model model) {
      model.addAttribute("citizens", citizenDao.getCitizens());
      return "citizen/list";
   }
   
   @RequestMapping(value="/add") 
	public String addCitizen(Model model) {
		model.addAttribute("citizen", new Citizen());
		return "citizen/add";
	}
   
   @RequestMapping(value="/add", method=RequestMethod.POST)
   public String processAddSubmit(@ModelAttribute("citizen") Citizen citizen,
                                   BindingResult bindingResult) { 
   	 if (bindingResult.hasErrors())
   			return "citizen/add";
   	 citizenDao.addCitizen(citizen);
   	 return "redirect:list";
    } 
   
   @RequestMapping(value="/update/{dni}", method = RequestMethod.GET)
	public String editCitizen(Model model, @PathVariable String dni) {
		model.addAttribute("citizen", citizenDao.getCitizen(dni));
		return "citizen/update"; 
	}
   
   @RequestMapping(value="/update", method = RequestMethod.POST) 
	public String processUpdateSubmit(
                           @ModelAttribute("citizen") Citizen citizen, 
                           BindingResult bindingResult) {
		 if (bindingResult.hasErrors()) 
			 return "citizen/update";
		 citizenDao.updateCitizen(citizen);
		 return "redirect:list"; 
	}
   
   @RequestMapping(value="/delete/{dni}")
	public String processDelete(@PathVariable String dni) {
	      citizenDao.deleteCitizen(dni);
          return "redirect:../list"; 
	}
   
   
   
   
   @RequestMapping(value="/addRegistro") 
	public String addCitizenBBDD(Model model) {
		model.addAttribute("citizen", new Citizen());
		return "citizen/add";
	}
  

   
}

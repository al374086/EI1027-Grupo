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


import es.uji.ei1027.reservas.dao.MunicipalityDao;
import es.uji.ei1027.reservas.modelo.Municipality;
import es.uji.ei1027.reservas.modelo.Usuario;

@Controller
@RequestMapping("/municipality")

public class MunicipalityController {
	
	private MunicipalityDao municipalityDao;
	
	 @Autowired
	 public void setMunicipalityDao(MunicipalityDao municipalityDao) {
	       this.municipalityDao=municipalityDao;
	 }
	 
	 // Operacions: Crear, llistar, actualitzar, esborrar
	 @RequestMapping(value="/add") 
	 public String addMunicipality(HttpSession session, Model model) {
		 if (session.getAttribute("user") == null || !((Usuario) session.getAttribute("user")).getUsername().equals("generalitat")) 
	       { 
	          return "redirect:/user/login";
	       } 
			model.addAttribute("municipality", new Municipality());
			//System.out.println("add en el controller");
			return "municipality/add";
	 }
	 
	 @RequestMapping(value="/add", method=RequestMethod.POST)
	 public String processAddSubmit(@ModelAttribute("municipality") Municipality municipality,
	                                   BindingResult bindingResult) {
	   	 if (bindingResult.hasErrors()) 
	   			return "municipality/add";
	   	 municipalityDao.addMunicipality(municipality);
	   	 return "redirect:list";
	 }
	 
	 
	 @RequestMapping(value="/update/{code}", method = RequestMethod.GET)
	 public String editMunicipality(HttpSession session, Model model, @PathVariable int code) {
		 if (session.getAttribute("user") == null || !((Usuario) session.getAttribute("user")).getUsername().equals("generalitat")) 
	       { 
	          return "redirect:/user/login";
	       } 
			model.addAttribute("municipality", municipalityDao.getMunicipality(code));
			return "municipality/update"; 
	 }
	   
	 @RequestMapping(value="/update", method = RequestMethod.POST) 
	 public String processUpdateSubmit(
	               @ModelAttribute("municipality") Municipality municipality, 
	              BindingResult bindingResult) {
			 if (bindingResult.hasErrors()) 
				 return "municipality/update";
			 municipalityDao.updateMunicipality(municipality);
			 return "redirect:list"; 
		}
	 
	 @RequestMapping("/list") 
	 public String listMunicipality(HttpSession session, Model model) {
		 if (session.getAttribute("user") == null || !((Usuario) session.getAttribute("user")).getUsername().equals("generalitat")) 
	       { 
	          return "redirect:/user/login";
	       } 
		 model.addAttribute("municipality", municipalityDao.getMunicipality());
		 return "municipality/list";
	 }
	 
	 @RequestMapping(value="/delete/{code}")
		public String processDelete(HttpSession session, Model model, @PathVariable int code) {
		 if (session.getAttribute("user") == null || !((Usuario) session.getAttribute("user")).getUsername().equals("generalitat")) 
	       { 
	          return "redirect:/user/login";
	       } 
	          municipalityDao.deleteMunicipality(code);
	          return "redirect:../list"; 
	 }
}

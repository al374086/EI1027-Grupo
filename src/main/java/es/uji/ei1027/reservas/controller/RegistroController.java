package es.uji.ei1027.reservas.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.reservas.dao.CitizenDao;
import es.uji.ei1027.reservas.modelo.Citizen;
import es.uji.ei1027.reservas.modelo.Usuario;

@Controller
@RequestMapping("/user")
public class RegistroController {
	
	

	private CitizenDao citizenDao;

	@Autowired
	public void setCitizenDao(CitizenDao citizenDao) {
		this.citizenDao = citizenDao;
	}

	@RequestMapping(value = "/addRegistro")
	public String addCitizen(HttpSession session, Model model) {
		if (session.getAttribute("user") != null) 
	       { 
	          return "redirect:/user/login";
	       }
		model.addAttribute("citizen", new Citizen());
		return "user/addRegistro";
	}

	@RequestMapping(value = "/addRegistro", method = RequestMethod.POST)
	public String processAddSubmit(HttpSession session, @ModelAttribute("citizen") Citizen citizen, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "user/addRegistro";
		
		if (session.getAttribute("user") != null) 
	       { 
	          return "redicrect:/user/login";
	       }
		
		citizenDao.addCitizen(citizen);
		
		return "redirect:/user/login";
	}

}
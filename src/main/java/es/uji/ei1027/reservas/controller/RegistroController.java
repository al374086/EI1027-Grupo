package es.uji.ei1027.reservas.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
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
		
		CitizenValidator citizenValidator = new CitizenValidator(); 
		citizenValidator.validate(citizen, bindingResult); 
		
		if (bindingResult.hasErrors())
			return "user/addRegistro";
		
		if (session.getAttribute("user") != null) 
	       { 
	          return "redicrect:/user/login";
	       }
		
		citizenDao.addCitizen(citizen);
		
		return "redirect:/user/login";
	}
	
	
	
	class CitizenValidator implements Validator { 
		@Override
		public boolean supports(Class<?> cls) { 
			return Usuario.class.isAssignableFrom(cls);
		}
		
		@Override 
		public void validate(Object obj, Errors errors) {
		  
			Citizen citizen = (Citizen) obj;
			
			if (citizenDao.getCitizen(citizen.getDni())!=null)
				errors.rejectValue("dni", "obligatorio", "El usuario ya exite");
			
			
			
	}
	}
	}
	
	

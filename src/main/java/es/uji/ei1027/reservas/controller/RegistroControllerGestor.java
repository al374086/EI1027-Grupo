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


import es.uji.ei1027.reservas.dao.MunicipalManagerDao;
import es.uji.ei1027.reservas.modelo.Citizen;
import es.uji.ei1027.reservas.modelo.MunicipalManager;
import es.uji.ei1027.reservas.modelo.Usuario;


@Controller
@RequestMapping("/user")
public class RegistroControllerGestor {
	
	

	private MunicipalManagerDao municipalmanagerDao;

	@Autowired
	public void setMunicipalManagerDao(MunicipalManagerDao municipalmanagerDao) {
		this.municipalmanagerDao=municipalmanagerDao;
	}

	@RequestMapping(value = "/addRegistroGestor")
	public String addMunicipalManager(HttpSession session, Model model) {
		if (session.getAttribute("user") != null) 
	       { 
	          return "redirect:/user/login";
	       }
	      
		model.addAttribute("municipalmanager", new MunicipalManager());
		return "user/addRegistroGestor";
	}

	@RequestMapping(value = "/addRegistroGestor", method = RequestMethod.POST)
	public String processAddSubmit(HttpSession session, @ModelAttribute("municipalmanager") MunicipalManager municipalmanager, BindingResult bindingResult) {
		
		MunicipalValidator municipalValidator = new MunicipalValidator(); 
		municipalValidator.validate(municipalmanager, bindingResult); 
		
		if (bindingResult.hasErrors())
			return "user/addRegistroGestor";
		
		if (session.getAttribute("user") == null) 
	       { 
	          return "redirect:/user/login";
	       }
		
		municipalmanagerDao.addMunicipalManager(municipalmanager);
		
		return "redirect:/municipalmanager/list";
	}
	
	
	
	
	
	   @RequestMapping(value="/updateGestor/{dni}", method = RequestMethod.GET)
		public String editMunicipalManager(Model model, @PathVariable String dni) {
		   
		   MunicipalManager gestor = municipalmanagerDao.getMunicipalManager(dni);
		   gestor.setPassword("");
			model.addAttribute("municipalmanager", gestor);
			
			
			return "/user/updateGestor"; 
		}
	   
	   
	   
	   
	   @RequestMapping(value="/updateGestor", method = RequestMethod.POST) 
		public String processUpdateSubmit(
	                           @ModelAttribute("municipalmanager") MunicipalManager municipalManager, 
	                           BindingResult bindingResult) {
			 if (bindingResult.hasErrors()) 
				 return "/user/updateGestor";
			 
			 municipalmanagerDao.updateMunicipalManager(municipalManager);
			 return "redirect:/municipalmanager/list"; 
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	class MunicipalValidator implements Validator { 
		@Override
		public boolean supports(Class<?> cls) { 
			return Usuario.class.isAssignableFrom(cls);
		}
		
		@Override 
		public void validate(Object obj, Errors errors) {
		  
			 MunicipalManager municipalmanager = ( MunicipalManager) obj;
			
			if (municipalmanagerDao.getMunicipalManager(municipalmanager.getDni())!=null)
				errors.rejectValue("dni", "obligatorio", "El gestor ya exite");
			
			
			
	}
	}
	}
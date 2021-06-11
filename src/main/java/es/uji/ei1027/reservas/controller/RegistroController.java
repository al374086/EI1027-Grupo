package es.uji.ei1027.reservas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.reservas.dao.CitizenDao;
import es.uji.ei1027.reservas.modelo.Citizen;

@Controller
@RequestMapping("/user")
public class RegistroController {
	
	
/*
	private CitizenDao citizenDao;

	@Autowired
	public void setCitizenDao(CitizenDao citizenDao) {
		this.citizenDao = citizenDao;
	}

	@RequestMapping("/login")
	public String loginCitizens(Model model) {
		model.addAttribute("citizens", citizenDao.getCitizens());
		return "user/login";
	}

	@RequestMapping(value = "/addRegistro")
	public String addCitizen(Model model) {
		model.addAttribute("citizen", new Citizen());
		return "user/addRegistro";
	}

	@RequestMapping(value = "/addRegistro", method = RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("citizen") Citizen citizen, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "user/addRegistro";
		citizenDao.addCitizen(citizen);
		return "redirect:login";
	}
*/
}
package es.uji.ei1027.reservas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.uji.ei1027.reservas.dao.MunicipalityDao;
import es.uji.ei1027.reservas.modelo.Municipality;

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
	 public String addMunicipality(Model model) {
			model.addAttribute("municipality", new Municipality());
			return "municipality/add";
	 }
	 @RequestMapping("/list") 
	 public String listMunicipality(Model model) {
		 model.addAttribute("municipality", municipalityDao.getMunicipality());
		 return "municipality/list";
	 }
	 
}

package es.uji.ei1027.clubesportiu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import es.uji.ei1027.clubesportiu.dao.PlanDao;

import es.uji.ei1027.clubesportiu.modelo.Plan;

@Controller
@RequestMapping("/plan")

public class PlanController {
	
	private PlanDao planDao;
	
	 @Autowired
	   public void setNadadorDao(PlanDao planDao) {
	       this.planDao=planDao;
	   }
	 
	// Operacions: Crear, llistar, actualitzar, esborrar
	   // ...
	 
	 
	 @RequestMapping(value="/add") 
		public String addNadador(Model model) {
			model.addAttribute("nadador", new Plan());
			return "plan/add";
		}
	 

}

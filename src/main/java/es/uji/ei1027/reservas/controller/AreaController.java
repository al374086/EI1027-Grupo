package es.uji.ei1027.reservas.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.reservas.dao.AreaDao;
import es.uji.ei1027.reservas.dao.ServiceDao;
import es.uji.ei1027.reservas.modelo.Area;
import es.uji.ei1027.reservas.modelo.Municipality;

@Controller
@RequestMapping("/area")

public class AreaController {

	private AreaDao areaDao;
	
	 @Autowired
	 public void setAreaDao(AreaDao areaDao) {
	       this.areaDao=areaDao;
	 }
	 
	 // Operacions: Crear, llistar, actualitzar, esborrar
	 @RequestMapping(value="/add") 
	 public String addArea(Model model) {
			model.addAttribute("area", new Area());
			//System.out.println("add en el controller");
			return "area/add";
	 }
	 
	 @RequestMapping(value="/add", method=RequestMethod.POST)
	 public String processAddSubmit(@ModelAttribute("area") Area area,
	                                   BindingResult bindingResult) {
	   	 if (bindingResult.hasErrors()) 
	   			return "area/add";
	   	 areaDao.addArea(area);
	   	 return "redirect:list";
	 }
	 
	 @RequestMapping("/list") 
	 public String listArea(Model model) {
		 model.addAttribute("area", areaDao.getAreas());
		 return "area/list";
	 }
	 
	 @RequestMapping(value="/update/{name}", method = RequestMethod.GET)
	 public String editArea(Model model, @PathVariable String name) {
			model.addAttribute("area", areaDao.getArea(name));
			return "area/update"; 
	 }
	   
	 @RequestMapping(value="/update", method = RequestMethod.POST) 
	 public String processUpdateSubmit(
	               @ModelAttribute("area") Area area, 
	              BindingResult bindingResult) {
			 if (bindingResult.hasErrors()) 
				 return "area/update";
			 areaDao.updateArea(area);
			 return "redirect:list"; 
		}
	 
	 @RequestMapping(value="/updateservei/{name}", method = RequestMethod.GET)
	 public String editAreaservei(Model model, @PathVariable String name) {
			model.addAttribute("area", areaDao.getArea(name));
			//model.addAttribute("servei", serviceDao.getServices());
			List<String> serveiList = Arrays.asList("Restaurante", "Windsurf");
			model.addAttribute("serveiList", serveiList);
			return "area/updateservei"; 
	 }
}

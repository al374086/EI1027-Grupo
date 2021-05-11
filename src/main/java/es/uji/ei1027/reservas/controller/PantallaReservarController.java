package es.uji.ei1027.reservas.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.reservas.services.PantallaReservarService;
@Controller
@RequestMapping("/pantallaReservar")
public class PantallaReservarController {
	
	private PantallaReservarService reservasService;

	   @Autowired 
	   public void setSociDao(PantallaReservarService reservasService) {
	       this.reservasService = reservasService;
	   }

	@RequestMapping(value="/seleccionarArea", method=RequestMethod.GET)
	public String getLocalidades(Model model) {
		model.addAttribute("provinciasList", reservasService.getProvincias());
		model.addAttribute("localidadesList", reservasService.getLocalidades());
		model.addAttribute("area", reservasService.getAreas());
		return "pantallaReservar/seleccionarArea"; 
	}
}

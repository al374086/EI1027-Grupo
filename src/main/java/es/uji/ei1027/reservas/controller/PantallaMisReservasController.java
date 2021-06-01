package es.uji.ei1027.reservas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.reservas.modelo.FormularioReservarArea;
import es.uji.ei1027.reservas.services.PantallaMisReservasService;
import es.uji.ei1027.reservas.services.PantallaReservarService;

@Controller
@RequestMapping("/pantallaMisReservas")
public class PantallaMisReservasController {
	
	private PantallaMisReservasService misReservasService;
	
	@Autowired 
	public void setSociDao(PantallaMisReservasService misReservasService) {
		this.misReservasService = misReservasService;
	}
	
	@RequestMapping(value="/misReservas", method=RequestMethod.GET)
	public String getLocalidades(Model model) {
		//model.addAttribute("provinciasList", reservasService.getProvincias());
		//model.addAttribute("localidadesList", reservasService.getLocalidades());
		//model.addAttribute("localidadesList", null);
		//model.addAttribute("area", reservasService.getAreas());
		//model.addAttribute("area", null);
		//model.addAttribute("formulario", new FormularioReservarArea());
		return "pantallaMisReservas/misReservas"; 
	}

}

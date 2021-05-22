package es.uji.ei1027.reservas.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.reservas.modelo.FormularioReservarArea;
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
		//model.addAttribute("localidadesList", reservasService.getLocalidades());
		model.addAttribute("localidadesList", null);
		//model.addAttribute("area", reservasService.getAreas());
		model.addAttribute("area", null);
		model.addAttribute("formulario", new FormularioReservarArea());
		//LocalDate fecha1 = new LocalDate();
		return "pantallaReservar/seleccionarArea"; 
	}
	
	@RequestMapping(value="/seleccionarArea", method=RequestMethod.POST)
	public String getDatos(Model model,@ModelAttribute("formulario") FormularioReservarArea datos, 
            BindingResult bindingResult) {
	//	if(datos.getProvincia() == null) {
	//		model.addAttribute("provinciasList", reservasService.getProvincias());
	//		return "pantallaReservar/seleccionarArea";
	//	}
		if(datos.getLocalidad() == null) {
			model.addAttribute("provinciasList", datos.getProvincia());
			model.addAttribute("localidadesList", reservasService.getLocalidades(datos.getProvincia()));
			return "pantallaReservar/seleccionarArea";
		}
		else if (datos.getArea() == null) {
			model.addAttribute("provinciasList", datos.getProvincia());
			model.addAttribute("localidadesList", datos.getLocalidad());
			model.addAttribute("area", reservasService.getAreas(datos.getLocalidad()));
			return "pantallaReservar/seleccionarArea";
		}
		else {
			//Ir a nueva pantalla reservar
		}
		
		return "pantallaReservar/seleccionarArea"; 
	}
	
	@RequestMapping(value="/reservar/{area}", method=RequestMethod.GET)
	public String reservarArea(Model model, @PathVariable String area) {
		model.addAttribute("formulario", new FormularioReservarArea());
		model.addAttribute("area", area);
		return "pantallaReservar/reservar"; 
	}
}

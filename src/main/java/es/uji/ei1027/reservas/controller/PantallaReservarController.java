package es.uji.ei1027.reservas.controller;

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

import es.uji.ei1027.reservas.modelo.FormularioSeleccionarLocalidad;
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
		model.addAttribute("formulario", new FormularioSeleccionarLocalidad());
		return "pantallaReservar/seleccionarArea"; 
	}
	
	@RequestMapping(value="/seleccionarArea", method=RequestMethod.POST)
	public String getDatos(Model model,@ModelAttribute("formulario") FormularioSeleccionarLocalidad datos, 
            BindingResult bindingResult) {
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
			System.out.println(datos.getProvincia());
			System.out.println(datos.getLocalidad());
			System.out.println(datos.getArea());
		}
		
		//System.out.println(datos.getArea());
		//System.out.println(provincia);
		//model.addAttribute("provinciasList", provincia);
		//model.addAttribute("localidadesList", reservasService.getLocalidades(provincia));
		//model.addAttribute("localidadesList", null);
		//model.addAttribute("area", reservasService.getAreas());
		//model.addAttribute("area", null);
		return "pantallaReservar/seleccionarArea"; 
	}
}

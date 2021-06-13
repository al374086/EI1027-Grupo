package es.uji.ei1027.reservas.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.reservas.modelo.FormularioReservarArea;
import es.uji.ei1027.reservas.services.GestorService;

@Controller
@RequestMapping("/gestor")
public class GestorController {
	
	private GestorService gestorService;

	   @Autowired 
	   public void setSociDao(GestorService gestorService) {
	       this.gestorService = gestorService;
	   }
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(HttpSession session, Model model) {
		return "redirect:/gestor/ocupacion"; 
	}
	
	@RequestMapping(value="/ocupacion", method=RequestMethod.GET)
	public String getLocalidades(Model model) {
		model.addAttribute("provinciasList", gestorService.getProvincias());
		model.addAttribute("formulario", new FormularioReservarArea());
		return "/gestor/ocupacion"; 
	}
	
	@RequestMapping(value="/ocupacion", method=RequestMethod.POST)
	public String getDatos(Model model,@ModelAttribute("formulario") FormularioReservarArea datos, 
            BindingResult bindingResult) {
		if(datos.getLocalidad() == null) {
			model.addAttribute("provinciasList", datos.getProvincia());
			model.addAttribute("localidadesList", gestorService.getLocalidades(datos.getProvincia()));
			return "/gestor/ocupacion";
		}
		else if (datos.getArea() == null) {
			model.addAttribute("provinciasList", datos.getProvincia());
			model.addAttribute("localidadesList", datos.getLocalidad());
			model.addAttribute("ocupado", gestorService.getNivelOcupacion(datos.getLocalidad()));
			return "/gestor/ocupacion";
		}
		else {
			//Ir a nueva pantalla reservar
		}
		
		return "/gestor/ocupacion"; 
	}

}

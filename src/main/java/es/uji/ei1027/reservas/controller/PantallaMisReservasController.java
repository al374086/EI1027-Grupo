package es.uji.ei1027.reservas.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.reservas.modelo.FormularioReservarArea;
import es.uji.ei1027.reservas.modelo.Usuario;
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
	public String getMisReservas(HttpSession session, Model model) {
		if (session.getAttribute("user") == null) 
	       { 
	          model.addAttribute("user", new Usuario());
	          session.setAttribute("nextUrl", "/pantallaMisReservas/misReservas");
	          return "/user/login";
	       } 
		Usuario user = (Usuario) session.getAttribute("user");
		model.addAttribute("misReservas", misReservasService.getMisReservas(user.getUsername()));
		return "pantallaMisReservas/misReservas"; 
	}
	
	@RequestMapping(value="/misReservas/cancelar/{id}", method=RequestMethod.GET)
	public String cancelarReserva(HttpSession session, Model model, @PathVariable int id) {
		if (session.getAttribute("user") == null) 
	       { 
	          model.addAttribute("user", new Usuario());
	          session.setAttribute("nextUrl", "/pantallaMisReservas/misReservas");
	          return "/user/login";
	       }
		model.addAttribute("misReservas", misReservasService.getMisReservas("73404595",id));
		return "pantallaMisReservas/cancelarReserva"; 
	}
	
	@RequestMapping(value="/misReservas/eliminar/{id}", method=RequestMethod.GET)
	public String eliminarReserva(HttpSession session, Model model, @PathVariable int id) {
		if (session.getAttribute("user") == null) 
	       { 
	          model.addAttribute("user", new Usuario());
	          session.setAttribute("nextUrl", "/pantallaMisReservas/misReservas");
	          return "/user/login";
	       }
		misReservasService.cancelarReserva("73404595", id);
		model.addAttribute("misReservas", misReservasService.getMisReservas("73404595",id));
		return "pantallaMisReservas/misReservas"; 
	}

}

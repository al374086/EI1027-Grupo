package es.uji.ei1027.reservas.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.reservas.modelo.Usuario;

@Controller
@RequestMapping("/gestor")
public class GestorController {
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String getMisReservas(HttpSession session, Model model) {
		return "gestor/index"; 
	}

}

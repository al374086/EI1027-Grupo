package es.uji.ei1027.reservas.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/gestormunicipal")
public class GestorMunicipalController {
	
	@RequestMapping(value="/index")
	public String index(HttpSession session, Model model) {
		return "/gestormunicipal/index"; 
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String listaReservas(HttpSession session, Model model) {
		return "/gestor/list"; 
	}
}

package es.uji.ei1027.reservas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/faq")
public class FAQController {

	@RequestMapping(value="/faq", method = RequestMethod.GET)
	   public String listCitizens(Model model) {
	      return "/faq";
	   }
	
}

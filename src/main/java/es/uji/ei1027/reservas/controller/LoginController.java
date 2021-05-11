package es.uji.ei1027.reservas.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.reservas.dao.UserDao;
import es.uji.ei1027.reservas.modelo.Usuario;

class UserValidator implements Validator { 
	@Override
	public boolean supports(Class<?> cls) { 
		return Usuario.class.isAssignableFrom(cls);
	}
	
	@Override 
	public void validate(Object obj, Errors errors) {
	  // Exercici: Afegeix codi per comprovar que 
         // l'usuari i la contrasenya no estiguen buits 
         // ...
		Usuario usuario = (Usuario) obj;
		
		if (usuario.getUsername().trim().equals(""))
			errors.rejectValue("username", "obligatori", "Cal introduir un usuari");
		
		
		if (usuario.getPassword().trim().equals(""))
			errors.rejectValue("password", "obligatori", "Cal introduir un password");
	}
}

@Controller
public class LoginController {
	@Autowired
	private UserDao userDao;

	@RequestMapping("user/login")
	public String login(Model model) {
		
		model.addAttribute("user", new Usuario());
		return "user/login";
	}

	@RequestMapping(value="user/login", method=RequestMethod.POST)
	public String checkLogin(@ModelAttribute("user") Usuario user,  		
				BindingResult bindingResult, HttpSession session) {
		
		UserValidator userValidator = new UserValidator(); 
		userValidator.validate(user, bindingResult); 
		
		if (bindingResult.hasErrors()) {
			
			return "user/login";
		}
	       // Comprova que el login siga correcte 
		// intentant carregar les dades de l'usuari 
		user = userDao.loadUserByUsername(user.getUsername(), user.getPassword()); 
		if (user == null) {
			bindingResult.rejectValue("password", "badpw", "Contrasenya incorrecta"); 
			return "user/login";
		}
		
		// Autenticats correctament. 
		// Guardem les dades de l'usuari autenticat a la sessió
		session.setAttribute("user", user); 
		
		// Torna a nextUrl
		if (session.getAttribute("nextUrl") == "/user/list") {
			String nextUrl = (String) session.getAttribute("nextUrl");
			session.removeAttribute("nextUrl");
			System.out.println("login correcto");
			return "redirect:/user/list" ;
		}
		
		// Torna a la pàgina principal
		return "redirect:/user/list";
	}

	@RequestMapping("/logout") 
	public String logout(HttpSession session) {
		session.invalidate(); 
		return "redirect:/";
	}
}

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

import es.uji.ei1027.reservas.dao.CitizenDao;
import es.uji.ei1027.reservas.dao.MunicipalManagerDao;
import es.uji.ei1027.reservas.dao.UserDao;
import es.uji.ei1027.reservas.modelo.Citizen;
import es.uji.ei1027.reservas.modelo.MunicipalManager;
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
			errors.rejectValue("username", "obligatorio", "Hay que introducir un usuario");
		
		
		if (usuario.getPassword().trim().equals(""))
			errors.rejectValue("password", "obligatorio", "Hay que introducir un password");
	}
}

@Controller
public class LoginController {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CitizenDao citizenDao;

	@RequestMapping({"user/login"})
	public String login(HttpSession session, Model model) {
		//Si esta logeado va a su lugar
		if (session.getAttribute("user") != null) {
			Usuario user = (Usuario) session.getAttribute("user");
			if(user.getRol().equals("ciudadanos")) {
				return "redirect:/pantallaMisReservas/misReservas";
			}else {
				return "MunicipalManager/list";
			
			}
		}
		
		model.addAttribute("user", new Usuario());
		return "user/login";
	}

	@RequestMapping(value={"user/login"}, method=RequestMethod.POST)
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
			System.out.println("el usuario es nulo");
			bindingResult.rejectValue("password", "badpw", "Contrasenya incorrecta"); 
			return"user/login";
		}
		
		// Autenticats correctament. 
		// Guardem les dades de l'usuari autenticat a la sessió
		session.setAttribute("user", user); 
		
		Citizen ciudadano;
		MunicipalManager municipalManager;
		
		//Tornar a nextUrl
		if (session.getAttribute("nextUrl") != null) {
			String nextUrl = (String) session.getAttribute("nextUrl");
			session.removeAttribute("nextUrl");
			return "redirect:" + nextUrl;
		}
		
		if(user.getRol().equals("ciudadanos")) {
			
			//if(user.getUsername().equals("generalitat") && user.)
			
			return "redirect:/pantallaMisReservas/misReservas";
		
		}else {
			
	
			return "MunicipalManager/list";
		
		}
		
		
	}
	
	
	@RequestMapping({"/user/faq"})
	public String faq(HttpSession session, Model model) {
		return "/user/faq";
	}
	
	
	@RequestMapping({"/user/miPerfil"})
	public String miPerfil(HttpSession session, Model model) {
		if (session.getAttribute("user") == null) 
	       { 
	          model.addAttribute("user", new Usuario());
	          session.setAttribute("nextUrl", "/user/miPerfil");
	          return "/user/login";
	       }
		model.addAttribute("citizen", citizenDao.getCitizen(((Usuario) session.getAttribute("user")).getUsername()));
		return "user/miPerfil";
	}
	
	
	
	
	
	
	
	/*
	@RequestMapping(value={"reserve/add"}, method=RequestMethod.POST)
	public String checkLoginCitizen(@ModelAttribute("user") Usuario user,  		
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
		
		Citizen ciudadano;
		MunicipalManager municipalManager;
		
		if(user.getRol().equals("ciudadanos")) {
			System.out.println("voy a la pagina reservas");
			return "reserve/add";
		
		}else {
			System.out.println("voy a la pagina del gestor municipal");
	
			return "municipality/add";
		
		}
		
		
	}
	
	
	
	@RequestMapping(value={"municipality/add"}, method=RequestMethod.POST)
	public String checkLoginMunicipality(@ModelAttribute("user") Usuario user,  		
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
		
		Citizen ciudadano;
		MunicipalManager municipalManager;
		
		if(user.getRol().equals("ciudadanos")) {
			System.out.println("voy a la pagina reservas");
			return "reserve/add";
		
		}else {
			System.out.println("voy a la pagina del gestor municipal");
	
			return "municipality/add";
		
		}
		
		
	}
	
	
	
	
	
	
	*/
	
	
	
	
	
	@RequestMapping("/logout") 
	public String logout(HttpSession session) {
		session.invalidate(); 
		return "redirect:/";
	}
}

package es.uji.ei1027.reservas.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.uji.ei1027.reservas.dao.UserDao;
import es.uji.ei1027.reservas.modelo.Usuario;

@Controller 
@RequestMapping("/user")

public class UsuarioControladorPublico {
	
	   private UserDao userDao;

	   @Autowired 
	   public void setSociDao(UserDao userDao) {
	       this.userDao = userDao;
	   }
	  
	   @RequestMapping("/list") 
	   public String listSocis(HttpSession session, Model model) {
	       if (session.getAttribute("user") == null) 
	       { 
	          model.addAttribute("user", new Usuario()); 
	          session.setAttribute("nextUrl", "/user/list");
	          return "login";
	       } 
	       model.addAttribute("users", userDao.listAllUsers());
	       return "user/list";
	   }
	


}

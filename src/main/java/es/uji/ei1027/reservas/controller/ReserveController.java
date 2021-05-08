package es.uji.ei1027.reservas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.reservas.dao.ReserveDao;
import es.uji.ei1027.reservas.modelo.Reserve;

@Controller
@RequestMapping("/reserve")
public class ReserveController {

   private ReserveDao reserveDao;

   @Autowired
   public void setReserveDao(ReserveDao reserveDao) {
       this.reserveDao=reserveDao;
   }

   // Operacions: Crear, llistar, actualitzar, esborrar
   
   @RequestMapping("/list")
   public String listReserves(Model model) {
      model.addAttribute("reserves", reserveDao.getReserves());
      return "reserve/list";
   }
   
   @RequestMapping(value="/add") 
	public String addReserve(Model model) {
		model.addAttribute("reserve", new Reserve());
		return "reserve/add";
	}
   
   @RequestMapping(value="/add", method=RequestMethod.POST)
   public String processAddSubmit(@ModelAttribute("reserve") Reserve reserve,
                                   BindingResult bindingResult) { 
   	 if (bindingResult.hasErrors())
   			return "reserve/add";
   	 reserveDao.addReserve(reserve);
   	 return "redirect:list";
    } 
   
   @RequestMapping(value="/update/{numberOfReserve}", method = RequestMethod.GET)
	public String editReserve(Model model, @PathVariable int numberOfReserve) {
		model.addAttribute("reserve", reserveDao.getReserve(numberOfReserve));
		return "reserve/update"; 
	}
   
   @RequestMapping(value="/update", method = RequestMethod.POST) 
	public String processUpdateSubmit(
                           @ModelAttribute("reserve") Reserve reserve, 
                           BindingResult bindingResult) {
		 if (bindingResult.hasErrors()) 
			 return "reserve/update";
		 reserveDao.updateReserve(reserve);
		 return "redirect:list"; 
	}
   
   @RequestMapping(value="/delete/{numberOfReserve}")
	public String processDelete(@PathVariable int numberOfReserve) {
	      reserveDao.deleteReserve(numberOfReserve);
          return "redirect:../list"; 
	}
}
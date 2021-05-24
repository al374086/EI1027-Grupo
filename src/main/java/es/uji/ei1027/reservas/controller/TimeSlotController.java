package es.uji.ei1027.reservas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.uji.ei1027.reservas.dao.TimeSlotDao;
import es.uji.ei1027.reservas.modelo.TimeSlot;

@Controller
@RequestMapping("/timeslot")
public class TimeSlotController {
	
	private TimeSlotDao timeslotDao;

	   @Autowired
	   public void setTimeSlotDao(TimeSlotDao timeslotDao) {
	       this.timeslotDao=timeslotDao;
	   }

	   // Operacions: Crear, llistar, actualitzar, esborrar
	   
	   @RequestMapping("/list")
	   public String listTimeSlots(Model model) {
	      model.addAttribute("timeslot", timeslotDao.getTimeSlots());
	      //System.out.println("Dentro del dao list timeslot");
	      return "timeslot/list";
	   }

	   @RequestMapping(value="/add") 
		public String addTimeSlot(Model model) {
			model.addAttribute("timeslot", new TimeSlot());
			return "timeslot/add";
	   }
}

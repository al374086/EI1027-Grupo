package es.uji.ei1027.reservas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.reservas.dao.PlanAsignadoDao;
import es.uji.ei1027.reservas.dao.TemporalServiceDao;
import es.uji.ei1027.reservas.modelo.PlanAsignado;
import es.uji.ei1027.reservas.modelo.TemporalService;

@Controller
@RequestMapping("/planasignado")

public class PlanAsignadoController {

	private PlanAsignadoDao planasignadoDao;
	
	 @Autowired
	   public void setPlanAsignado(PlanAsignadoDao planasignadoDao) {
	       this.planasignadoDao=planasignadoDao;
	   }
	 
	// Operacions: Crear, llistar, actualitzar, esborrar
	   // ...
	 
	 @RequestMapping("/list")
	    public String listPlanesAsignados(Model model) {
	        model.addAttribute("planesasignados", planasignadoDao.getPlanesAsignados());
	        return "planasignado/list";
	    }
	 
	 @RequestMapping(value="/add") 
		public String addPlanAsignado(Model model) {
			model.addAttribute("planasignado", new PlanAsignado());
			return "planasignado/add";
		}
	 
	 
	 @RequestMapping(value="/add", method= RequestMethod.POST)
	    public String processAddSubmit(@ModelAttribute("planasignado") PlanAsignado planasignado,
	                                   BindingResult bindingResult) {
	        if (bindingResult.hasErrors())
	        	return "planasignado/add";
	       
	        return "redirect:list";
	 }
	 
	/* 
	 @RequestMapping(value="/update/{idservicio}/{idplan}", method = RequestMethod.GET)
	    public String editPlanAsignado(Model model, @PathVariable int idservicio, @PathVariable int idplan) {
	        model.addAttribute("planasignado", planasignadoDao.getPlanAsignado(idplan, idservicio));
	        return "planasignado/update";
	    }

	    @RequestMapping(value="/update", method = RequestMethod.POST)
	    public String processUpdateSubmit(
	            @ModelAttribute("planasignado") PlanAsignado planasignado,
	            BindingResult bindingResult) {
	        if (bindingResult.hasErrors())
	            return "planasignado/update";
	        planasignadoDao.updatePlan(planasignado));
	        return "redirect:list";
	    }
	    
	   */ 
	    
	    @RequestMapping(value="/delete/{idservicio}/{idplan}")
	    public String processDelete(@PathVariable int idservicio, @PathVariable int idplan ) {
	    	planasignadoDao.deletePlanAsignado(idservicio, idplan);
	        return "redirect:../../list";
	    }
	 
	 

}

package es.uji.ei1027.reservas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import es.uji.ei1027.reservas.dao.PlanDao;
import es.uji.ei1027.reservas.modelo.Plan;

@Controller
@RequestMapping("/plan")

public class PlanController {
	
	private PlanDao planDao;
	
	 @Autowired
	   public void setPlanDao(PlanDao planDao) {
	       this.planDao=planDao;
	   }
	 
	// Operacions: Crear, llistar, actualitzar, esborrar
	   // ...
	 
	 
	  @RequestMapping("/list")
	    public String listPlanes(Model model) {
	        model.addAttribute("planes", planDao.getPlans());
	        return "plan/list";
	    }
	  
	  
	 @RequestMapping(value="/add") 
		public String addPlan(Model model) {
			model.addAttribute("plan", new Plan());
			return "plan/add";
		}
	 
	 
	 
	 @RequestMapping(value="/add", method= RequestMethod.POST)
	    public String processAddSubmit(@ModelAttribute("plan") Plan plan,
	                                   BindingResult bindingResult) {
	        if (bindingResult.hasErrors())
	        	return "plan/add";
	       /*try {	        	
	        planDao.addPlan(plan);
	        } catch (DuplicateKeyException e) { 
	        	throw new ClubesportiuException(
	        			"Ja existeix un plan d'aquest area en " 
	        	                 +plan.getIdplan(), "CPduplicada"); 
	        }catch (DataAccessException e) { 
	            throw new ClubesportiuException(  
	                    "Error en l'accés a la base de dades", "ErrorAccedintDades"); 
	          }*/
	        
	        return "redirect:list";
	 }
	 
	 
	 
	 @RequestMapping(value="/update/{idplan}/{name_area}", method = RequestMethod.GET)
	    public String editPlan(Model model, @PathVariable int idplan,@PathVariable String name_area) {
	        model.addAttribute("plan", planDao.getPlan(idplan, name_area));
	        return "plan/update";
	    }

	    @RequestMapping(value="/update", method = RequestMethod.POST)
	    public String processUpdateSubmit(
	            @ModelAttribute("plan") Plan plan,
	            BindingResult bindingResult) {
	        if (bindingResult.hasErrors())
	            return "plan/update";
	        planDao.updatePlan(plan);
	        return "redirect:list";
	    }
	    

	    @RequestMapping(value="/delete/{idplan}/{name_area}")
	    public String processDelete(@PathVariable int idplan,@PathVariable String name_area) {
	        planDao.deletePlan(idplan, name_area);
	        return "redirect:../../list";
	    }
	 
	 
	    
	 

}

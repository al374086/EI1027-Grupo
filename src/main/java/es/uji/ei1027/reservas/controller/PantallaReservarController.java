package es.uji.ei1027.reservas.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.reservas.modelo.FormularioReservarArea;
import es.uji.ei1027.reservas.modelo.Usuario;
import es.uji.ei1027.reservas.modelo.Zone;
import es.uji.ei1027.reservas.services.PantallaReservarService;
@Controller
@RequestMapping("/pantallaReservar")
public class PantallaReservarController {
	
	private PantallaReservarService reservasService;

	   @Autowired 
	   public void setSociDao(PantallaReservarService reservasService) {
	       this.reservasService = reservasService;
	   }

	@RequestMapping(value="/seleccionarArea", method=RequestMethod.GET)
	public String getLocalidades(Model model) {
		model.addAttribute("provinciasList", reservasService.getProvincias());
		//model.addAttribute("localidadesList", reservasService.getLocalidades());
		model.addAttribute("localidadesList", null);
		//model.addAttribute("area", reservasService.getAreas());
		model.addAttribute("area", null);
		model.addAttribute("formulario", new FormularioReservarArea());
		return "pantallaReservar/seleccionarArea"; 
	}
	
	@RequestMapping(value="/seleccionarArea", method=RequestMethod.POST)
	public String getDatos(Model model,@ModelAttribute("formulario") FormularioReservarArea datos, 
            BindingResult bindingResult) {
	//	if(datos.getProvincia() == null) {
	//		model.addAttribute("provinciasList", reservasService.getProvincias());
	//		return "pantallaReservar/seleccionarArea";
	//	}
		if(datos.getLocalidad() == null) {
			model.addAttribute("provinciasList", datos.getProvincia());
			model.addAttribute("localidadesList", reservasService.getLocalidades(datos.getProvincia()));
			return "pantallaReservar/seleccionarArea";
		}
		else if (datos.getArea() == null) {
			model.addAttribute("provinciasList", datos.getProvincia());
			model.addAttribute("localidadesList", datos.getLocalidad());
			model.addAttribute("areas", reservasService.getAreasv2(datos.getLocalidad()));
			return "pantallaReservar/seleccionarArea";
		}
		else {
			//Ir a nueva pantalla reservar
		}
		
		return "pantallaReservar/seleccionarArea"; 
	}
	
	@RequestMapping(value="/reservar/{area}", method=RequestMethod.GET)
	public String reservarArea(Model model, @PathVariable String area) {
		FormularioReservarArea formulario = new FormularioReservarArea();
		formulario.setArea(area);
		model.addAttribute("formulario", formulario);
		model.addAttribute("areas", (area));
		LocalDate fecha = LocalDate.now();
		List<LocalDate> fechaList = new ArrayList<LocalDate>();
		fechaList.add(fecha);
		fechaList.add(fecha.plusDays(1));
		fechaList.add(fecha.plusDays(2));
		model.addAttribute("fechaList",fechaList);
		model.addAttribute("timeSlotList",reservasService.getTimeSlots(area));
		model.addAttribute("imagen",reservasService.getImagen(area));
		return "pantallaReservar/reservar"; 
	}
	
	@RequestMapping(value="/reservar", method=RequestMethod.POST)
	public String getReservarArea(Model model,@ModelAttribute("formulario") FormularioReservarArea datos, 
            BindingResult bindingResult) {
		//System.out.println(datos.getTime().split("nameArea=")[1].split("]")[0]);
		//System.out.println(datos.getFecha());
		//System.out.println(datos.getTime().split("timeId=")[1].split(",")[0]);
		return "redirect:./reservar/" + datos.getTime().split("nameArea=")[1].split("]")[0] + "/" + datos.getFecha() + "/" + datos.getTime().split("timeId=")[1].split(",")[0]; 	
	}
	
	private void comunSeleccionarZonas(Model model, String area, String fecha, int timeId, List<String> seleccionado) {
		LocalDate localDate = LocalDate.parse(fecha);
		FormularioReservarArea formulario = new FormularioReservarArea();
		formulario.setArea(area);
		model.addAttribute("formulario", formulario);
		model.addAttribute("areas", (area));
		List<LocalDate> fechaList = new ArrayList<LocalDate>();
		fechaList.add(localDate);
		model.addAttribute("fechaList",fechaList);
		model.addAttribute("timeSlotList",reservasService.getTimeSlot(timeId));
		model.addAttribute("zoneList",reservasService.getZonasDisponibles(area, localDate, reservasService.getTimeSlot(timeId), seleccionado));
		
		model.addAttribute("seleccionado",reservasService.getZones(seleccionado, area));
		model.addAttribute("seleccionadoSoloLetra",seleccionado);
		
		if (seleccionado.size() > 0) {
			model.addAttribute("zoneSelected", true);
		}
		
		model.addAttribute("timeSelected", true);
		
		model.addAttribute("imagen",reservasService.getImagen(area));
	}
	
	@RequestMapping(value="/reservar/{area}/{fecha}/{timeId}", method=RequestMethod.GET)
	public String reservarArea(Model model, @PathVariable String area, @PathVariable String fecha, @PathVariable int timeId) {
		
		this.comunSeleccionarZonas(model, area, fecha, timeId, new ArrayList<String>());
		/*LocalDate localDate = LocalDate.parse(fecha);
		FormularioReservarArea formulario = new FormularioReservarArea();
		formulario.setArea(area);
		model.addAttribute("formulario", formulario);
		model.addAttribute("areas", (area));
		List<LocalDate> fechaList = new ArrayList<LocalDate>();
		fechaList.add(localDate);
		model.addAttribute("fechaList",fechaList);
		model.addAttribute("timeSlotList",reservasService.getTimeSlot(timeId));
		model.addAttribute("zoneList",reservasService.getZonasDisponibles(area, localDate, reservasService.getTimeSlot(timeId)));
		
		model.addAttribute("selected", true); */
		
		return "pantallaReservar/reservar"; 
	}
	
	@RequestMapping(value="/reservar/{area}/{fecha}/{timeId}/{letterAndNumber}", method=RequestMethod.GET)
	public String reservarAreaTest(Model model, @PathVariable String area, @PathVariable String fecha, @PathVariable int timeId, @PathVariable List<String> letterAndNumber) {
		this.comunSeleccionarZonas(model, area, fecha, timeId, letterAndNumber);
		//reservasService.reservar(area, localDate, timeId, letterAndNumber);
		return "pantallaReservar/reservar"; 
	}
	
	@RequestMapping(value="/reservar/{area}/{fecha}/{timeId}/{letterAndNumber}/{list}", method=RequestMethod.GET)
	public String reservarAreaTest(Model model, @PathVariable String area, @PathVariable String fecha, @PathVariable int timeId, @PathVariable String letterAndNumber, @PathVariable List<String> list) {
		if (list.contains(letterAndNumber))
			list.remove(letterAndNumber);
		else
			list.add(letterAndNumber);
		this.comunSeleccionarZonas(model, area, fecha, timeId, list);
		//reservasService.reservar(area, localDate, timeId, letterAndNumber);
		return "pantallaReservar/reservar"; 
	}
	
	@RequestMapping(value="/completar/{area}/{fecha}/{timeId}/{list}", method=RequestMethod.GET)
	public String reservarArea(HttpSession session, Model model, @PathVariable String area, @PathVariable String fecha, @PathVariable int timeId, @PathVariable List<String> list) {
		if (session.getAttribute("user") == null) 
	       { 
	          model.addAttribute("user", new Usuario());
	          session.setAttribute("nextUrl", "/pantallaReservar/reservar/" + area + "/" + fecha + "/" + timeId);
	          return "/user/login";
	       }
		LocalDate localDate = LocalDate.parse(fecha);
		reservasService.reservar(area, localDate, timeId, list, ((Usuario) session.getAttribute("user")).getUsername());
		return "redirect:/pantallaMisReservas/misReservas";
	}
}

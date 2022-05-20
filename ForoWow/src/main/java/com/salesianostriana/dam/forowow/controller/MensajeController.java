package com.salesianostriana.dam.forowow.controller;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.forowow.model.Hilo;
import com.salesianostriana.dam.forowow.model.Mensaje;
import com.salesianostriana.dam.forowow.security.Usuario;
import com.salesianostriana.dam.forowow.services.HiloService;
import com.salesianostriana.dam.forowow.services.MensajeService;
import com.salesianostriana.dam.forowow.services.UsuarioService;

@Controller
public class MensajeController {
	
	@Autowired
	private MensajeService mensajeService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private HiloService hiloService;
	
	
	@PostMapping ("/crear-mensaje")
	@RequestMapping(value="/crear-mensaje",method = RequestMethod.POST) 
	public String crearMensaje (
			@RequestParam(value="idHilo") String idHilo, 
			@RequestParam(value="mensaje") String mensaje,
			@RequestParam(value="tipoMensaje") String tipoMensaje, 
			@RequestParam(value="fechaCreacion") String fechaCreacion, 
			Model model) {
		fechaCreacion+=" 00:00:00";
		Optional<Usuario>user = this.usuarioService.obtenerUsuarioLogeado();
		Hilo hilo = this.hiloService.findById(Long.parseLong(idHilo)).get();
		Mensaje mensajeCreado = new Mensaje(0, mensaje, tipoMensaje, user.get().getUsername(), hilo, Timestamp.valueOf(fechaCreacion));
		this.mensajeService.save(mensajeCreado);
		hiloService.asignarLikeAHilo(hilo);
		return "redirect:/hilo/"+idHilo;
	}
	
	@PostMapping("/responder-hilo-formulario")
	@RequestMapping(value="/responder-hilo-formulario",method = RequestMethod.POST) 
	public String responderHiloFormulario(@RequestParam(value="idHilo") String idHilo, Model model) {
		model.addAttribute("idHilo", idHilo);
		return "formulariomensaje";
	}
	
	@PostMapping ("/eliminar-mensaje")
	@RequestMapping(value="/eliminar-mensaje",method = RequestMethod.POST) 
	public String eliminarMensaje (
			@RequestParam(value="idMensaje") String idMensaje, 
			@RequestParam(value="idHilo") String idHilo, 
			Model model) {
		Hilo hilo = this.hiloService.findById(Long.parseLong(idHilo)).get();
		Mensaje mensaje = this.mensajeService.findById(Long.parseLong(idMensaje)).get();
		List<Mensaje>listaMensajes = hilo.getMensajes();
		Iterator<Mensaje> itr = listaMensajes.iterator();
		 
	    while (itr.hasNext())
	    {
	        Mensaje t = itr.next();
	        if (t.getId()==Long.parseLong(idMensaje)) {
	            itr.remove();
	        }
	    }
	    hilo.setMensajes(listaMensajes);
	    this.hiloService.save(hilo);
		this.mensajeService.delete(mensaje);
		//return mensaje.toString();
		return "redirect:/hilo/"+idHilo;
	}
	
	@PostMapping ("/editar-mensaje-formulario")
	@RequestMapping(value="/editar-mensaje-formulario",method = RequestMethod.POST) 
	public String editarMensajeFormulario (
			@RequestParam(value="idMensaje") String idMensaje, 
			@RequestParam(value="idHilo") String idHilo, 
			Model model) {
		Mensaje mensaje = this.mensajeService.findById(Long.parseLong(idMensaje)).get();
		model.addAttribute("mensaje",mensaje);
		model.addAttribute("idHilo", idHilo);
		
		return "/formularioeditarmensaje";
	}
	
	@PostMapping ("/actualizar-mensaje")
	@RequestMapping(value="/actualizar-mensaje",method = RequestMethod.POST) 
	public String actualizarMensaje (
			@RequestParam(value="idMensaje") String idMensaje, 
			@RequestParam(value="idHilo") String idHilo,
			@RequestParam(value="contenido") String contenido,
			@RequestParam(value="tipoMensaje") String tipoMensaje, 
			@RequestParam(value="fechaCreacion") String fechaCreacion, 
			Model model) {
		Mensaje mensajeAntiguo = this.mensajeService.findById(Long.parseLong(idMensaje)).get();
		mensajeAntiguo.setContenido(contenido);
		fechaCreacion+=" 00:00:00";
		mensajeAntiguo.setFechaCreacion(Timestamp.valueOf(fechaCreacion));
		mensajeAntiguo.setTipoMensaje(tipoMensaje);
		this.mensajeService.edit(mensajeAntiguo);
		
		return "redirect:/hilo/"+idHilo;
	}

}
package com.salesianostriana.dam.forowow.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.forowow.model.Categoria;
import com.salesianostriana.dam.forowow.model.Hilo;
import com.salesianostriana.dam.forowow.model.Mensaje;
import com.salesianostriana.dam.forowow.model.MensajeUsuario;
import com.salesianostriana.dam.forowow.security.Usuario;
import com.salesianostriana.dam.forowow.services.CategoriaService;
import com.salesianostriana.dam.forowow.services.HiloService;
import com.salesianostriana.dam.forowow.services.MensajeService;
import com.salesianostriana.dam.forowow.services.UsuarioService;



@Controller
public class HiloController {
	
	@Autowired
	private HiloService hiloService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private MensajeService mensajeService;
	
	@PostMapping("/formulario-hilo")
    @RequestMapping(value="/formulario-hilo",method = RequestMethod.POST) 
	public String crearHilo (
			@RequestParam(value="idCategoria") String idCategoria, 
			Model model) {
		model.addAttribute("idCategoria", idCategoria);
		return "formulariohilo";
	}
	
	@GetMapping("/hilos")
	public String listarHilos (
			Model model) {
		List<Hilo>listaHilos = new ArrayList<>();
		listaHilos = this.hiloService.findAll();
		model.addAttribute("listaHilos", listaHilos);
		return "hilosadmin";
	}
	
	@GetMapping("/hilos-ordenar-fecha")
	public String ordenarHilosFecha(Model model) {
		List<Hilo>listaHilos = new ArrayList<>();
		listaHilos = this.hiloService.findAll();
		Collections.sort(listaHilos, Comparator.comparing(Hilo::getFechaCreacion));
		model.addAttribute("listaHilos", listaHilos);
		return "hilosadmin";
	}
	
	@PostMapping("/crear-hilo")
	@RequestMapping(value="/crear-hilo",method = RequestMethod.POST) 
	public String procesaFormulario(
			@RequestParam(value="idCategoria") String idCategoria, 
			@RequestParam(value="titulo") String titulo, 
			@RequestParam(value="mensaje") String mensaje,
			@RequestParam(value="tipoMensaje") String tipoMensaje, 
			@RequestParam(value="fechaCreacion") String fechaCreacion, 
			Model model) {
		Optional<Usuario>user = this.usuarioService.obtenerUsuarioLogeado();
		Categoria categoria = this.categoriaService.findById(Long.parseLong(idCategoria)).get();
		fechaCreacion+=" 00:00:00";
		List<Mensaje>listaMensajes = new ArrayList<>();
		Hilo hilo = new Hilo(
				0, 
				titulo,
				0, 
				tipoMensaje,
				mensaje, 
				Timestamp.valueOf(fechaCreacion), 
				user.get().getUsername(), 
				categoria, 
				listaMensajes
				);
	
		this.hiloService.save(hilo);
		Mensaje mens = new Mensaje(0, mensaje, hilo.getTipoHilo(), hilo.getCreador(), hilo, hilo.getFechaCreacion());
		this.mensajeService.save(mens);
		List<Mensaje>listamens = hilo.getMensajes();
		listamens.add(mens);
		hilo.setMensajes(listamens);
		hiloService.save(hilo);
		return "redirect:/categoria/"+idCategoria;
	}
	
	@GetMapping("/hilo/{id}")
	public String mostrarHilo(@PathVariable String id, Model model) {
		Hilo hilo = this.hiloService.findById(Long.parseLong(id)).get();
		List<Mensaje>listaMensajes = hilo.getMensajes();
		List<MensajeUsuario>listaMensajeUsuario = new ArrayList<>();
		for (Mensaje mensaje : listaMensajes) {
			Optional<Usuario> usuario = this.usuarioService.getUsuarioRepo().findUserByUsername(mensaje.getCreador());
			Usuario user = usuario.get();
			listaMensajeUsuario.add(new MensajeUsuario(user,mensaje));
		}
		model.addAttribute("listaMensajeUsuarios", listaMensajeUsuario);
		model.addAttribute("hilo", hilo);
	
		return "hilo";
	}
	
	@PostMapping ("/eliminar-hilo")
	@RequestMapping(value="/eliminar-hilo",method = RequestMethod.POST) 
	public String eliminarHilo (
			@RequestParam(value="idHilo") String idHilo, 
			Model model) {
		Hilo hilo = this.hiloService.findById(Long.parseLong(idHilo)).get();
		long idCategoria=hilo.getCategoria().getId();
		this.hiloService.delete(hilo);
		
		return "redirect:/categoria/"+idCategoria;
	}
	
	@PostMapping ("/editar-hilo-formulario")
	@RequestMapping(value="/editar-hilo-formulario",method = RequestMethod.POST) 
	public String editarHiloFormulario (
			@RequestParam(value="idHilo") String idHilo, 
			Model model) {
		Hilo hilo = this.hiloService.findById(Long.parseLong(idHilo)).get();
		Long idCategoria = hilo.getCategoria().getId();
		model.addAttribute("idCategoria", idCategoria);
		model.addAttribute("hilo", hilo);
		
		return "/formularioeditarhilo";
	}
	
	@PostMapping ("/actualizar-hilo")
	@RequestMapping(value="/actualizar-hilo",method = RequestMethod.POST) 
	public String actualizarMensaje (
			@RequestParam(value="idHilo") String idHilo,
			@RequestParam(value="idCategoria") String idCategoria, 
			@RequestParam(value="titulo") String titulo, 
			@RequestParam(value="contenido") String contenido,
			@RequestParam(value="tipoMensaje") String tipoMensaje, 
			@RequestParam(value="fechaCreacion") String fechaCreacion, 
			Model model) {
		Hilo hiloAntiguo = this.hiloService.findById(Long.parseLong(idHilo)).get();
		hiloAntiguo.setNombre(titulo);
		fechaCreacion+=" 00:00:00";
		hiloAntiguo.setFechaCreacion(Timestamp.valueOf(fechaCreacion));
		hiloAntiguo.setTipoHilo(tipoMensaje);
		hiloAntiguo.setContenido(contenido);
		this.hiloService.edit(hiloAntiguo);
		
		return "redirect:/categoria/"+idCategoria;
	}
	
	@PostMapping ("/dar-like")
	@RequestMapping(value="/dar-like",method = RequestMethod.POST) 
	public String darLike (
			@RequestParam(value="idHilo") String idHilo, 
			Model model) {
		Hilo hiloAntiguo = this.hiloService.findById(Long.parseLong(idHilo)).get();
		hiloAntiguo.setLikes(hiloAntiguo.getLikes()+1);
		Usuario usuario = this.usuarioService.obtenerUsuarioLogeado().get();
		if(usuario.getRango().toString().equalsIgnoreCase("rey")) {
			hiloAntiguo.setLikes(hiloAntiguo.getLikes()+5);
			this.hiloService.save(hiloAntiguo);
		}
		if(hiloAntiguo.getLikes()%3==0) {
			hiloAntiguo.setLikes(hiloAntiguo.getLikes()+3);
			this.hiloService.save(hiloAntiguo);
		}
		
		this.hiloService.edit(hiloAntiguo);
		long idCategoria=hiloAntiguo.getCategoria().getId();
		return "redirect:/categoria/"+idCategoria;
	}
}

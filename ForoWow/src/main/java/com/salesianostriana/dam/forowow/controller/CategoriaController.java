package com.salesianostriana.dam.forowow.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

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
import com.salesianostriana.dam.forowow.security.Usuario;

import com.salesianostriana.dam.forowow.services.CategoriaService;

import com.salesianostriana.dam.forowow.services.UsuarioService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor 
@AllArgsConstructor
@Controller
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public CategoriaController(CategoriaService categoriaService) {
		super();
		this.categoriaService = categoriaService;
	}

	@GetMapping({ "/index", "/" })
	public String mostrarPortada(Model model) {
		
		List<Categoria>listaCategorias = this.categoriaService.obtenerTodasLasCategorias();
		model.addAttribute("listaCategorias", listaCategorias);
		
		Optional<Usuario> user = this.usuarioService.obtenerUsuarioLogeado();
		if (user.isPresent() && !user.get().getRole().equals("ADMIN")) {
			model.addAttribute("rango", user.get().getRango().toString());
		}
		return "index";
	}
	
	@GetMapping("/categoria/{id}")
	public String listarHilos(@PathVariable String id, Model model, HttpServletRequest request) {
		Usuario usuarioAutenticado = this.usuarioService.obtenerUsuarioLogeado().get();
		Categoria categoria = this.categoriaService.findById(Long.parseLong(id)).get();
		if(categoria.getId()==1 && usuarioAutenticado.getRango().toString().equalsIgnoreCase(Usuario.Rango.vasallo.toString())) {
			return "redirect:/index";
		}
		List<Hilo>listaHilos = categoria.getHilos();
		List<Hilo>listaHilosNoDuplicados = listaHilos.stream()
		.distinct()
		.collect(Collectors.toList());
		model.addAttribute("hilos", listaHilosNoDuplicados);
		model.addAttribute("categoria", categoria);
		return "categoria";
	}
	
	@PostMapping("/formulario-categoria")
    @RequestMapping(value="/formulario-categoria",method = RequestMethod.POST) 
	public String crearCategoria ( 
			Model model) {
		return "formulariocategoria";
	}
	
	@PostMapping("/crear-categoria")
	@RequestMapping(value="/crear-categoria",method = RequestMethod.POST) 
	public String procesaFormularioCat( 
			@RequestParam(value="nombre") String nombre, 
			@RequestParam(value="descripcion") String descripcion,
			Model model) {
		List<Hilo>listaHilos = new ArrayList<>();
		Categoria categoria = new Categoria(0,nombre, descripcion, listaHilos);
		categoriaService.save(categoria);
		return "redirect:/index";
	}
	
	@PostMapping ("/eliminar-categoria")
	@RequestMapping(value="/eliminar-categoria",method = RequestMethod.POST) 
	public String eliminarCategoria (
			@RequestParam(value="idCategoria") String idCategoria, 
			Model model) {
		Categoria categoria = this.categoriaService.findById(Long.parseLong(idCategoria)).get();
	
		this.categoriaService.delete(categoria);
		
		return "redirect:/index";
	}
	
	@PostMapping ("/editar-categoria-formulario")
	@RequestMapping(value="/editar-categoria-formulario",method = RequestMethod.POST) 
	public String editarCategoriaFormulario (
			@RequestParam(value="idCategoria") String idCategoria, 
			Model model) {
		Categoria categoria = this.categoriaService.findById(Long.parseLong(idCategoria)).get();
		
		model.addAttribute("categoria", categoria);
		
		return "/formularioeditarcategoria";
	}
	
	@PostMapping ("/actualizar-categoria")
	@RequestMapping(value="/actualizar-categoria",method = RequestMethod.POST) 
	public String actualizarCategoria (
			@RequestParam(value="idCategoria") String idCategoria, 
			@RequestParam(value="nombre") String nombre, 
			@RequestParam(value="descripcion") String descripcion,
			Model model) {
		Categoria categoriaAntigua = this.categoriaService.findById(Long.parseLong(idCategoria)).get();
		categoriaAntigua.setNombre(nombre);
		categoriaAntigua.setDescripcion(descripcion);
		this.categoriaService.edit(categoriaAntigua);
		
		return "redirect:/index";
	}
	
}
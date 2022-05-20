package com.salesianostriana.dam.forowow.controller;

import java.util.ArrayList;
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

import com.salesianostriana.dam.forowow.model.Hilo;
import com.salesianostriana.dam.forowow.model.Mensaje;
import com.salesianostriana.dam.forowow.model.MensajeUsuario;
import com.salesianostriana.dam.forowow.security.Usuario;
import com.salesianostriana.dam.forowow.security.UsuarioRepo;
import com.salesianostriana.dam.forowow.services.HiloService;
import com.salesianostriana.dam.forowow.services.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepo usuarioRepo;

	@Autowired
	private HiloService hiloService;

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/perfil/{username}")
	public String mostrarPerfil(@PathVariable String username, Model model) {
		Usuario usuario = this.usuarioService.getUsuarioRepo().findUserByUsername(username).get();

		model.addAttribute("usuario", usuario);

		return "perfil";
	}

	@GetMapping("/usuarios")
	public String listarUsuarios(Model model) {
		List<Usuario> listaUsuarios = new ArrayList<>();
		listaUsuarios = this.usuarioRepo.getUsuarios();
		model.addAttribute("usuarios", listaUsuarios);
		return "perfilesadmin";
	}

	@GetMapping("/login")
	public String mostrarLogin(Model model) {
		return "formulariologuin";
	}

	@GetMapping("/logout")
	public String cerrarSesion(Model model) {
		return "index";
	}

	@GetMapping("/login-error")
	public String mostrarLoginError(Model model) {
		return "formulariologuinerror";
	}

	@GetMapping("/autenticar-usuario")
	public String inicarSesion(Model model) {
		List<Usuario> listaUsuarios = new ArrayList<>();
		listaUsuarios = this.usuarioService.getUsuarioRepo().getUsuarios();
		List<Usuario> listaReyes = listaUsuarios.stream().filter(u -> u.getRango().toString().equalsIgnoreCase("rey"))
				.toList();

		for (Usuario usuario : listaReyes) {
			List<Hilo> listaHilos = this.hiloService.findAll().stream()
					.filter(h -> h.getCreador().equalsIgnoreCase(usuario.getUsername())).toList();
			if (listaHilos.size() > 0) {
				for (Hilo hilo : listaHilos) {
					hilo.setLikes(hilo.getLikes() + 10);
					this.hiloService.save(hilo);
				}
			}
		}
		return "index.html";
	}

	@GetMapping("/usuarios-ordenar-puntos")
	public String ordenarUsuariosPuntos(Model model) {
		List<Usuario> listaUsuarios = new ArrayList<>();
		listaUsuarios = this.usuarioService.getUsuarioRepo().getUsuarios();
		List<Usuario> listaUsuariosPuntos = listaUsuarios.stream()
				.sorted(Comparator.comparingInt(Usuario::getPuntos).reversed()).collect(Collectors.toList());
		model.addAttribute("usuarios", listaUsuariosPuntos);
		return "perfilesadmin";
	}

}

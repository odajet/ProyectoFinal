package com.salesianostriana.dam.forowow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesianostriana.dam.forowow.model.Hilo;
import com.salesianostriana.dam.forowow.security.Usuario;
import com.salesianostriana.dam.forowow.services.HiloService;
import com.salesianostriana.dam.forowow.services.UsuarioService;

@Controller
public class BusquedaController {
	
	@Autowired
	private HiloService hiloService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/buscar-todo")
	@RequestMapping(value="/buscar-todo",method = RequestMethod.POST) 
	@ResponseBody
	public String buscarTodo(@RequestParam(value="busqueda") String busqueda, Model model){
        List<Usuario>listaUsuarios = this.buscarUsuarios(busqueda);
        List<Hilo>listaHilos = this.buscarHilos(busqueda);
        String resultado = "";

        if(listaUsuarios.size()>0) {
            for (Usuario usuario : listaUsuarios) {
                resultado += resultado != "" ? "," : "";
                resultado+="{\"titulo\":\""+usuario.getUsername()+"\", \"tipo\":\"Usuario\", \"ruta\":\"/perfil/"+usuario.getUsername()+ "\"}";
            }
        }
        if(listaHilos.size()>0) {
            for (Hilo hilo : listaHilos) {
                resultado += resultado != "" ? "," : "";
                resultado+="{\"titulo\":\""+hilo.getNombre()+"\", \"tipo\":\"Hilo\", \"ruta\":\"/hilo/"+hilo.getId()+ "\"}";
            }
        }
        return "{\"resultado\":["+resultado+"]}";
    }
	
	private List<Usuario> buscarUsuarios(String busqueda){
		List<Usuario>listaUsuarios=this.usuarioService.getUsuarioRepo().findUserByUsernameList(busqueda);
		return listaUsuarios;
	}
	
	private List<Hilo> buscarHilos(String busqueda){
		List<Hilo>listaHilos=this.findHiloByHiloList(busqueda);
		return listaHilos;
	}
	
	public List<Hilo> findHiloByHiloList(String cadena) {
		return this.hiloService.findAll().stream()
				.filter(h -> h.getNombre().toLowerCase().contains(cadena.toLowerCase()))
				.toList();
	}

}

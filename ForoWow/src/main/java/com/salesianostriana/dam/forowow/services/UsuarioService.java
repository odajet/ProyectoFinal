package com.salesianostriana.dam.forowow.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.forowow.security.Usuario;
import com.salesianostriana.dam.forowow.security.UsuarioRepo;
import com.salesianostriana.dam.forowow.service.base.ServicioBase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor 
@AllArgsConstructor
@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepo usuarioRepo;
	
	public Optional<Usuario>obtenerUsuarioLogeado(){
		//Obtener el usuario que está logueado
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		Optional<Usuario> user = usuarioRepo.findUserByUsername(username); //fin obtener el usuario que está logueado
		return user;

	}
	
	
}

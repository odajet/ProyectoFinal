package com.salesianostriana.dam.forowow.security;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.forowow.security.Usuario.Rango;

@Repository
public class UsuarioRepo {
	
	private List<Usuario> usuarios;

	public List<Usuario> getUsuarios() {
		return Collections.unmodifiableList(usuarios);
	}

	public Optional<Usuario> findUserByUsername(String username) {
		return usuarios.stream()
				.filter(u -> u.getUsername().equals(username))
				.findFirst();
	}
	
	public List<Usuario> findUserByUsernameList(String username) {
		return usuarios.stream()
				.filter(u -> u.getUsername().toLowerCase().contains(username.toLowerCase()))
				.toList();
	}
	
	public Long findUserByUsernameAndPassword(String username, String password) {
		return usuarios.stream()
				.filter(u -> u.getUsername().equals(username))
				.filter(u -> u.getPassword().equals(password))
				.count();
	}
	
	@PostConstruct
	public void init() {
		usuarios = List.of(
				Usuario.builder()
					.username("admin")
					.password("admin")
					.role("ADMIN")
					.rango(Rango.rey)
					.avatar(Usuario.getAvatar("admin"))
					.build()
				,
				Usuario.builder()
				.username("user")
				.password("1234")
				.role("USER")
				.puntos(1)
				.rango(Rango.vasallo)
				.avatar(Usuario.getAvatar("user"))
				.build()
				,
				Usuario.builder()
				.username("principe")
				.password("1234")
				.role("USER")
				.puntos(500)
				.rango(Rango.principe)
				.avatar(Usuario.getAvatar("principe"))
				.build()
				,
				Usuario.builder()
				.username("rey")
				.password("1234")
				.role("USER")
				.puntos(3000)
				.rango(Rango.rey)
				.avatar(Usuario.getAvatar("rey"))
				.build()
				
				
				);
				
	}
	
	

}

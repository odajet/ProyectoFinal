package com.salesianostriana.dam.forowow.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.salesianostriana.dam.forowow.model.Mensaje;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
	
	private String username;
	private String role;
	private String password;
	private Rango rango;
	private int puntos;
	private String avatar;

	public enum Rango{
		rey, principe, vasallo
	}
	
	public static String getAvatar(String username) {
		return "https://robohash.org/"+username;
	}
	

}

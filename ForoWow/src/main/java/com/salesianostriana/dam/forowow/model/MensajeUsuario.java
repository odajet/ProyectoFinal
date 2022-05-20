package com.salesianostriana.dam.forowow.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;

import com.salesianostriana.dam.forowow.security.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class MensajeUsuario {
	
	private Usuario usuario;
	private Mensaje mensaje;

}

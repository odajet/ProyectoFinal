package com.salesianostriana.dam.forowow.model;

import java.sql.Timestamp;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import org.springframework.format.annotation.DateTimeFormat;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @NoArgsConstructor @AllArgsConstructor @Entity @Builder
public class Mensaje {

	@Id @GeneratedValue
	private long id;
	private String contenido;
	private String tipoMensaje;
	private String creador;
	@ManyToOne
	@JoinColumn(name = "hilo_id")
	private Hilo hilo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Timestamp fechaCreacion;

	
}
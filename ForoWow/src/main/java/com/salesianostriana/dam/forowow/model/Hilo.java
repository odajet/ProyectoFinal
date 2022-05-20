package com.salesianostriana.dam.forowow.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data @NoArgsConstructor @AllArgsConstructor @Entity @Builder @Table
public class Hilo {

	@Id @GeneratedValue @Column
	private long id;
	@Column
	private String nombre;
	@Column
	private int likes;
	@Column
	private String tipoHilo;
	@Column
	private String contenido;
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Timestamp fechaCreacion;
	@Column
	private String creador;
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	@Builder.Default
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy="hilo", fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
	private List<Mensaje>mensajes = new ArrayList<>();
	
	
}
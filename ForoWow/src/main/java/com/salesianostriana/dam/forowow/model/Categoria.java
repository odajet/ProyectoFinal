package com.salesianostriana.dam.forowow.model;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @Entity @Builder @Table
public class Categoria {
	
	@Id @GeneratedValue @Column
	private long id;
	@Column
	private String nombre;
	@Column
	private String descripcion;
	
	@Builder.Default
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy="categoria", fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
	private List<Hilo>hilos = new ArrayList<>();
	
}

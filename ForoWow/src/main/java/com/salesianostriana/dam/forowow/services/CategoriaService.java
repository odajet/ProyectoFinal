package com.salesianostriana.dam.forowow.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.forowow.model.Categoria;
import com.salesianostriana.dam.forowow.repositories.CategoriaRepository;
import com.salesianostriana.dam.forowow.service.base.ServicioBaseImpl;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor 
@AllArgsConstructor
@Service
public class CategoriaService extends ServicioBaseImpl<Categoria, Long, CategoriaRepository>{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> obtenerTodasLasCategorias() {
		
		List<Categoria> listaCategorias = new ArrayList<Categoria>();
		
		categoriaRepository.findAll().forEach(categoria -> listaCategorias.add(categoria));	
		return listaCategorias;
	}

}

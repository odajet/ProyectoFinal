package com.salesianostriana.dam.forowow.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.forowow.model.Categoria;
import com.salesianostriana.dam.forowow.model.Hilo;
import com.salesianostriana.dam.forowow.repositories.CategoriaRepository;
import com.salesianostriana.dam.forowow.repositories.HiloRepository;
import com.salesianostriana.dam.forowow.service.base.ServicioBaseImpl;

@Service
public class HiloService extends ServicioBaseImpl<Hilo, Long, HiloRepository> {

	private List<Hilo>listaHilos;
	
	@Autowired
	private HiloRepository hiloRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Hilo> obtenerHilosPorCategoria(long idCategoria) {
		List<Hilo> listaHilos = new ArrayList<Hilo>();
		Optional <Categoria> categoria = categoriaRepository.findById(idCategoria);
		listaHilos = hiloRepository.findByCategoria(categoria.get().getNombre());
		return listaHilos;

	}
	
	public List<Hilo> findHiloByHiloList(String cadena) {
		return listaHilos.stream()
				.filter(h -> h.getNombre().contains(cadena))
				.toList();
	}
	
	public void asignarLikeAHilo(Hilo hilo) {
		hilo.setLikes(hilo.getLikes()+1);
		this.save(hilo);
	}

}

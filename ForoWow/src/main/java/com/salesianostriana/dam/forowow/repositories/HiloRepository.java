package com.salesianostriana.dam.forowow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.forowow.model.Hilo;

public interface HiloRepository extends JpaRepository <Hilo, Long>{
	List<Hilo> findByCategoria(String categoria);
}

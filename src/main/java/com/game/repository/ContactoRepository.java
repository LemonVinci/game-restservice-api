package com.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game.model.Contacto;

public interface ContactoRepository extends JpaRepository<Contacto,Integer> {
	  
	Contacto findByName(String name);	
}

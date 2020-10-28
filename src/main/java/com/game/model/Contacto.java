package com.game.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contacto")
public class Contacto {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String email;
	private String telefono;
	
	
}

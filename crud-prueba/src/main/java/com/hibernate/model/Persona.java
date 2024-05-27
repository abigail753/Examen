package com.hibernate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "persona")
public class Persona {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "idpersona")
	private int id;
	@Column (name = "nombre")
	private String nombre;
	@Column (name = "calorias")
	private int calorias;
	
	
	public Persona() {
		super();
	}


	public Persona(String nombre, int calorias) {
		super();
		this.nombre = nombre;
		this.calorias = calorias;
	}


	public int getId() {
		return id;
	}


	public String getNombre() {
		return nombre;
	}


	public int getCalorias() {
		return calorias;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setCalorias(int calorias) {
		this.calorias = calorias;
	}
	
	
	
	
	
}

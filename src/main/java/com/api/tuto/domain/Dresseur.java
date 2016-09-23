package com.api.tuto.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "dresseur")
public class Dresseur {
	
	@Id
	private int id;
	
	@Column(name = "nom")
	private String nom;
	
	@JsonIgnore
	@OneToMany(mappedBy="dresseur")
	private List<PokeCapture> pokeCaptures;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<PokeCapture> getPokeCaptures() {
		return pokeCaptures;
	}

	public void setPokeCaptures(List<PokeCapture> pokeCaptures) {
		this.pokeCaptures = pokeCaptures;
	}
	
	public void addPokeCaptures(PokeCapture poke) {
		pokeCaptures.add(poke);
	}
	
	
	
	

}

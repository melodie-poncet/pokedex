package com.api.tuto.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="poke_capture")
public class PokeCapture implements Serializable{
	
	private static final long serialVersionUID = -5526328398586277608L;

	@EmbeddedId
	private PokeCaptureID id;
	
	@Column(name = "niveau")
	private int niveau;
	
	@Column(name = "sexe")
	private char sexe;
	
	@ManyToOne
	@JoinColumn(name = "id_pokemon", insertable = false, updatable = false )
	TypePokemon type_poke;
	
	@ManyToOne
	@JoinColumn(name = "id_dresseur", insertable = false, updatable = false)
	Dresseur dresseur;


	public PokeCaptureID getId() {
		return id;
	}

	public void setId(PokeCaptureID id) {
		this.id = id;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public char getSexe() {
		return sexe;
	}

	public void setSexe(char sexe) {
		this.sexe = sexe;
	}

	public TypePokemon getType_poke() {
		return type_poke;
	}

	public void setType_poke(TypePokemon type_poke) {
		this.type_poke = type_poke;
	}

	public Dresseur getDresseur() {
		return dresseur;
	}

	public void setDresseur(Dresseur dresseur) {
		this.dresseur = dresseur;
	}
	
	
}

package com.api.tuto.domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.api.tuto.repository.PKeyPokeCapture;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name ="poke_capture")
public class PokeCapture implements Serializable{
	
	@EmbeddedId
	private PKeyPokeCapture pk;
	
	@Column(name = "niveau")
	private int niveau;
	
	@Column(name = "sexe")
	private String sexe;
	
	@ManyToOne
	@JoinColumn(name = "id_pokemon", insertable=false, updatable=false)
	TypePokemon type_poke;
	
	
	@ManyToOne
	@JoinColumn(name = "id_dresseur", insertable=false, updatable=false)
	Dresseur dresseur;
	
	

	public PokeCapture(){
		
	}

	public PKeyPokeCapture getPk() {
		return pk;
	}

	public void setPk(PKeyPokeCapture pk) {
		this.pk = pk;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

//	public TypePokemon getType_poke() {
//		return type_poke;
//	}
//
//	public void setType_poke(TypePokemon type_poke) {
//		this.type_poke = type_poke;
//	}
//
//	public Dresseur getDresseur() {
//		return dresseur;
//	}
//
//	public void setDresseur(Dresseur dresseur) {
//		this.dresseur = dresseur;
//	}
	
	@JsonIgnore
	public int getType_pokeID() {
		return pk.getType_poke().getId();
	}
	
	@JsonIgnore
	public int getDresseurID() {
		return pk.getDresseur().getId();
	}
	
	@Override
	public boolean equals(Object o){
		
		 if(o instanceof PokeCapture){
			 PokeCapture poke = (PokeCapture) o;
			 if(poke.getDresseurID() != this.getDresseurID()  || poke.getType_pokeID() != this.getType_pokeID()){
				 return false;
			 }
			 
			 return true;
			 
		 }else{
			 return false;
		 }
		
	}
}

package com.api.tuto.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PokeCaptureID implements Serializable {

	private static final long serialVersionUID = 3366512289445072900L;
	
	private int id_dresseur;
	private int id_typePokemon;
	
	public PokeCaptureID(int id_dresseur, int id_typePokemon){
		this.id_dresseur = id_dresseur;
		this.id_typePokemon = id_typePokemon;
	}

	public int getId_dresseur() {
		return id_dresseur;
	}

	public int getId_typePokemon() {
		return id_typePokemon;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
			
		if (obj == null){
			return false;
		}
			
		if (getClass() != obj.getClass()){
			return false;
		}
			
		PokeCaptureID other = (PokeCaptureID) obj;
		if(other.getId_dresseur() != id_dresseur || other.getId_typePokemon()!= id_typePokemon){
			return false;
		}
		return true;
	}

}

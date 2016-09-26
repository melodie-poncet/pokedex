package com.api.tuto.repository;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.api.tuto.domain.Dresseur;
import com.api.tuto.domain.TypePokemon;

import java.util.Objects;

@Embeddable
public class PKeyPokeCapture implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6864200419194846423L;


	@ManyToOne
	@JoinColumn(name = "id_pokemon")
	TypePokemon type_poke;
	
	
	@ManyToOne
	@JoinColumn(name = "id_dresseur")
	Dresseur dresseur;
	
	public PKeyPokeCapture(){
		
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

	@Override
	public boolean equals(Object o){
		
		 if(o instanceof PKeyPokeCapture){
			 PKeyPokeCapture pk = (PKeyPokeCapture) o;
 
            if(pk.getDresseur().getId() != dresseur.getId()){
                return false;
            }
 
            if(pk.getType_poke().getId() != type_poke.getId()){
                return false;
            }
 
            return true;
        }
 
        return false;
		
	}
	
	@Override
	public int hashCode(){
		return Objects.hashCode(dresseur.getId()) + Objects.hashCode(type_poke.getId());
	}
}

package com.api.tuto.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name ="poke_capture")
public class PokeCapture implements Serializable{
	
	
	
	private static final long serialVersionUID = -5526328398586277608L;

	
	@Column(name = "niveau")
	private int niveau;
	
	@Column(name = "sexe")
	private String sexe;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "id_pokemon")
	TypePokemon type_poke;
	
	
	@Id
	@ManyToOne
	@JoinColumn(name = "id_dresseur")
	Dresseur dresseur;

	public PokeCapture(){
		
	}

	public PokeCapture(TypePokemon tp, Dresseur d, String niveau, String sexe){
		dresseur = d;
		type_poke = tp;
		try{
			this.niveau = Integer.valueOf(niveau);
		}catch (NumberFormatException e) {
			System.err.println(e);
			this.niveau = 0;
		}
		this.sexe = sexe;
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
	public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PokeCapture pc = (PokeCapture) o;
        if(pc.getDresseur().getId() != dresseur.getId() || pc.getType_poke().getId() != type_poke.getId()) {
            return false;
        }
        return true;
    }
	
	
}

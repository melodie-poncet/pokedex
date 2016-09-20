package com.api.tuto.domain;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "pokemons")
public class TypePokemon  {

    @Id
    private int id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "type")
    private String typeP;
    
	@OneToMany(mappedBy="type_poke")
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


	public String getTypeP() {
		return typeP;
	}

	public void setTypeP(String typeP) {
		this.typeP = typeP;
	}
	
	public List<PokeCapture> getPokeCaptures() {
		return pokeCaptures;
	}

	public void addPokeCaptures(PokeCapture pokeCapture) {
		pokeCaptures.add(pokeCapture);
	}

}

package com.api.tuto.domain;


import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "pokemons")
public class Pokemon  {

    @Id
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "type")
    private String typeP;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pokemon p = (Pokemon) o;
        if(p.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, p.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", nom='" + nom + "'" +
                ", typeP='" + typeP + "'" +
                '}';
    }
}

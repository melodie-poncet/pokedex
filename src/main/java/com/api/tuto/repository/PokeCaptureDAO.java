package com.api.tuto.repository;

import java.util.List;

import com.api.tuto.domain.PokeCapture;

public interface PokeCaptureDAO {
	
	public void addPokeCapture(PokeCapture poke);
	public void updatePokeCapture(PokeCapture poke);
	public boolean removePokeCapture(int id_dresseur, int id_typePokemon);
	public boolean removePokeCapture(PokeCapture poke);
	public List<PokeCapture> getPokeCapturesByIdDresseur(int id_dresseur);
	public List<PokeCapture> getPokeCapturesByIdTypePokemon(int id_typePokemon);
	public PokeCapture getPokeCaptureByIdTypePokemonAndIdDresseur(int id_typePokemon,int id_dresseur);
}

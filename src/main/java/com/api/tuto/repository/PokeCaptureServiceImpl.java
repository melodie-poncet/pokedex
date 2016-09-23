package com.api.tuto.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.tuto.domain.PokeCapture;

@Transactional
@Service
public class PokeCaptureServiceImpl implements PokeCaptureService {
	
	@Autowired
	private PokeCaptureDAO pokeDAO;

	@Override
	public void addPokeCapture(PokeCapture poke) {
		this.pokeDAO.addPokeCapture(poke);
	}

	@Override
	public void updatePokeCapture(PokeCapture poke) {
		this.pokeDAO.updatePokeCapture(poke);
	}


	@Override
	public boolean removePokeCapture(int id_dresseur, int id_typePokemon) {
		return this.pokeDAO.removePokeCapture(id_dresseur, id_typePokemon);
	}
	
	@Override
	public boolean removePokeCapture(PokeCapture poke) {
		return this.pokeDAO.removePokeCapture(poke);
	}

	@Override
	public List<PokeCapture> getPokeCapturesByIdDresseur(int id_dresseur) {
		return this.pokeDAO.getPokeCapturesByIdDresseur(id_dresseur);
	}

	@Override
	public List<PokeCapture> getPokeCapturesByIdTypePokemon(int id_typePokemon) {
		return this.pokeDAO.getPokeCapturesByIdTypePokemon(id_typePokemon);
	}

	@Override
	public PokeCapture getPokeCaptureByIdTypePokemonAndIdDresseur(int id_typePokemon, int id_dresseur) {
		return this.pokeDAO.getPokeCaptureByIdTypePokemonAndIdDresseur(id_typePokemon, id_dresseur);
	}

	


}

package com.api.tuto.repository;

import com.api.tuto.domain.TypePokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypePokemonRepository extends JpaRepository<TypePokemon,Integer> {
	
	TypePokemon findById(int id);
}
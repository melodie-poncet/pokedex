package com.api.tuto;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.api.tuto.domain.Pokemon;
import com.api.tuto.repository.PokemonRepository;

@RestController
@RequestMapping("/pokedex")
public class PokemonResource {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Transactional(readOnly = true)
    @RequestMapping(path="/pokemons", method = RequestMethod.GET)
    @CrossOrigin(origins ="http://editor.swagger.io")
    List<Pokemon> getPokemons()  {
        List<Pokemon> pokemons = pokemonRepository.findAll();
        return pokemons;
    }

    @RequestMapping(path ="/pokemons/{id}", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    ResponseEntity<Pokemon> getPokemon(@PathVariable Long id) {
        Optional<Pokemon> pokemon = Optional.ofNullable(pokemonRepository.findOne(id));
        if(pokemon.isPresent()) {
            return ResponseEntity.ok(pokemon.get());
        } else {
            return new ResponseEntity<Pokemon>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/pokemons", method = RequestMethod.POST)
    public ResponseEntity<Pokemon> createPokemon(@RequestBody Pokemon pokemon) throws URISyntaxException {
    	Pokemon result = pokemonRepository.save(pokemon);
        URI newRessourceURI = new URI("/pokedex/pokemons/" + result.getId());
        return ResponseEntity.created(newRessourceURI).body(pokemon);
    }


    @RequestMapping(value = "/pokemons/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Pokemon> updateTask(@PathVariable Long id, @RequestBody Pokemon pokemon) throws URISyntaxException {
    	pokemon.setId(id);
        Pokemon result = pokemonRepository.save(pokemon);
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(value = "/pokemons/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) throws URISyntaxException {
        pokemonRepository.delete(id);
        return ResponseEntity.noContent().build();
    }



}
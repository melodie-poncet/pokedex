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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/pokedex")
public class PokemonResource {

    @Autowired
    private PokemonRepository pokemonRepository;

    @ApiOperation(value = "Gets all pokemons in pokedex.",  response = Pokemon.class, responseContainer = "List")
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "Successful response") })
    @Transactional(readOnly = true)
    @RequestMapping(path="/pokemons", method = RequestMethod.GET)
    @CrossOrigin(origins ="http://editor.swagger.io")
    List<Pokemon> getPokemons()  {
        List<Pokemon> pokemons = pokemonRepository.findAll();
        return pokemons;
    }

    @ApiOperation(value = "Get pokemon with ID.",  response = Pokemon.class)
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "Successful response") })
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

    @ApiOperation(value = "Create a new pokemon",  response = Pokemon.class, notes = "the ID must match the official number of pokemon ")
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "Successful response") })
    @RequestMapping(value = "/pokemons", method = RequestMethod.POST)
    public ResponseEntity<Pokemon> createPokemon(@RequestBody Pokemon pokemon) throws URISyntaxException {
    	Pokemon result = pokemonRepository.save(pokemon);
        URI newRessourceURI = new URI("/pokedex/pokemons/" + result.getId());
        return ResponseEntity.created(newRessourceURI).body(pokemon);
    }

    @ApiOperation(value = "Update a pokemon with ID",  response = Pokemon.class, notes = "ID does must not change, it could be a problem")
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "Successful response") })
    @RequestMapping(value = "/pokemons/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Pokemon> updateTask(@PathVariable Long id, @RequestBody Pokemon pokemon) throws URISyntaxException {
    	pokemon.setId(id);
        Pokemon result = pokemonRepository.save(pokemon);
        return ResponseEntity.ok().body(result);
    }

    @ApiOperation(value = "Delete a pokemon with ID",  response = Pokemon.class)
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "Successful response") })
    @RequestMapping(value = "/pokemons/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) throws URISyntaxException {
        pokemonRepository.delete(id);
        return ResponseEntity.noContent().build();
    }



}
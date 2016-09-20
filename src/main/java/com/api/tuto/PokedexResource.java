package com.api.tuto;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.api.tuto.domain.Dresseur;
import com.api.tuto.domain.PokeCapture;
import com.api.tuto.domain.TypePokemon;
import com.api.tuto.repository.DresseurRepository;
import com.api.tuto.repository.PokeCaptureRepository;
import com.api.tuto.repository.TypePokemonRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/pokedex")
public class PokedexResource {

    @Autowired
    private TypePokemonRepository typePokemonRepository;
    
    @Autowired
    private DresseurRepository dresseurRepository;
    
    @Autowired
    private PokeCaptureRepository pokeCaptureRepository;

    @ApiOperation(value = "Gets all pokemons in pokedex.",  response = TypePokemon.class, responseContainer = "List")
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "Successful response") })
    @Transactional(readOnly = true)
    @RequestMapping(path="/t_pokemons", method = RequestMethod.GET)
    @CrossOrigin(origins ="http://editor.swagger.io")
    List<TypePokemon> getPokemons()  {
        List<TypePokemon> typePokemons = typePokemonRepository.findAll();
        return typePokemons;
    }

    @ApiOperation(value = "Get pokemon with ID.",  response = TypePokemon.class)
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "Successful response") })
    @RequestMapping(path ="/t_pokemons/{id}", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    ResponseEntity<TypePokemon> getPokemon(@PathVariable int id) {
        Optional<TypePokemon> typePokemon = Optional.ofNullable(typePokemonRepository.findOne(id));
        if(typePokemon.isPresent()) {
            return ResponseEntity.ok(typePokemon.get());
        } else {
            return new ResponseEntity<TypePokemon>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Create a new pokemon",  response = TypePokemon.class, notes = "the ID must match the official number of pokemon ")
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "Successful response") })
    @RequestMapping(value = "/t_pokemons", method = RequestMethod.POST)
    public ResponseEntity<TypePokemon> createPokemon(@RequestBody TypePokemon typePokemon) throws URISyntaxException {
    	TypePokemon result = typePokemonRepository.save(typePokemon);
        URI newRessourceURI = new URI("/pokedex/pokemons/" + result.getId());
        return ResponseEntity.created(newRessourceURI).body(typePokemon);
    }

    @ApiOperation(value = "Update a pokemon with ID",  response = TypePokemon.class, notes = "ID does must not change")
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "Successful response"), @io.swagger.annotations.ApiResponse(code = 401, message = "ID should not be changed !") })
    @RequestMapping(value = "/t_pokemons/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TypePokemon> updatePokemon(@PathVariable int id, @RequestBody TypePokemon typePokemon) throws URISyntaxException {
    	if(typePokemon.getId() != id){
    		return new ResponseEntity<TypePokemon>(HttpStatus.UNAUTHORIZED);
    	}
        TypePokemon result = typePokemonRepository.save(typePokemon);
        return ResponseEntity.ok().body(result);
    }

    @ApiOperation(value = "Delete a pokemon with ID",  response = TypePokemon.class)
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "Successful response") })
    @RequestMapping(value = "/pokemons/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePokemon(@PathVariable int id) throws URISyntaxException {
        typePokemonRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    

    @ApiOperation(value = "Gets all dresseurs.",  response = Dresseur.class, responseContainer = "List")
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "Successful response") })
    @Transactional(readOnly = true)
    @RequestMapping(path="/dresseurs", method = RequestMethod.GET)
    List<Dresseur> getDresseurs()  {
        List<Dresseur> dresseurs = dresseurRepository.findAll();
        return dresseurs;
    }

    @ApiOperation(value = "Get dresseur with ID.",  response = Dresseur.class)
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "Successful response") })
    @RequestMapping(path ="/dresseurs/{id}", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    ResponseEntity<Dresseur> getDresseur(@PathVariable int id) {
        Optional<Dresseur> dresseur = Optional.ofNullable(dresseurRepository.findOne(id));
        if(dresseur.isPresent()) {
            return ResponseEntity.ok(dresseur.get());
        } else {
            return new ResponseEntity<Dresseur>(HttpStatus.NOT_FOUND);
        }
    }
    
    @ApiOperation(value = "Get pokemons of dresseur with ID.",  response = Dresseur.class)
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "Successful response") })
    @RequestMapping(path ="/dresseurs/{id}/pokemons", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    List<PokeCapture> getPokemonsDresseur(@PathVariable int id) {
        Optional<Dresseur> dresseur = Optional.ofNullable(dresseurRepository.findOne(id));
        if(dresseur.isPresent()) {
        	Dresseur d = dresseur.get();
        	return d.getPokeCaptures();
        }else{
        	return null;
        }
    }
    
    

    @ApiOperation(value = "Create a new dresseur",  response = TypePokemon.class, notes = "the ID is automatically defined")
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "Successful response") })
    @RequestMapping(value = "/dresseurs", method = RequestMethod.POST)
    public ResponseEntity<Dresseur> createDresseur(@RequestBody Dresseur dresseur) throws URISyntaxException {
    	dresseur.setId(Utile.getAutoID(dresseurRepository));
    	Dresseur result = dresseurRepository.save(dresseur);
        URI newRessourceURI = new URI("/pokedex/dresseurs/" + result.getId());
        return ResponseEntity.created(newRessourceURI).body(dresseur);
    }
    
    @ApiOperation(value = "Create a new liste pokeCapture",  response = TypePokemon.class)
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "Successful response"), @io.swagger.annotations.ApiResponse(code = 206, message = "Pokemons have not all been added because they don't exist in database") })
    @RequestMapping(value = "/dresseurs/{id}/pokemons", method = RequestMethod.POST)
    public ResponseEntity<List<PokeCapture>> createPokeCaptureDresseur(@PathVariable int id, @RequestBody List<PokeCapture> pokeCaptures) {
    	
    	Optional<Dresseur> dresseur = Optional.ofNullable(dresseurRepository.findOne(id));
        if(dresseur.isPresent()) {
        	boolean allAdd = true;
        	
        	for(PokeCapture poke : pokeCaptures){
        		Optional<TypePokemon> typePokemon = Optional.ofNullable(typePokemonRepository.findOne(poke.getType_poke().getId()));
        		if(typePokemon.isPresent()){
        			poke.setDresseur(dresseur.get());
        			poke.setType_poke(typePokemon.get());
					pokeCaptureRepository.save(poke);
					dresseur.get().addPokeCaptures(poke);
					typePokemon.get().addPokeCaptures(poke);
					
        		}else{
					allAdd = false;
				}
					
        	}
        	if(allAdd){
        		return ResponseEntity.ok().body(pokeCaptures);
        	}else{
        		return new ResponseEntity<List<PokeCapture>>(HttpStatus.PARTIAL_CONTENT);
        	}
        	
        }else{
        	return new ResponseEntity<List<PokeCapture>>(HttpStatus.NOT_FOUND);
        }
    	
    }

    @ApiOperation(value = "Update a dresseur with ID",  response = TypePokemon.class, notes = "ID does must not change")
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "Successful response"), @io.swagger.annotations.ApiResponse(code = 401, message = "ID should not be changed !") })
    @RequestMapping(value = "/dresseurs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Dresseur> updateDresseur(@PathVariable int id, @RequestBody Dresseur dresseur) throws URISyntaxException {
    	if(dresseur.getId() != id){
    		return new ResponseEntity<Dresseur>(HttpStatus.UNAUTHORIZED);
    	}
        Dresseur result = dresseurRepository.save(dresseur);
        return ResponseEntity.ok().body(result);
    }

    @ApiOperation(value = "Delete a dresseur with ID",  response = TypePokemon.class)
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "Successful response") })
    @RequestMapping(value = "/dresseurs/{id}", method = RequestMethod.DELETE)
    public HttpStatus deleteDresseur(@PathVariable int id) throws URISyntaxException {
        dresseurRepository.delete(id);
        return HttpStatus.OK;
    }



}
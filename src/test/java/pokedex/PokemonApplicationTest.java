package pokedex;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

//import javax.servlet.ServletContext;

import com.api.tuto.PokedexResource;
import com.api.tuto.PokemonApplication;
import com.api.tuto.domain.Dresseur;
import com.api.tuto.domain.TypePokemon;
import com.api.tuto.repository.DresseurRepository;
import com.api.tuto.repository.PokeCaptureRepository;
import com.api.tuto.repository.TypePokemonRepository;

import ch.qos.logback.core.status.Status;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = PokemonApplication.class)

// Replace deprecated @SpringApplicationConfiguration
@SpringBootTest
@ContextConfiguration(classes = PokemonApplication.class)

@WebAppConfiguration
public class PokemonApplicationTest {
	
	@Autowired
	private TypePokemonRepository typePokemonRepository;
	
	@Autowired
	private DresseurRepository dresseurRepository;
	
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void test_getDresseurByID() throws Exception{
		for(Dresseur d : dresseurRepository.findAll()){
			System.err.println(d.getId() + "  " + d.getNom());
			mockMvc.perform(get("/pokedex/dresseurs/" + d.getId()))
							.andExpect(status().isOk())
							.andExpect(jsonPath("nom").value(d.getNom()));
		}
	}
	
	@Test
	public void test_getTypePokemonByID() throws Exception{
		for(TypePokemon t_poke : typePokemonRepository.findAll()){
			System.err.println(t_poke.getId() + "  " + t_poke.getNom() + "  " + t_poke.getTypeP());
			mockMvc.perform(get("/pokedex/t_pokemons/" + t_poke.getId()))
							.andExpect(status().isOk())
							.andExpect(jsonPath("nom").value(t_poke.getNom()))
							.andExpect(jsonPath("typeP").value(t_poke.getTypeP()));
		}
	}
	
	@Test
	public void test_postDresseurGenerateID() throws Exception{
		
		int cpt = 1;
		for(Dresseur d : dresseurRepository.findAll()){
			if(d.getId()>cpt){
				cpt = d.getId();
			}
		}
		cpt += 1;
		mockMvc.perform( post("/pokedex/dresseurs/").contentType(MediaType.APPLICATION_JSON)
				.content("{\"nom\": \"DresseurTest\"}"))
				.andExpect(status().isCreated());
		
		if(!dresseurRepository.exists(cpt)){
			fail();
		}else{
			assertEquals("Problème de compteur pour new ID Dresseur", "DresseurTest", dresseurRepository.findOne(cpt).getNom());
			dresseurRepository.delete(cpt);
		}
	}
	
	
	@Test
	public void test_postPokeCapture_coherence_DresseurID() throws Exception{
		mockMvc.perform( post("/pokedex/dresseurs/3/pokemons").contentType(MediaType.APPLICATION_JSON)
				.content("[{\"niveau\": 3,\"pk\": {\"dresseur\": {\"id\": 88},\"type_poke\": {\"id\": 25}},\"sexe\": \"M\"}]"))
//				.andExpect(content().string("\"CONFLICT\""))
				.andExpect(status().isConflict());
	
	}
	
	@Test
	public void test_postPokeCapture_UnknownDresseur() throws Exception{
		Random r = new Random();
		int id = r.nextInt();
		while(dresseurRepository.exists(id)){
			id = r.nextInt();
		}
		
		mockMvc.perform( post("/pokedex/dresseurs/" + id + "/pokemons").contentType(MediaType.APPLICATION_JSON)
				.content("[{\"niveau\": 3,\"pk\": {\"dresseur\": {\"id\": " + id + "},\"type_poke\": {\"id\": 25}},\"sexe\": \"M\"}]"))
				.andExpect(status().isNotFound());
	
	}
	
	@Test
	public void test_postPokeCapture_UnknownPokemon() throws Exception{
		Random r = new Random();
		int id = r.nextInt();
		while(typePokemonRepository.exists(id)){
			id = r.nextInt();
		}
		
		mockMvc.perform( post("/pokedex/dresseurs/1/pokemons").contentType(MediaType.APPLICATION_JSON)
				.content("[{\"niveau\": 3,\"pk\": {\"dresseur\": {\"id\": 1},\"type_poke\": {\"id\": " + id + "}},\"sexe\": \"M\"}]"))
				.andExpect(status().isPartialContent());
	
	}
}

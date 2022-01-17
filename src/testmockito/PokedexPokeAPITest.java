package testmockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import controller.PokedexController;
import service.PokedexPokeAPI;

public class PokedexPokeAPITest {
	PokedexPokeAPI pokemonPokeAPIMocked = Mockito.mock(PokedexPokeAPI.class);
	PokedexPokeAPI pokemonPokeAPI = PokedexPokeAPI.getInstance();
	PokedexController pokedexControllerMocked = Mockito.mock(PokedexController.class);
	String stubData = new String();
	
	@Captor
	ArgumentCaptor<String> strCaptor = ArgumentCaptor.forClass(String.class);;
	public AutoCloseable closeable;
	
    @Before
    public void open() {
       closeable = MockitoAnnotations.openMocks(this);
    }

    @After
    public void release() throws Exception {
       closeable.close();
    }
	
	/**
	 * O metodo abaixo lê um arquivo em cache para gerar um json,
	 * armazenado em dataStub, que será retornado sempre que 
	 * a classe moackada for chamada para fazer uma requisição http 
	 * à API externa.
	 */
    
    public void stubPokeAPI() {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("src/testmockito/cache_pokemon_id_120_PokeAPI.json"));
			String output = "";
			String line;
			while ((line = br.readLine()) != null) {
				output += line;
			}
			stubData = output;
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@BeforeEach
	public void setUp() {
		stubPokeAPI();
	}
    	
	@Test
	public void testGetDataFromStubPokeAPI() {
		when(pokemonPokeAPIMocked.getDataPokeAPIFromUrl(anyString())).thenReturn(stubData);
		String new_data = pokemonPokeAPIMocked.getDataPokeAPIFromUrl("url");	
		verify(pokemonPokeAPIMocked, times(1)).getDataPokeAPIFromUrl(strCaptor.capture());
		assertEquals("url", strCaptor.getValue());
		assertEquals(new_data, stubData);
		assertTrue(new_data.contains("{\"ability\":{\"name\":\"natural-cure\",\"url\":"
				+ "\"https://pokeapi.co/api/v2/ability/30/\"}"));
	}
	
	@Test
	public void testGetDataFromRealPokeAPI() {
		String realData = pokemonPokeAPI.getDataPokeAPIFromUrl("https://pokeapi.co/api/v2/pokemon/120");
		assertTrue(realData.contains("{\"ability\":{\"name\":\"natural-cure\",\"url\":"
				+ "\"https://pokeapi.co/api/v2/ability/30/\"}"));
	}
	
	@Test
	public void testDataStub() {
		String realData = pokemonPokeAPI.getDataPokeAPIFromUrl("https://pokeapi.co/api/v2/pokemon/120");
		assertEquals(stubData, realData);
	}
}
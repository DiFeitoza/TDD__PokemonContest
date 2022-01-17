package testmockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import controller.PokedexController;
import service.PokedexPokeAPI;
import view.DisplayView;

public class PokedexPokeAPITest {
	PokedexController pokedexControllerMocked = Mockito.mock(PokedexController.class);
	DisplayView pokedexDisplayMocked = Mockito.mock(DisplayView.class);
	PokedexPokeAPI pokedexPokeAPI = PokedexPokeAPI.getInstance();

	String cacheDataPokemon = new String();
	String cacheDataEffectAbilities = new String();

	@Captor
	ArgumentCaptor<String> strCaptor = ArgumentCaptor.forClass(String.class);
	public AutoCloseable closeable;

	@Before
	public void open() {
		closeable = MockitoAnnotations.openMocks(this);
		cacheDataPokemon = generateDataFromCache("src/testmockito/cache_pokemon_id_120_PokeAPI.json");
		cacheDataEffectAbilities = generateDataFromCache("src/testmockito/cache_effect_abilities_148_PokeAPI.json");
	}

	@After
	public void release() throws Exception {
		closeable.close();
	}
	
	@BeforeEach
	public void setUp() {
		cacheDataPokemon = generateDataFromCache("src/testmockito/cache_pokemon_id_120_PokeAPI.json");
		cacheDataEffectAbilities = generateDataFromCache("src/testmockito/cache_effect_abilities_148_PokeAPI.json");	
	}

	/**
	 * Método leitura dos arquivos json em cache para comparação com o retorno das
	 * requisições à PokeAPI
	 */
	public String generateDataFromCache(String path) {
		BufferedReader br;
		String output = "";
		try {
			br = new BufferedReader(new FileReader(path));
			String line;
			while ((line = br.readLine()) != null) {
				output += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}

	/**
	 * SESSÃO 1 - Verifica os dados de retorno da PokeAPI, ao mesmo tempo que valida
	 * os arqivos json em cache gerados para testes futuros
	 */

	@Test
	@Order(1)
	@DisplayName("1. Verifica os dados de retorno da PokeAPI para /pokemon")
	public void test1() {
		String data = pokedexPokeAPI.getDataPokeAPIFromUrl("https://pokeapi.co/api/v2/pokemon/120");
		assertEquals(data, cacheDataPokemon);
		assertTrue(data.contains(
				"{\"abilities\":[{\"ability\":{\"name\":\"illuminate\",\"url\":\"https://pokeapi.co/api/v2/ability/35/\"}"));
	}

	@Test
	@Order(1)
	@DisplayName("2. Verifica os dados de retorno da PokeAPI, para /ability")
	public void test2() {
		String data = pokedexPokeAPI.getDataPokeAPIFromUrl("https://pokeapi.co/api/v2/ability/148");
		assertEquals(data, cacheDataEffectAbilities);
		assertTrue(data
				.contains("{\"effect\":\"This Pokémon's moves have 1.3× their power when it moves last in a turn."));
	}
	
	/**
	 * SESSÃO 2 - Verifica o retorno de chamadas para url inválidas ou vazias
	 * Obs. para os dois casos são lançadas exceções e os dados retornados são vazios
	 */
	
	@Test
	@Order(2)
	@DisplayName("3. Verifica os dados de retorno da PokeAPI, para url inválida")
	public void test3() {		
		String data = pokedexPokeAPI.getDataPokeAPIFromUrl("invalid_url");	
		assertEquals(data, "");
	}
	
	@Test
	@Order(2)
	@DisplayName("4. Verifica os dados de retorno da PokeAPI, para url vazia")
	public void test4() {
		String data = pokedexPokeAPI.getDataPokeAPIFromUrl("");
		assertEquals(data, "");
	}
}
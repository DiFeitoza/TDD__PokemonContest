package testmockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

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
import model.PokedexPokemon;
import service.PokedexPokeAPI;
import view.DisplayView;

public class PokedexControllerTest {
	PokedexPokeAPI pokemonPokeAPIMocked = Mockito.mock(PokedexPokeAPI.class);
	PokedexController pokedexControllerMocked = Mockito.mock(PokedexController.class);
	DisplayView pokedexDisplayMocked = Mockito.mock(DisplayView.class);
	PokedexController pokedexController = PokedexController.getInstance();
	String cacheDataPokemon = new String();
	String cacheDataEffectAbilities = new String();

	@Captor
	ArgumentCaptor<String> strCaptor = ArgumentCaptor.forClass(String.class);
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
	 * Método de criação dos dados do stub da PokeAPI para retornar o arquivo json
	 * em cache em vez do response da requisição http, quando chamado o método
	 * PokedexPokeAPI.getDataPokeAPIFromUrl(String url)
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

	@BeforeEach
	public void setUp() {
		pokedexController.setPokedexPokeAPI(pokemonPokeAPIMocked);
		pokedexController.setPokedexDisplay(pokedexDisplayMocked);

		cacheDataPokemon = generateDataFromCache("src/testmockito/cache_pokemon_id_120_PokeAPI.json");
		cacheDataEffectAbilities = generateDataFromCache("src/testmockito/cache_effect_abilities_148_PokeAPI.json");
		when(pokemonPokeAPIMocked.getDataPokeAPIFromUrl(Mockito.matches("https://pokeapi.co/api/v2/pokemon/.*")))
				.thenReturn(cacheDataPokemon);
		when(pokemonPokeAPIMocked.getDataPokeAPIFromUrl(Mockito.matches("https://pokeapi.co/api/v2/ability/.*")))
				.thenReturn(cacheDataEffectAbilities);
		doNothing().when(pokedexDisplayMocked).print(any(PokedexPokemon.class), anyString());
	}

	/**
	 * SESSÃO 1 - Verificando se o stub da classe PokemonPokeAPI retorna os dados
	 * mockados corretamente com base na url utilizada.
	 * 
	 * Sempre que a url começa com "https://pokeapi.co/api/v2/pokemon/" são
	 * retornados os dados do arquivo json em cache para o pokemons de id 120
	 * "cache_pokemon_id_120_PokeAPI.json".
	 * 
	 * Caso seja solicitado com a url iniciando em
	 * "https://pokeapi.co/api/v2/ability/", são retornados os dados do arquivo json
	 * em cache para o ability de id 148 "cache_pokemon_id_120_PokeAPI.json".
	 */

	@Test
	@Order(1)
	@DisplayName("1. Verifica os dados de retorno do Stub da PokeAPI para /pokemon")
	public void test1() {
		String dateReturn = pokemonPokeAPIMocked.getDataPokeAPIFromUrl("https://pokeapi.co/api/v2/pokemon/any");
		verify(pokemonPokeAPIMocked, times(1)).getDataPokeAPIFromUrl(strCaptor.capture());
		assertEquals("https://pokeapi.co/api/v2/pokemon/any", strCaptor.getValue());
		assertEquals(dateReturn, cacheDataPokemon);
		assertTrue(dateReturn.contains(
				"{\"ability\":{\"name\":\"natural-cure\",\"url\":" + "\"https://pokeapi.co/api/v2/ability/30/\"}"));
	}

	@Test
	@Order(1)
	@DisplayName("2. Verifica os dados de retorno do Stub da PokeAPI, para /ability")
	public void test2() {
		String dateReturn = pokemonPokeAPIMocked.getDataPokeAPIFromUrl("https://pokeapi.co/api/v2/ability/any");
		verify(pokemonPokeAPIMocked, times(1)).getDataPokeAPIFromUrl(strCaptor.capture());
		assertEquals("https://pokeapi.co/api/v2/ability/any", strCaptor.getValue());
		assertEquals(dateReturn, cacheDataEffectAbilities);
		assertTrue(dateReturn
				.contains("{\"effect\":\"This Pokémon's moves have 1.3× their power when it moves last in a turn."));
	}

	/**
	 * SESSÃO 2 - Verificando se as urls de requisição à PokeAPI foram montadas
	 * corretamente com base no arquivo do stub.
	 */

	@Test
	@Order(2)
	@DisplayName("3. Verifica a montagem das urls na interacao entre PokedexController e Stub PokeAPI")
	public void test3() {
		pokedexController.printPokedexPokemonFromId(120);
		verify(pokemonPokeAPIMocked, times(4)).getDataPokeAPIFromUrl(strCaptor.capture());
		List<String> capturedUrls = strCaptor.getAllValues();
		assertEquals(capturedUrls.get(0), "https://pokeapi.co/api/v2/pokemon/120/");
		assertEquals(capturedUrls.get(1), "https://pokeapi.co/api/v2/ability/35/");
		assertEquals(capturedUrls.get(2), "https://pokeapi.co/api/v2/ability/30/");
		assertEquals(capturedUrls.get(3), "https://pokeapi.co/api/v2/ability/148/");
	}

	/**
	 * SESSÃO 3 - Verificando os dados de retorno da API, a partir do Stub da classe
	 * PokemonPokeAPI.
	 * 
	 * Aqui é verificado se os dados retornados pelo stub da PokeAPI foram lidos
	 * corretamente para o objeto correspondente (PokemonPokedex).
	 * 
	 * Obs.: A biblioteca Gson exige que seja criada uma estrutura de
	 * classes/atributos correspondetes à estrutura do arquivo json para os valores
	 * que pretendemos registrar.
	 */

	@Test
	@Order(3)
	@DisplayName("4. Verifica atributo 'id' do obj PokemonPokedex gerado pelo retorno do Stub da PokeAPI")
	public void test4() {
		pokedexControllerMocked.printPokedexPokemonFromId(120);
		String data = pokemonPokeAPIMocked.getDataPokeAPIFromUrl("https://pokeapi.co/api/v2/pokemon/any");
		PokedexPokemon pokedexPokemon = pokedexController.parsePokemonFromJson(data);
		assertEquals(pokedexPokemon.getId(), 120);
	}

	@Test
	@Order(3)
	@DisplayName("5. Verifica atributo 'name' do obj PokemonPokedex gerado pelo retorno do Stub da PokeAPI")
	public void test5() {
		pokedexControllerMocked.printPokedexPokemonFromId(120);
		String data = pokemonPokeAPIMocked.getDataPokeAPIFromUrl("https://pokeapi.co/api/v2/pokemon/any");
		PokedexPokemon pokedexPokemon = pokedexController.parsePokemonFromJson(data);
		assertEquals(pokedexPokemon.getName(), "staryu");
	}

	@Test
	@Order(3)
	@DisplayName("6. Verifica atributo 'height' do obj PokemonPokedex gerado pelo retorno do Stub da PokeAPI")
	public void test6() {
		pokedexControllerMocked.printPokedexPokemonFromId(120);
		String data = pokemonPokeAPIMocked.getDataPokeAPIFromUrl("https://pokeapi.co/api/v2/pokemon/any");
		PokedexPokemon pokedexPokemon = pokedexController.parsePokemonFromJson(data);
		assertEquals(pokedexPokemon.getHeight(), 8);
	}

	@Test
	@Order(3)
	@DisplayName("7. Verifica atributo 'weight' do obj PokemonPokedex gerado pelo retorno do Stub da PokeAPI")
	public void test7() {
		pokedexControllerMocked.printPokedexPokemonFromId(120);
		String data = pokemonPokeAPIMocked.getDataPokeAPIFromUrl("https://pokeapi.co/api/v2/pokemon/any");
		PokedexPokemon pokedexPokemon = pokedexController.parsePokemonFromJson(data);
		assertEquals(pokedexPokemon.getHeight(), 8);
	}

	@Test
	@Order(3)
	@DisplayName("8. Verifica atributo 'types' do obj PokemonPokedex gerado pelo retorno do Stub da PokeAPI")
	public void test8() {
		pokedexControllerMocked.printPokedexPokemonFromId(120);
		String data = pokemonPokeAPIMocked.getDataPokeAPIFromUrl("https://pokeapi.co/api/v2/pokemon/any");
		PokedexPokemon pokedexPokemon = pokedexController.parsePokemonFromJson(data);
		assertEquals(pokedexPokemon.getTypes().size(), 1);
	}

	@Test
	@Order(3)
	@DisplayName("9. Verifica atributo 'sprites' (url da image) do obj PokemonPokedex gerado pelo retorno do Stub da PokeAPI")
	public void test9() {
		pokedexControllerMocked.printPokedexPokemonFromId(120);
		String data = pokemonPokeAPIMocked.getDataPokeAPIFromUrl("https://pokeapi.co/api/v2/pokemon/any");
		PokedexPokemon pokedexPokemon = pokedexController.parsePokemonFromJson(data);
		assertEquals(pokedexPokemon.getImageUrl(),
				"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/120.png");
	}

	@Test
	@Order(3)
	@DisplayName("10. Verifica atributo 'abilities' (urls para abilities) do obj PokemonPokedex gerado pelo retorno do Stub da PokeAPI")
	public void test10() {
		pokedexControllerMocked.printPokedexPokemonFromId(120);
		String data = pokemonPokeAPIMocked.getDataPokeAPIFromUrl("https://pokeapi.co/api/v2/pokemon/any");
		PokedexPokemon pokedexPokemon = pokedexController.parsePokemonFromJson(data);
		assertEquals(pokedexPokemon.getAbilitiesUrls().size(), 3);
	}

	/**
	 * SESSÃO 4 - Verificando os dados gerados a partir de novas requisições que
	 * foram montadas com o retorno da primeira requisicao ao stub da PokeAPI.
	 * 
	 * Aqui é verificado se os dados retornados pelo stub da PokeAPI foram lidos
	 * corretamente para o objeto correspondente (PokedexAbiliy).
	 * 
	 * Diferentemente da sessão anterior, serão verificados os dados das requisições
	 * dos efeitos das abilities de um PokemonPokedex gerado na primeira requisão.
	 */

	@Test
	@Order(4)
	@DisplayName("11. Verifica o 'effect' do obj PokemonAbiliy gerado pelo retorno do Stub da PokeAPI")
	public void test11() {
		pokedexControllerMocked.printPokedexPokemonFromId(120);
		String data = pokemonPokeAPIMocked.getDataPokeAPIFromUrl("https://pokeapi.co/api/v2/pokemon/any");
		PokedexPokemon pokedexPokemon = pokedexController.parsePokemonFromJson(data);
		List<String> abilities_urls = pokedexPokemon.getAbilitiesUrls();
		String pokemonAbilitiesAndEffects = pokedexController.getPokemonEffectsAndAbilities(abilities_urls);
		assertTrue(pokemonAbilitiesAndEffects.contains(
				"<br>Abilidade: analytic<br>Efeito: This Pokémon's moves have 1.3× their power when it moves last in a turn."));
		assertEquals(pokemonAbilitiesAndEffects.length(), 471);
	}	
}
package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import DAO.PokemonRepository;
import model.Pokemon;

class PokemonRepositoryTest {
	PokemonRepository pokemonRepository;

	@BeforeEach
	void setUp() throws Exception {
		pokemonRepository = new PokemonRepository();
	}

	@AfterEach
	void tearDown() throws Exception {
		pokemonRepository = null;
	}

	// MAP

	@Test
	@Order(1)
	@DisplayName("Set 'valid map' PokemonRepository")
	void SetValidMap() {
		HashMap<Integer, Pokemon> map = new HashMap<Integer, Pokemon>();
		assertTrue(pokemonRepository.setMapPokemon(map));
	}

	@Test
	@Order(1)
	@DisplayName("Set and Get 'valid map' PokemonRepository")
	void SetAndGetValidMap() {
		HashMap<Integer, Pokemon> map = new HashMap<Integer, Pokemon>();
		Pokemon poke1 = new Pokemon(0, "Ponita", "Fire", 10, 10, 100, 5);
		Pokemon poke2 = new Pokemon(1, "Rapidash", "Normal", 10, 10, 100, 5);
		map.put(0, poke1);
		map.put(1, poke2);
		assertTrue(pokemonRepository.setMapPokemon(map));
		assertEquals(map, pokemonRepository.getMapPokemon());
	}

	@Test
	@Order(1)
	@DisplayName("Set 'null map' PokemonRepository")
	void SetNullMap() {
		HashMap<Integer, Pokemon> map = null;
		assertFalse(pokemonRepository.setMapPokemon(map));
	}

	// ADD POKEMON [Valid]
	/*
	 * In the PokemonRepository the attributes are set through the Pokemon class The
	 * validation of attributes is done by the set of methods of the Pokemon class.
	 * Widely tested.
	 */

	@Test
	@Order(2)
	@DisplayName("Add 'valid Pokemon' and 'update Id' PokemonRepository")
	void AddValidPokemon() {
		assertTrue(pokemonRepository.addPokemon("Ponita", "Fire", 10, 10, 100, 5));
		assertEquals(1, pokemonRepository.getId());

	}
	
	// ADD POKEMON [null attribute]

	@Test
	@Order(3)
	@DisplayName("Add 'any attribute null [1]' PokemonRepository")
	void AddPokemonWithAnyAttributeNull1() {
		assertFalse(pokemonRepository.addPokemon(null, "Fire", 10, 10, 100, 5));
	}
	
	@Test
	@Order(3)
	@DisplayName("Add 'any attribute null [2]' PokemonRepository")
	void AddPokemonWithAnyAttributeNull2() {
		assertFalse(pokemonRepository.addPokemon("Ponita", null, 10, 10, 100, 5));

	}
	
	@Test
	@Order(3)
	@DisplayName("Add 'any attribute null [3]' PokemonRepository")
	void AddPokemonWithAnyAttributeNull3() {
		assertFalse(pokemonRepository.addPokemon("Ponita", "Fire", null, 10, 100, 5));
	}
	
	@Test
	@Order(3)
	@DisplayName("Add 'any attribute null [4]' PokemonRepository")
	void AddPokemonWithAnyAttributeNull4() {
		assertFalse(pokemonRepository.addPokemon("Ponita", "Fire", 10, null, 100, 5));
	}
	
	@Test
	@Order(3)
	@DisplayName("Add 'any attribute null [5]' PokemonRepository")
	void AddPokemonWithAnyAttributeNull5() {
		assertFalse(pokemonRepository.addPokemon("Ponita", "Fire", 10, 10, null, 5));
	}
	
	@Test
	@Order(3)
	@DisplayName("Add 'any attribute null [6]' PokemonRepository")
	void AddPokemonWithAnyAttributeNull6() {
		assertFalse(pokemonRepository.addPokemon("Ponita", "Fire", 10, 10, 100, null));
	}
	
	// ADD POKEMON [Empty atrribute]
	/*
	 * In the PokemonRepository the attributes are set through the Pokemon class The
	 * validation of attributes is done by the set of methods of the Pokemon class.
	 * Widely tested.
	 */
	
	@Test
	@Order(4)
	@DisplayName("Add 'name empty [1]' PokemonRepository")
	void AddPokemonWithEmptyName() {
		assertFalse(pokemonRepository.addPokemon("", "Fire", 10, 10, 100, 5));
	}
	
	@Test
	@Order(4)
	@DisplayName("Add 'element empty [2]' PokemonRepository")
	void AddPokemonWithEmptyElement() {
		assertFalse(pokemonRepository.addPokemon("Ponita", "", 10, 10, 100, 5));

	}
	
	// ADD POKEMON [Invalid atrribute]
	/*
	 * In the PokemonRepository the attributes are set through the Pokemon class The
	 * validation of attributes is done by the set of methods of the Pokemon class.
	 * Widely tested.
	 */
	
	@Test
	@Order(5)
	@DisplayName("Add 'invalid name' PokemonRepository")
	void AddPokemonWithInvalidName() {
		assertFalse(pokemonRepository.addPokemon("lowercase", "Fire", 10, 10, 100, 5));
	}
	
	@Test
	@Order(5)
	@DisplayName("Add 'invalid element' PokemonRepository")
	void AddPokemonWithInvalidElement() {
		assertFalse(pokemonRepository.addPokemon("Ponita", "notElement", 10, 10, 100, 5));

	}
	
	@Test
	@Order(5)
	@DisplayName("Add 'invalid attack' PokemonRepository")
	void AddPokemonWithInvalidAttack() {
		assertFalse(pokemonRepository.addPokemon("Ponita", "Fire", 0, 10, 100, 5));

	}
	
	@Test
	@Order(5)
	@DisplayName("Add 'invalid defense' PokemonRepository")
	void AddPokemonWithInvalidDefense() {
		assertFalse(pokemonRepository.addPokemon("Ponita", "Fire", 10, 0, 100, 5));
	}
	
	@Test
	@Order(5)
	@DisplayName("Add 'invalid hp' PokemonRepository")
	void AddPokemonWithInvaliHp() {
		assertFalse(pokemonRepository.addPokemon("Ponita", "Fire", 10, 10, -1, 5));
	}
	
	@Test
	@Order(5)
	@DisplayName("Add 'invalid speed' PokemonRepository")
	void AddPokemonWithInvaliSpeed() {
		assertFalse(pokemonRepository.addPokemon("Ponita", "Fire", 10, 10, 100, 0));
	}
	
	// GET POKEMON

	@Test
	@Order(6)
	@DisplayName("Get 'existing pokemon' PokemonRepository")
	void GetExistingPokemon() {
		pokemonRepository.addPokemon("Rapidash", "Normal", 10, 10, 100, 5);
		assertNotNull(pokemonRepository.getPokemon(0));
	}

	@Test
	@Order(6)
	@DisplayName("Get 'out range index pokemon' PokemonRepository")
	void GetOutRangeIndexPokemon() {
		assertNull(pokemonRepository.getPokemon(1));
	}

	@Test
	@Order(6)
	@DisplayName("Get 'negative indice' PokemonRepository")
	void GetNegativeIndicePokemon() {
		assertNull(pokemonRepository.getPokemon(-1));
	}

	// FIND POKEMON

	@Test
	@Order(7)
	@DisplayName("Find 'existing pokemon' PokemonRepository")
	void FindExistingPokemon() {
		pokemonRepository.addPokemon("Rapidash", "Normal", 10, 10, 100, 5);
		assertTrue(pokemonRepository.findPokemon(0));
	}

	@Test
	@Order(7)
	@DisplayName("Find 'not existing pokemon' PokemonRepository")
	void FindNotExistingPokemon() {
		assertFalse(pokemonRepository.findPokemon(1));
	}

	// simpleListPokemon

	@Test
	@Order(8)
	@DisplayName("simpleList PokemonRepository")
	void SimpleListPokemon() {
		pokemonRepository.addPokemon("Ditto", "Normal", 20, 20, 200, 10);
		assertEquals("Id/Nome 0-Ditto\n", pokemonRepository.simpleListPokemon());
	}

	// fullListPokemon

	@Test
	@Order(9)
	@DisplayName("fullList PokemonRepository")
	void fullListPokemon() {
		pokemonRepository.addPokemon("Ditto", "Normal", 20, 20, 200, 10);
		System.out.println(pokemonRepository.fullListPokemon());
		assertEquals("Id 0 [Ditto] Normal Atk:20 Def:20 HP:200 Speed:10", pokemonRepository.fullListPokemon());
	}

}
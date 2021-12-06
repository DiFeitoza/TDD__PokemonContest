package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import controller.PokemonController;

class PokemonControllerTest {
	private PokemonController pokemonController;

	@BeforeEach
	void setUp() throws Exception {
		pokemonController = new PokemonController();
	}

	@AfterEach
	void tearDown() throws Exception {
		pokemonController.resetMapPokemon();
	}

	// SEED Repository

	@Test
	@Order(1)
	@DisplayName("Seed repository from PokemonData")
	void seedPokemonRepository() throws Exception {
		assertTrue(pokemonController.seedPokemonRepository());
		// the seed method imports 151 pokemons from 1st generation pokemon from
		// data/pokemon.txt
		assertEquals(152, pokemonController.getRepositoryId());
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
		assertTrue(pokemonController.addPokemon("Ponita", "Fire", 10, 10, 100, 5));
		assertEquals(1, pokemonController.getRepositoryId());
	}

	// ADD POKEMON [null attribute]

	@Test
	@Order(2)
	@DisplayName("Add 'any attribute null [1]' PokemonRepository")
	void AddPokemonWithAnyAttributeNull1() {
		assertFalse(pokemonController.addPokemon(null, "Fire", 10, 10, 100, 5));
	}

	@Test
	@Order(2)
	@DisplayName("Add 'any attribute null [2]' PokemonRepository")
	void AddPokemonWithAnyAttributeNull2() {
		assertFalse(pokemonController.addPokemon("Ponita", null, 10, 10, 100, 5));

	}

	@Test
	@Order(2)
	@DisplayName("Add 'any attribute null [3]' PokemonRepository")
	void AddPokemonWithAnyAttributeNull3() {
		assertFalse(pokemonController.addPokemon("Ponita", "Fire", null, 10, 100, 5));
	}

	@Test
	@Order(2)
	@DisplayName("Add 'any attribute null [4]' PokemonRepository")
	void AddPokemonWithAnyAttributeNull4() {
		assertFalse(pokemonController.addPokemon("Ponita", "Fire", 10, null, 100, 5));
	}

	@Test
	@Order(2)
	@DisplayName("Add 'any attribute null [5]' PokemonRepository")
	void AddPokemonWithAnyAttributeNull5() {
		assertFalse(pokemonController.addPokemon("Ponita", "Fire", 10, 10, null, 5));
	}

	@Test
	@Order(2)
	@DisplayName("Add 'any attribute null [6]' PokemonRepository")
	void AddPokemonWithAnyAttributeNull6() {
		assertFalse(pokemonController.addPokemon("Ponita", "Fire", 10, 10, 100, null));
	}

	// ADD POKEMON [Empty atrribute]
	/*
	 * In the PokemonRepository the attributes are set through the Pokemon class The
	 * validation of attributes is done by the set of methods of the Pokemon class.
	 * Widely tested.
	 */

	@Test
	@Order(3)
	@DisplayName("Add 'name empty [1]' PokemonRepository")
	void AddPokemonWithEmptyName() {
		assertFalse(pokemonController.addPokemon("", "Fire", 10, 10, 100, 5));
	}

	@Test
	@Order(3)
	@DisplayName("Add 'element empty [2]' PokemonRepository")
	void AddPokemonWithEmptyElement() {
		assertFalse(pokemonController.addPokemon("Ponita", "", 10, 10, 100, 5));

	}

	// ADD POKEMON [Invalid atrribute]
	/*
	 * In the PokemonRepository the attributes are set through the Pokemon class The
	 * validation of attributes is done by the set of methods of the Pokemon class.
	 * Widely tested.
	 */

	@Test
	@Order(4)
	@DisplayName("Add 'invalid name' PokemonRepository")
	void AddPokemonWithInvalidName() {
		assertFalse(pokemonController.addPokemon("lowercase", "Fire", 10, 10, 100, 5));
	}

	@Test
	@Order(4)
	@DisplayName("Add 'invalid element' PokemonRepository")
	void AddPokemonWithInvalidElement() {
		assertFalse(pokemonController.addPokemon("Ponita", "notElement", 10, 10, 100, 5));

	}

	@Test
	@Order(4)
	@DisplayName("Add 'invalid attack' PokemonRepository")
	void AddPokemonWithInvalidAttack() {
		assertFalse(pokemonController.addPokemon("Ponita", "Fire", 0, 10, 100, 5));

	}

	@Test
	@Order(4)
	@DisplayName("Add 'invalid defense' PokemonRepository")
	void AddPokemonWithInvalidDefense() {
		assertFalse(pokemonController.addPokemon("Ponita", "Fire", 10, 0, 100, 5));
	}

	@Test
	@Order(4)
	@DisplayName("Add 'invalid hp' PokemonRepository")
	void AddPokemonWithInvaliHp() {
		assertFalse(pokemonController.addPokemon("Ponita", "Fire", 10, 10, -1, 5));
	}

	@Test
	@Order(4)
	@DisplayName("Add 'invalid speed' PokemonRepository")
	void AddPokemonWithInvaliSpeed() {
		assertFalse(pokemonController.addPokemon("Ponita", "Fire", 10, 10, 100, 0));
	}

	// GET POKEMON

	@Test
	@Order(5)
	@DisplayName("Get 'existing pokemon' PokemonRepository")
	void GetExistingPokemon() {
		pokemonController.addPokemon("Rapidash", "Normal", 10, 10, 100, 5);
		assertNotNull(pokemonController.getPokemon(0));
	}

	@Test
	@Order(5)
	@DisplayName("Get 'out range index pokemon' PokemonRepository")
	void GetOutRangeIndexPokemon() {
		assertNull(pokemonController.getPokemon(1));
	}

	@Test
	@Order(5)
	@DisplayName("Get 'negative indice' PokemonRepository")
	void GetNegativeIndicePokemon() {
		assertNull(pokemonController.getPokemon(-1));
	}

	// FIND POKEMON

	@Test
	@Order(6)
	@DisplayName("Find 'existing pokemon' PokemonRepository")
	void FindExistingPokemon() {
		pokemonController.addPokemon("Rapidash", "Normal", 10, 10, 100, 5);
		assertTrue(pokemonController.findPokemon(0));
	}

	@Test
	@Order(6)
	@DisplayName("Find 'not existing pokemon' PokemonRepository")
	void FindNotExistingPokemon() {
		assertFalse(pokemonController.findPokemon(1));
	}

	// simpleListPokemon

	@Test
	@Order(7)
	@DisplayName("simpleList PokemonRepository")
	void SimpleListPokemon() {
		pokemonController.addPokemon("Ditto", "Normal", 20, 20, 200, 10);
		assertEquals("Id/Nome 0-Ditto\n", pokemonController.simpleListPokemon());
	}

	// fullListPokemon

	@Test
	@Order(8)
	@DisplayName("fullList PokemonRepository")
	void fullListPokemon() {
		pokemonController.addPokemon("Ditto", "Normal", 20, 20, 200, 10);
		assertEquals("Id 0 [Ditto] Normal Atk:20 Def:20 HP:200 Speed:10\n", pokemonController.fullListPokemon());
	}
}
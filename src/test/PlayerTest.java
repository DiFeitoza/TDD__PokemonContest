package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import model.Player;
import model.Pokemon;

class PlayerTest {
	private Player player;

	@BeforeEach
	void setUp() throws Exception {
		player = new Player();
	}

	@AfterEach
	void tearDown() throws Exception {
		player = null;
	}

	// GET AND SET NAME

	@Test
	@Order(1)
	@DisplayName("Set and Get 'valid'  name")
	void testSetAndGetPlayerName() {
		assertTrue(player.setName("Ash"));
		assertEquals("Ash", player.getName());
	}

	@Test
	@Order(1)
	@DisplayName("Set 'more then 20 chars' in name")
	void testSetMoreThen20CharsPlayerName() {
		assertFalse(player.setName("Abcdefghij Abcdefghij"));
	}

	@Test
	@Order(1)
	@DisplayName("Set 'Empty string' in name")
	void testSetEmptyStringName() {
		assertFalse(player.setName(""));
	}

	@Test
	@Order(1)
	@DisplayName("Set 'null' in name")
	void testSetNullName() {
		assertFalse(player.setName(null));
	}

	// GET and SET ArrayList

	@Test
	@Order(2)
	@DisplayName("Set and Get 'valid array' pokemon")
	void testSetAndGetValidArrayPokemon() {
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		pokemons.add(new Pokemon());
		assertTrue(player.setPokemons(pokemons));
	}

	@Test
	@Order(2)
	@DisplayName("Set and Get 'empty' pokemon")
	void testSetEmptyArrayPokemon() {
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		assertFalse(player.setPokemons(pokemons));
	}

	@Test
	@Order(2)
	@DisplayName("Set 'More then 6 pokemon in array' pokemon")
	void testSetMoreThan6PokemonInArray() {
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		for (int i = 0; i < 7; i++) {
			pokemons.add(new Pokemon(1, "Evee", "Normal", 1, 1, 1, 1));
		}
		assertFalse(player.setPokemons(pokemons));
	}
}

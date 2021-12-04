package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import model.Pokemon;

/*
 *	ID: inteiro não negativo
 *	HP: inteiro entre 0 e 999
 *  ATTACK, DEFENSE, SPEED: inteiro entre 1 e 999
 *  NAME:
 *  	Limitado a vinte caracteres.						
 *  	Regra de escrita: "[A-Z]{1}[a-z'?\\\\.?]+([- ]?[A-Z]{1}[a-z'?\\\\.?]+)?[♀♂]?"
 *  		Começa com uma maiúscula
 *  		seguida de um ou mais caracteres, sendo
 *  		letra(s) minúsculas, com o sem a presença de uma aspa simples ou um ponto
 *  		podendo ser seguida de um hifen ou um espaço
 *  		seguida de um ou mais caracteres, sendo
 *  		letra(s) minúsculas, com o sem a presença de uma aspa simples ou um ponto
 *  		podendo terminar, tanto a primeira palavra, como a palavra composta,
 *  		ou pelo caracter ♀ ou pelo ♂, mas não por ambos.
 *  ELEMENT: apenas um dos (16) seguintes valores:
 *  	Bug, Dragon, Electric, Fairy, Fighting, Fire,
 *  	Ghost, Grass, Ground, Ice, Normal, Poison, Psychic,
 *  	Rock, Steel, Water;
 *  toString: retorna o formato "Id 1 [Ditto] Normal Atk:2 Def:1 HP:5 Speed:2"
 *	Obs. nenhum atributo deve aceitar null	
 */

@TestMethodOrder(OrderAnnotation.class)
class PokemonTest {
	private Pokemon pokemon;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		Integer id = 1;
		String name = "Ditto", element = "Normal";
		Integer attack = 2, defense = 1, hp = 5, speed = 2;
		pokemon = new Pokemon(id, name, element, attack, defense, hp, speed);
	}

	@AfterEach
	void tearDown() throws Exception {
		pokemon = null;
	}

	// ID

	@Test
	@Order(1)
	@DisplayName("Set and Get 'Valid' - pokemon.id")
	void setAndGetValidPokemonId() {
		assertTrue(pokemon.setId(1));
		assertEquals(1, pokemon.getId());
	}

	@Test
	@Order(1)
	@DisplayName("Set 'Null' - pokemon.id")
	void setNullPokemonId() {
		assertFalse(pokemon.setId(null));
	}

	@Test
	@Order(1)
	@DisplayName("Set 'Negative' - pokemon.id")
	void setNegativePokemonId() {
		assertFalse(pokemon.setId(-1));
	}

	// ATTACK

	@Test
	@Order(2)
	@DisplayName("Set and Get 'Valid' - pokemon.attack")
	void setAndGetValidPokemonAttack() {
		assertTrue(pokemon.setAttack(5));
		assertEquals(5, pokemon.getAttack());
	}

	@Test
	@Order(2)
	@DisplayName("Set 'Null' - pokemon.attack")
	void setNullPokemonAttack() {
		assertFalse(pokemon.setAttack(null));
	}

	@Test
	@Order(2)
	@DisplayName("Set '0' - pokemon.attack")
	void setZeroPokemonAttack() {
		assertFalse(pokemon.setAttack(0));
	}

	@Test
	@Order(2)
	@DisplayName("Set 'Negative' - pokemon.attack")
	void setNegativePokemonAttack() {
		assertFalse(pokemon.setAttack(-1));
	}

	@Test
	@Order(2)
	@DisplayName("Set 'value > 999' - pokemon.attack")
	void setMoreThan999PokemonAttack() {
		assertFalse(pokemon.setAttack(1000));
	}

	// DEFENSE

	@Test
	@Order(3)
	@DisplayName("Set and Get 'Valid' - pokemon.defense")
	void setAndGetValidPokemonDefense() {
		assertTrue(pokemon.setDefense(10));
		assertEquals(10, pokemon.getDefense());
	}

	@Test
	@Order(3)
	@DisplayName("Set 'Null' - pokemon.defense")
	void setNullPokemonDefense() {
		assertFalse(pokemon.setDefense(null));
	}

	@Test
	@Order(3)
	@DisplayName("Set '0' - pokemon.defense")
	void setZeroPokemonDefense() {
		assertFalse(pokemon.setDefense(0));
	}

	@Test
	@Order(3)
	@DisplayName("Set 'Negative' - pokemon.defense")
	void setNegativePokemonDefense() {
		assertFalse(pokemon.setDefense(-1));
	}

	@Test
	@Order(3)
	@DisplayName("Set 'value > 999' - pokemon.defense")
	void setMoreThan999PokemonDefense() {
		assertFalse(pokemon.setDefense(1000));
	}

	// SPEED

	@Test
	@Order(4)
	@DisplayName("Set and Get 'Valid' - pokemon.speed")
	void setAndGetValidPokemonSpeed() {
		assertTrue(pokemon.setSpeed(10));
		assertEquals(10, pokemon.getSpeed());
	}

	@Test
	@Order(4)
	@DisplayName("Set 'Null' - pokemon.speed")
	void setNullPokemonSpeed() {
		assertFalse(pokemon.setSpeed(null));
	}

	@Test
	@Order(4)
	@DisplayName("Set '0' - pokemon.speed")
	void setZeroPokemonSpeed() {
		assertFalse(pokemon.setSpeed(0));
	}

	@Test
	@Order(4)
	@DisplayName("Set 'Negative' - pokemon.speed")
	void setNegativePokemonSpeed() {
		assertFalse(pokemon.setSpeed(-1));
	}

	@Test
	@Order(4)
	@DisplayName("Set 'value > 999' - pokemon.speed")
	void setMoreThan999PokemonSpeed() {
		assertFalse(pokemon.setSpeed(1000));
	}

	// HP

	@Test
	@Order(5)
	@DisplayName("Set and Get 'Valid' - pokemon.hp")
	void setAndGetValidPokemonHp() {
		assertTrue(pokemon.setHp(10));
		assertEquals(10, pokemon.getHp());
	}

	@Test
	@Order(5)
	@DisplayName("Set 'Null' - pokemon.hp")
	void setNullPokemonHp() {
		assertFalse(pokemon.setHp(null));
	}

	@Test
	@Order(5)
	@DisplayName("Set 'Negative' - pokemon.hp")
	void setNegativePokemonHp() {
		assertFalse(pokemon.setHp(-1));
	}

	@Test
	@Order(5)
	@DisplayName("Set 'value > 999' - pokemon.hp")
	void setMoreThan999PokemonHp() {
		assertFalse(pokemon.setHp(1000));
	}

	// NAME

	@Test
	@Order(6)
	@DisplayName("Set and Get - pokemon.name")
	void setAndGetValidPokemonName() {
		assertTrue(pokemon.setName("Slowbro"));
		assertEquals("Slowbro", pokemon.getName());
	}

	@Test
	@Order(6)
	@DisplayName("Set 'Valid Names' - pokemon.name")
	void setValidPokemonNames() {
		assertTrue(pokemon.setName("MissingNo."));
		assertTrue(pokemon.setName("Mr. Mime"));
		assertTrue(pokemon.setName("Farfetch'd"));
		assertTrue(pokemon.setName("Nidoran♂"));
		assertTrue(pokemon.setName("Nidoran♀"));
	}

	@Test
	@Order(6)
	@DisplayName("Set 'Null' - pokemon.name")
	void setNullPokemonName() {
		assertFalse(pokemon.setName(null));
	}

	@Test
	@Order(6)
	@DisplayName("Set 'Empty' - pokemon.name")
	void setEmptyPokemonName() {
		assertFalse(pokemon.setName(""));
	}

	@Test
	@Order(6)
	@DisplayName("Set 'More one space' - pokemon.name")
	void setMoreOneSpacePokemonName() {
		assertFalse(pokemon.setName("Double  Space"));
	}

	@Test
	@Order(6)
	@DisplayName("Set 'Initial not capitalized' - pokemon.name")
	void setInitialNotCapitalizedPokemonName() {
		assertFalse(pokemon.setName("pikachu"));
	}

	@Test
	@Order(6)
	@DisplayName("Set 'One letter' - pokemon.name")
	void setOneLetterPokemonName() {
		assertFalse(pokemon.setName("L"));
	}

	@Test
	@Order(6)
	@DisplayName("Ser 'More than two names' - pokemon.name")
	void setMoreThanTwoPokemonName() {
		assertFalse(pokemon.setName("Blastoise Da Silva"));
	}

	@Test
	@Order(6)
	@DisplayName("Set 'More than 20 chars' - pokemon.name")
	void setMoreThan20CharsPokemonName() {
		assertFalse(pokemon.setName("Abcdefghij Abcdefghij"));
	}

	@Test
	@Order(6)
	@DisplayName("Set 'Number in name' - pokemon.name")
	void setNumberInPokemonName() {
		assertFalse(pokemon.setName("Charizard2"));
	}

	// ELEMENT

	@Test
	@Order(7)
	@DisplayName("Set and Get 'Valid' - pokemon.element")
	void setAndGetValidPokemonElement() {
		assertTrue(pokemon.setElement("Ice"));
		assertEquals("Ice", pokemon.getElement());
	}

	@Test
	@Order(7)
	@DisplayName("Set 'Null' - pokemon.element")
	void setNullPokemonElement() {
		assertFalse(pokemon.setElement(null));
	}

	@Test
	@Order(7)
	@DisplayName("Set 'Value out List of 16 possible elements' - pokemon.element")
	void setValueOutListOf16PossiblePokemonElement() {
		assertFalse(pokemon.setElement("Storm"));
	}

	@Test
	@Order(8)
	@DisplayName("Test pokemon.toString ")
	void pokemonToString() {
		assertEquals("Id 1 [Ditto] Normal Atk:2 Def:1 HP:5 Speed:2", pokemon.toString());
	}

}

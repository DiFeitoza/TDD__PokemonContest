package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import model.Battle;
import model.Pokemon;

class BattleTest {
	Battle battle;

	@BeforeEach
	void setUp() throws Exception {
		battle = new Battle();
	}

	@AfterEach
	void tearDown() throws Exception {
		battle = null;
	}

	// Instance Battle

	@Test
	@Order(1)
	@DisplayName("Get 'instance' of Battle")
	void testGetInstanceOfBattle() {
		Battle battle = Battle.getInstance();
		assertNotNull(battle);
	}

	@Test
	@Order(1)
	@DisplayName("Get 'same instance' of Battle")
	void testGetSameInstanceOfBattle() {
		Battle battle1 = Battle.getInstance();
		Battle battle2 = Battle.getInstance();
		assertNotNull(battle1);
		assertEquals(battle1, battle2);
	}

	// AmountOfPokemons (for player)

	@Test
	@Order(2)
	@DisplayName("Get and Set 'valid' amountOfPokemons")
	void testGetAndSetValidAmountOfPokemon() {
		battle.setAmountOfPokemon(1);
		assertEquals(1, battle.getAmountOfPokemon());
	}

	@Test
	@Order(2)
	@DisplayName("Set 'number greather than 6' amountOfPokemons")
	void testSetNumberGratherThan6AmountOfPokemon() {
		assertFalse(battle.setAmountOfPokemon(7));
	}

	@Test
	@Order(2)
	@DisplayName("Set 'number less than 1' amountOfPokemons")
	void testSetNumberLessThan1AmountOfPokemon() {
		assertFalse(battle.setAmountOfPokemon(0));
	}

	// CountFights

	@Test
	@Order(3)
	@DisplayName("Get and Set 'valid' countFights")
	void testGetAndSetValidCountFights() {
		battle.setCountFights(1);
		assertEquals(1, battle.getCountFights());
	}

	@Test
	@Order(3)
	@DisplayName("Set 'number greather than 11' countFights")
	void testSetNumberGratherThan6CountFights() {
		assertFalse(battle.setCountFights(12));
	}

	@Test
	@Order(3)
	@DisplayName("Set 'number less than 1' countFights")
	void testSetNumberLessThan1CountFights() {
		assertFalse(battle.setCountFights(-1));
	}

	// Player One

	@Test
	@Order(4)
	@DisplayName("Set playerOne with 'array out of range (length < 1)'")
	void testSetPlayerOneWithArrayLessThan1() {
		assertFalse(battle.setPlayerOne("Ash", new ArrayList<Pokemon>()));
	}

	@Test
	@Order(4)
	@DisplayName("Set playerOne with 'array out of range (length > 6)'")
	void testSetPlayerOneWithArrayGreaterThan6() {
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		for (int i = 0; i < 7; i++) {
			pokemons.add(new Pokemon());
		}
		assertFalse(battle.setPlayerOne("Ash", pokemons));
	}

	@Test
	@Order(4)
	@DisplayName("Set playerOne with 'name with more than 20 characters'")
	void testSetPlayerOneNameMoreThan20Chars() {
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		for (int i = 0; i < 7; i++) {
			pokemons.add(new Pokemon());
		}
		assertFalse(battle.setPlayerOne("abcdefgij abcdefghij", pokemons));
	}

	@Test
	@Order(4)
	@DisplayName("Set playerOne with 'name empty'")
	void testSetPlayerOneWithNameEmpty() {
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		for (int i = 0; i < 7; i++) {
			pokemons.add(new Pokemon());
		}
		assertFalse(battle.setPlayerOne("", pokemons));
	}

	@Test
	@Order(4)
	@DisplayName("Set playerOne with 'name null'")
	void testSetPlayerOneWithNameNull() {
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		for (int i = 0; i < 7; i++) {
			pokemons.add(new Pokemon());
		}
		assertFalse(battle.setPlayerOne(null, pokemons));
	}

	@Test
	@Order(4)
	@DisplayName("Set playerOne with 'valid values'")
	void testSetPlayerOneWithValidValues() {
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		battle.setAmountOfPokemon(6);
		for (int i = 0; i < 6; i++) {
			pokemons.add(new Pokemon());
		}
		assertTrue(battle.setPlayerOne("Ash", pokemons));
	}

	// PlayerTwo

	@Test
	@Order(5)
	@DisplayName("Set playerTwo with 'array out of range (length < 1)'")
	void testSetPlayerTwoWithArrayLessThan1() {
		assertFalse(battle.setPlayerTwo("Ash", new ArrayList<Pokemon>()));
	}

	@Test
	@Order(5)
	@DisplayName("Set playerTwo with 'array out of range (length > 6)'")
	void testSetPlayerTwoWithArrayGreaterThan6() {
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		for (int i = 0; i < 7; i++) {
			pokemons.add(new Pokemon());
		}
		assertFalse(battle.setPlayerTwo("Ash", pokemons));
	}

	@Test
	@Order(5)
	@DisplayName("Set playerTwo with 'name with more than 20 characters'")
	void testSetPlayerTwoNameMoreThan20Chars() {
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		for (int i = 0; i < 7; i++) {
			pokemons.add(new Pokemon());
		}
		assertFalse(battle.setPlayerTwo("abcdefgij abcdefghij", pokemons));
	}

	@Test
	@Order(5)
	@DisplayName("Set playerTwo with 'name empty'")
	void testSetPlayerTwoWithNameEmpty() {
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		for (int i = 0; i < 7; i++) {
			pokemons.add(new Pokemon());
		}
		assertFalse(battle.setPlayerTwo("", pokemons));
	}

	@Test
	@Order(5)
	@DisplayName("Set playerTwo with 'name null'")
	void testSetPlayerTwoWithNameNull() {
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		for (int i = 0; i < 7; i++) {
			pokemons.add(new Pokemon());
		}
		assertFalse(battle.setPlayerTwo(null, pokemons));
	}

	@Test
	@Order(5)
	@DisplayName("Set playerTwo with 'valid values'")
	void testSetPlayerTwoWithValidValues() {
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		battle.setAmountOfPokemon(6);
		for (int i = 0; i < 6; i++) {
			pokemons.add(new Pokemon());
		}
		assertTrue(battle.setPlayerTwo("Ash", pokemons));
	}

	// damageMultiplier

	/*
	 * a pokemon type is defined when a pokemon is instantiated, the control of
	 * allowed types is done by the Pokemon.java class. Therefore, invalid entries
	 * of element types will not be tested. Although the Battle class returns a
	 * message if it does.
	 */

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Bug and Dark'")
	void testCalculateDamageMultiplier1() {
		Double mult = battle.damageMultiplier("Bug", "Dark");
		assertEquals(2.0, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Bug and Fire'")
	void testCalculateDamageMultiplier2() {
		Double mult = battle.damageMultiplier("Bug", "Fire");
		assertEquals(0.5, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Bug and Fighting'")
	void testCalculateDamageMultiplier3() {
		Double mult = battle.damageMultiplier("Bug", "Fighting");
		assertEquals(0.5, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Dragon and Dragon'")
	void testCalculateDamageMultiplier4() {
		Double mult = battle.damageMultiplier("Dragon", "Dragon");
		assertEquals(2.0, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Dragon and Stell'")
	void testCalculateDamageMultiplier5() {
		Double mult = battle.damageMultiplier("Dragon", "Stell");
		assertEquals(0.5, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Dragon and Fairy'")
	void testCalculateDamageMultiplier6() {
		Double mult = battle.damageMultiplier("Dragon", "Fairy");
		assertEquals(0.2, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Electric and Water'")
	void testCalculateDamageMultiplier7() {
		Double mult = battle.damageMultiplier("Electric", "Water");
		assertEquals(2.0, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Electric and Grass'")
	void testCalculateDamageMultiplier8() {
		Double mult = battle.damageMultiplier("Electric", "Grass");
		assertEquals(0.5, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Electric and Ground'")
	void testCalculateDamageMultiplier9() {
		Double mult = battle.damageMultiplier("Electric", "Ground");
		assertEquals(0.2, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Fairy and Dark'")
	void testCalculateDamageMultiplier10() {
		Double mult = battle.damageMultiplier("Fairy", "Dark");
		assertEquals(2.0, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Fairy and Poison'")
	void testCalculateDamageMultiplier11() {
		Double mult = battle.damageMultiplier("Fairy", "Poison");
		assertEquals(0.5, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Bug and Fighting'")
	void testCalculateDamageMultiplier12() {
		Double mult = battle.damageMultiplier("Bug", "Fighting");
		assertEquals(0.5, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Fighting and Ice'")
	void testCalculateDamageMultiplier13() {
		Double mult = battle.damageMultiplier("Fighting", "Ice");
		assertEquals(2.0, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Fighting and Flying'")
	void testCalculateDamageMultiplier14() {
		Double mult = battle.damageMultiplier("Fighting", "Flying");
		assertEquals(0.5, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Fighting and Poison'")
	void testCalculateDamageMultiplier15() {
		Double mult = battle.damageMultiplier("Fighting", "Poison");
		assertEquals(0.5, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Fighting and Ghost'")
	void testCalculateDamageMultiplier16() {
		Double mult = battle.damageMultiplier("Fighting", "Ghost");
		assertEquals(0.2, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Fire and Bug'")
	void testCalculateDamageMultiplier17() {
		Double mult = battle.damageMultiplier("Fire", "Bug");
		assertEquals(2.0, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Fire and Fire'")
	void testCalculateDamageMultiplier18() {
		Double mult = battle.damageMultiplier("Fire", "Fire");
		assertEquals(0.5, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Flying and Grass'")
	void testCalculateDamageMultiplier19() {
		Double mult = battle.damageMultiplier("Flying", "Grass");
		assertEquals(2.0, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Flying and Steel'")
	void testCalculateDamageMultiplier20() {
		Double mult = battle.damageMultiplier("Flying", "Steel");
		assertEquals(0.5, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Ghost and Psychic'")
	void testCalculateDamageMultiplier21() {
		Double mult = battle.damageMultiplier("Ghost", "Psychic");
		assertEquals(2.0, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Ghost and Dark'")
	void testCalculateDamageMultiplier22() {
		Double mult = battle.damageMultiplier("Ghost", "Dark");
		assertEquals(0.5, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Ghost and Normal'")
	void testCalculateDamageMultiplier23() {
		Double mult = battle.damageMultiplier("Ghost", "Normal");
		assertEquals(0.2, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Grass and Water'")
	void testCalculateDamageMultiplier24() {
		Double mult = battle.damageMultiplier("Grass", "Water");
		assertEquals(2.0, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Grass and Steel'")
	void testCalculateDamageMultiplier25() {
		Double mult = battle.damageMultiplier("Grass", "Steel");
		assertEquals(0.5, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Ground and Poison'")
	void testCalculateDamageMultiplier26() {
		Double mult = battle.damageMultiplier("Ground", "Poison");
		assertEquals(2.0, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Ground and Bug'")
	void testCalculateDamageMultiplier27() {
		Double mult = battle.damageMultiplier("Ground", "Bug");
		assertEquals(0.5, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Ground and Flying'")
	void testCalculateDamageMultiplier28() {
		Double mult = battle.damageMultiplier("Ground", "Flying");
		assertEquals(0.2, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Ice and Dragon'")
	void testCalculateDamageMultiplier29() {
		Double mult = battle.damageMultiplier("Ice", "Dragon");
		assertEquals(2.0, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Ice and Bug'")
	void testCalculateDamageMultiplier30() {
		Double mult = battle.damageMultiplier("Ice", "Bug");
		assertEquals(0.5, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Normal and Rock'")
	void testCalculateDamageMultiplier31() {
		Double mult = battle.damageMultiplier("Normal", "Rock");
		assertEquals(0.5, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Normal and Ghost'")
	void testCalculateDamageMultiplier32() {
		Double mult = battle.damageMultiplier("Normal", "Ghost");
		assertEquals(0.2, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Poison and Fairy'")
	void testCalculateDamageMultiplier33() {
		Double mult = battle.damageMultiplier("Poison", "Fairy");
		assertEquals(2.0, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Poison and Poison'")
	void testCalculateDamageMultiplier34() {
		Double mult = battle.damageMultiplier("Poison", "Poison");
		assertEquals(0.5, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Poison and Steel'")
	void testCalculateDamageMultiplier35() {
		Double mult = battle.damageMultiplier("Poison", "Steel");
		assertEquals(0.2, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Psychic and Fighting'")
	void testCalculateDamageMultiplier36() {
		Double mult = battle.damageMultiplier("Psychic", "Fighting");
		assertEquals(2.0, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Psychic and Stell'")
	void testCalculateDamageMultiplier37() {
		Double mult = battle.damageMultiplier("Psychic", "Stell");
		assertEquals(0.5, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Psychic and Dark'")
	void testCalculateDamageMultiplier38() {
		Double mult = battle.damageMultiplier("Psychic", "Dark");
		assertEquals(0.2, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Rock and Bug'")
	void testCalculateDamageMultiplier39() {
		Double mult = battle.damageMultiplier("Rock", "Bug");
		assertEquals(2.0, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Rock and Fighting'")
	void testCalculateDamageMultiplier40() {
		Double mult = battle.damageMultiplier("Rock", "Fighting");
		assertEquals(0.5, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Steel and Fairy'")
	void testCalculateDamageMultiplier41() {
		Double mult = battle.damageMultiplier("Steel", "Fairy");
		assertEquals(2.0, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Steel and Water'")
	void testCalculateDamageMultiplier42() {
		Double mult = battle.damageMultiplier("Steel", "Water");
		assertEquals(0.5, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Water and Fire'")
	void testCalculateDamageMultiplier43() {
		Double mult = battle.damageMultiplier("Water", "Fire");
		assertEquals(2.0, mult);
	}

	@Test
	@Order(5)
	@DisplayName("Calculate damageMultiplier 'Water and Water'")
	void testCalculateDamageMultiplier44() {
		Double mult = battle.damageMultiplier("Water", "Water");
		assertEquals(0.5, mult);
	}

	// Damage Attack

	@Test
	@Order(6)
	@DisplayName("Test 'damage attack less than 1' attackDamage")
	void testDamageAttackLessThan1() {
		Pokemon pokeAtk = new Pokemon(0, "Ponita", "Fire", 10, 10, 100, 5);
		Pokemon pokeDef = new Pokemon(1, "Blastoise", "Water", 10, 10, 100, 5);
		Double mult = battle.damageMultiplier("Fire", "Water"); // mult = 0.5
		battle.attackDamage(mult, pokeAtk, pokeDef);
		assertEquals(99, pokeDef.getHp());
	}

	@Test
	@Order(6)
	@DisplayName("Test 'damage attack greather than 1' attackDamage")
	void testDamageAttackGreatherThan1() {
		Pokemon pokeAtk = new Pokemon(1, "Blastoise", "Water", 10, 10, 100, 5);
		Pokemon pokeDef = new Pokemon(0, "Ponita", "Fire", 10, 10, 100, 5);
		Double mult = battle.damageMultiplier("Water", "Fire"); // mult = 2.0
		battle.attackDamage(mult, pokeAtk, pokeDef);
		assertEquals(90, pokeDef.getHp());
	}

	@Test
	@Order(6)
	@DisplayName("Test 'damage attack greather than enemy hp' attackDamage")
	void testDamageAttackGreatherThanEnemyHp() {
		Pokemon pokeAtk = new Pokemon(1, "Blastoise", "Water", 10, 10, 10, 5);
		Pokemon pokeDef = new Pokemon(0, "Ponita", "Fire", 10, 10, 5, 5);
		Double mult = battle.damageMultiplier("Water", "Fire"); // mult = 2.0
		battle.attackDamage(mult, pokeAtk, pokeDef);
		assertEquals(0, pokeDef.getHp());
	}
}

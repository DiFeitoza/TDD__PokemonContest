package controller;

import java.util.ArrayList;

import model.Battle;
import model.Player;
import model.Pokemon;

public class BattleController {
	private static BattleController battleController;
	private Battle battle = Battle.getInstance();

	public static BattleController getInstance() {
		if (battleController == null) {
			battleController = new BattleController();
		}
		return battleController;
	}

	public int getCountFights() {
		return battle.getCountFights();
	}

	public boolean setCountFights(int countFights) {
		return battle.setCountFights(countFights);
	}

	public int getAmountOfPokemon() {
		return battle.getAmountOfPokemon();
	}

	public boolean setAmountOfPokemon(int amountOfPokemon) {
		return battle.setAmountOfPokemon(amountOfPokemon);
	}

	public Double damageMultiplier(String type1, String type2) {
		return battle.damageMultiplier(type1, type2);
	}

	public void attackDamage(double mult, Pokemon pokeAttack, Pokemon pokeDefense) {
		battle.attackDamage(mult, pokeAttack, pokeDefense);
	}

	public Player getPlayerOne() {
		return battle.getPlayerOne();
	}

	public boolean setPlayerOne(String name, ArrayList<Pokemon> pokemons) {
		return battle.setPlayerOne(name, pokemons);
	}

	public Player getPlayerTwo() {
		return battle.getPlayerTwo();
	}

	public boolean setPlayerTwo(String name, ArrayList<Pokemon> pokemons) {
		return battle.setPlayerTwo(name, pokemons);
	}
}
package model;

import java.util.ArrayList;

public class Battle {
	private int countFights;
	private int damageMultiplierP1, damageMultiplierP2;
	private Player playerOne, playerTwo;
	private int fighterP1, fighterP2;
	private ArrayList<String> resultsOfBattleP1;
	private ArrayList<String> resultsOfBattleP2;

	public int getCountFights() {
		return countFights;
	}

	public void setCountFights(int countFights) {
		this.countFights = countFights;
	}

	public int getDamageMultiplierP1() {
		return damageMultiplierP1;
	}

	public void setDamageMultiplierP1(int damageMultiplierP1) {
		this.damageMultiplierP1 = damageMultiplierP1;
	}

	public int getDamageMultiplierP2() {
		return damageMultiplierP2;
	}

	public void setDamageMultiplierP2(int damageMultiplierP2) {
		this.damageMultiplierP2 = damageMultiplierP2;
	}

	public Player getPlayerOne() {
		return playerOne;
	}

	public void setPlayerOne(Player playerOne) {
		this.playerOne = playerOne;
	}

	public Player getPlayerTwo() {
		return playerTwo;
	}

	public void setPlayerTwo(Player playerTwo) {
		this.playerTwo = playerTwo;
	}

	public int getFighterP1() {
		return fighterP1;
	}

	public void setFighterP1(int fighterP1) {
		this.fighterP1 = fighterP1;
	}

	public int getFighterP2() {
		return fighterP2;
	}

	public void setFighterP2(int fighterP2) {
		this.fighterP2 = fighterP2;
	}

	public ArrayList<String> getResultsOfBattleP1() {
		return resultsOfBattleP1;
	}

	public void setResultsOfBattleP1(ArrayList<String> resultsOfBattleP1) {
		this.resultsOfBattleP1 = resultsOfBattleP1;
	}

	public ArrayList<String> getResultsOfBattleP2() {
		return resultsOfBattleP2;
	}

	public void setResultsOfBattleP2(ArrayList<String> resultsOfBattleP2) {
		this.resultsOfBattleP2 = resultsOfBattleP2;
	}
}

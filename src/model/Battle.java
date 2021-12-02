package model;

import java.util.ArrayList;

public class Battle {
	int amountOfFights = 3;
	ArrayList<Pokemon> player = new ArrayList<Pokemon>(amountOfFights);
	ArrayList<Pokemon> machine = new ArrayList<Pokemon>(amountOfFights);

	void compareType(float mult, String type1, String type2) {
	}

	void attakDamage(int typeAtack, float mult, Pokemon pokeAtack, int a, Pokemon pokeDefense, int d) {
	}
	
	void printStatusBattle(Pokemon pokeStatus, int i) {
	}
	
	void battle() {
	}
}

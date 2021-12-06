package model;

import java.util.ArrayList;

public class Player {
	String name;
	ArrayList<Pokemon> pokemons;

	public Player(String name, ArrayList<Pokemon> pokemons) {
		this.name = name;
		this.pokemons = pokemons;
	}

	public Player() {
	}

	public String getName() {
		return name;
	}

	public boolean setName(String name) {
		if (name != null && name.length() <= 20 && name != "") {
			this.name = name;
			return true;
		}
		return false;
	}

	public ArrayList<Pokemon> getPokemons() {
		return pokemons;
	}

	public boolean setPokemons(ArrayList<Pokemon> pokemons) {
		if (pokemons.isEmpty() || pokemons.size() > 6 || pokemons == null) {
			return false;
		}
		this.pokemons = pokemons;
		return true;
	}
}

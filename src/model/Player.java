package model;

import java.util.ArrayList;

public class Player {
	String name;
	ArrayList<Pokemon> pokemons;
	
	public Player(String name, ArrayList<Pokemon> pokemons) {
		this.name = name;
		this.pokemons = pokemons;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Pokemon> getPokemons() {
		return pokemons;
	}

	public void setPokemons(ArrayList<Pokemon> pokemons) {
		this.pokemons = pokemons;
	}
}

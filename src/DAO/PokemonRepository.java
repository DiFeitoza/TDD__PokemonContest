package DAO;

import java.util.HashMap;

import model.Pokemon;

public class PokemonRepository {
	private static PokemonRepository pokemonRepository;
	private HashMap<Integer, Pokemon> mapPokemon = new HashMap<Integer, Pokemon>();
	private int id = 0;

	public static PokemonRepository getInstance() {
		if (pokemonRepository == null) {
			pokemonRepository = new PokemonRepository();
		}
		return pokemonRepository;
	}

	public HashMap<Integer, Pokemon> getMapPokemon() {
		return mapPokemon;
	}

	public void setMapPokemon(HashMap<Integer, Pokemon> mapPokemon) {
		this.mapPokemon = mapPokemon;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
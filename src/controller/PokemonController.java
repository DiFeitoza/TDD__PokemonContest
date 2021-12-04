package controller;

import java.io.IOException;
import java.util.HashMap;

import DAO.PokemonRepository;
import data.PokemonData;
import model.Pokemon;

public class PokemonController {
	private static PokemonController pokemonController;
	private PokemonRepository pokemonRepository = PokemonRepository.getInstance();
	private PokemonData pokemonData = PokemonData.getInstance();

	public PokemonController() {
	}

	public static PokemonController getInstance() {
		if (pokemonController == null) {
			pokemonController = new PokemonController();
		}
		return pokemonController;
	}

	public boolean seedPokemonRepository() throws IOException {
		HashMap<Integer, Pokemon> allPokemonsInData = pokemonData.generateMapFromDataPokemon();
		return pokemonRepository.setMapPokemon(allPokemonsInData);
	}

	public Pokemon getPokemon(int id) {
		return pokemonRepository.getPokemon(id);
	}

	public String simpleListPokemon() {
		return pokemonRepository.simpleListPokemon();
	}

	public String fullListPokemon() {
		return pokemonRepository.fullListPokemon();
	}
}

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

	public int getRepositoryId() {
		return pokemonRepository.getId();
	}

	public boolean seedPokemonRepository() throws IOException {
		HashMap<Integer, Pokemon> allPokemonsInData = pokemonData.generateMapFromDataPokemon();
		return pokemonRepository.setMapPokemon(allPokemonsInData);
	}
	
	public boolean rebootRepository() throws IOException {
		HashMap<Integer, Pokemon> allPokemonsInData = pokemonData.rebootData();
		return pokemonRepository.setMapPokemon(allPokemonsInData);
	}

	public HashMap<Integer, Pokemon> getMapPokemon() {
		return pokemonRepository.getMapPokemon();
	}

	public boolean resetMapPokemon() {
		return pokemonRepository.resetMapPokemon();
	}

	public boolean addPokemon(String name, String element, Integer attack, Integer defense, Integer hp, Integer speed) {
		return pokemonRepository.addPokemon(name, element, attack, defense, hp, speed);
	}

	public boolean findPokemon(int id) {
		return pokemonRepository.findPokemon(id);
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

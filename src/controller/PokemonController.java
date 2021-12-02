package controller;

import java.util.Set;

import DAO.PokemonRepository;
import data.PokemonData;

public class PokemonController {
	private PokemonRepository pokemonRepository = PokemonRepository.getInstance();
	private PokemonData pokemonData = PokemonData.getInstance();
	
	public PokemonController() {
	}
}
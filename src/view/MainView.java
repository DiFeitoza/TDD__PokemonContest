package view;

import java.io.IOException;

import controller.PokemonController;
import data.PokemonData;

public class MainView {
	private static PokemonController pokemonController = PokemonController.getInstance();

	public static void main(String[] args) {
		boolean boolSeed = false;
		System.out.println("Entrei");
		
		try {
			boolSeed = pokemonController.seedPokemonRepository();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if( boolSeed ) {
			System.out.println("Jogo carregado com sucesso!");
		} else {			
			System.out.println("Erro no import dos dados");
		}
	}
}
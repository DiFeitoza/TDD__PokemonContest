package view;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import controller.BattleController;
import controller.PokemonController;
import model.Pokemon;

public class ChoosePokemonView {
	private static ChoosePokemonView choosePokemonView;
	private static PokemonController pokemonController = PokemonController.getInstance();
	private static BattleController battleController = BattleController.getInstance();
	private static Scanner sc = new Scanner(System.in);

	public static ChoosePokemonView getInstance() {
		if (choosePokemonView == null) {
			choosePokemonView = new ChoosePokemonView();
		}
		return choosePokemonView;
	}

	public static int randonNumber(int min, int max) {
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}

	public static void amountOfPokemonsToFight() {
		String input = "";
		int amountOfPokemon = -1;

		while (amountOfPokemon < 1 || amountOfPokemon > 6) {
			System.out.println("Digite o número de pokemons entre 1 e 6");
			input = sc.nextLine();
			if (input.matches("\\d{1}")) {
				amountOfPokemon = Integer.parseInt(input);
			}
		}
		battleController.setAmountOfPokemon(amountOfPokemon);
	}

	public static void validMachineChoice(ArrayList<Pokemon> pokeList) {
		int randonId;
		boolean ok = true;
		while (ok) {
			randonId = randonNumber(1, 127);
			for (Pokemon p : pokeList) {
				if (p.getId() == randonId) {
					System.out.println("Gary está pensando qual id escolher..." + "\n");
					ok = false;
				}
			}
			if (ok) {
				Pokemon poke = pokemonController.getPokemon(randonId);
				if (poke != null) {
					pokeList.add(pokemonController.getPokemon(randonId));
					ok = false;
				} else
					ok = true;
			} else
				ok = true;
		}
	}

	public static void validChoice(ArrayList<Pokemon> pokeList, int i) {
		boolean ok = true;
		int chooseId;
		while (ok) {
			String input = sc.nextLine();
			if (input.matches("\\d{1,3}")) {
				chooseId = Integer.parseInt(input);
				if (chooseId >= 0 && chooseId <= 151) {
					for (Pokemon p : pokeList) {
						if (p.getId() == chooseId) {
							System.out.println("Você já escolheu esse Pokemon, tente novamente." + "\n");
							ok = false;
						}
					}
					if (ok) {
						Pokemon poke = pokemonController.getPokemon(chooseId);
						if (poke != null) {
							pokeList.add(pokemonController.getPokemon(chooseId));
							ok = false;
						} else
							ok = true;
					} else
						ok = true;
				} else
					System.out.println("Escolha um Id válido entre 0 e 151:");
			} else
				System.out.println("Escolha um número entre 0 e 151:");
		}
	}

	public static void choosePlayerPokemons() {
		ArrayList<Pokemon> pokeListP1 = new ArrayList<Pokemon>();
		ArrayList<Pokemon> pokeListP2 = new ArrayList<Pokemon>();
		int amountOfPokemon = battleController.getAmountOfPokemon();
		System.out.println(pokemonController.simpleListPokemon());

		for (int i = 0; i < amountOfPokemon; i++) {
			System.out.println("Seu numero da sorte: " + randonNumber(0, 151) + "\n");
			System.out.println("Digite o ID do " + (i + 1) + "º Pokemon:");
			validChoice(pokeListP1, i);
			validMachineChoice(pokeListP2);
			System.out.println("----------------------------------" + "\n" + " Ash escolheu "
					+ pokeListP1.get(i).getName() + "\n" + " Gary escolheu " + pokeListP2.get(i).getName() + "\n"
					+ "----------------------------------" + "\n");
		}
		battleController.setPlayerOne("Ash", pokeListP1);
		battleController.setPlayerTwo("Gary", pokeListP2);
	}

	public void choosePokemonMenu() {
		amountOfPokemonsToFight();
		choosePlayerPokemons();
	}
}
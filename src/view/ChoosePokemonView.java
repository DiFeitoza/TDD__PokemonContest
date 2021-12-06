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

	static void amountOfPokemonsToFight() {
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

	public static boolean validMachineChoice(ArrayList<Pokemon> pokeList) {
		int randonId = randonNumber(1, 127);
		for (Pokemon p : pokeList) {
			if (p.getId() == randonId) {
				System.out.println("Gary está pensando qual id escolher..." + "\n");
				return false;
			}
		}
		pokeList.add(pokemonController.getPokemon(randonId));
		return true;
	}

	public static boolean validChoice(ArrayList<Pokemon> pokeList, int chooseId, int i) {

		while (chooseId < 0 || chooseId > 127) {
			System.out.println("Escolha um Id valido entre 0 e 127: ");
			chooseId = Integer.parseInt(sc.nextLine());
			System.out.println("\n");
		}
		for (Pokemon p : pokeList) {
			if (p.getId() == chooseId) {
				System.out.println("Você já escolheu esse Pokemon, tente novamente." + "\n");
				return false;
			}
		}
		pokeList.add(pokemonController.getPokemon(chooseId));
		return true;
	}

	public static void choosePlayerPokemons() {
		ArrayList<Pokemon> pokeListP1 = new ArrayList<Pokemon>();
		ArrayList<Pokemon> pokeListP2 = new ArrayList<Pokemon>();

		System.out.println(pokemonController.simpleListPokemon());

		for (int i = 0; i < battleController.getAmountOfPokemon(); i++) {
			// PLayer choice
			System.out.println("Seu numero da sorte: " + randonNumber(1, 127) + "\n");

			while (true) {
				System.out.println("Digite o ID do " + (i + 1) + "º Pokemon:");
				int choose = Integer.parseInt(sc.nextLine());
				if (validChoice(pokeListP1, choose, i)) {
					break;
				}
			}

			// Machine choice
			while (true) {
				if (validMachineChoice(pokeListP2))
					;
				break;
			}

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
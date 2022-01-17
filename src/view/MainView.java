package view;

import java.io.IOException;
import java.util.Scanner;

import controller.PokemonController;

public class MainView {
	private static PokemonController pokemonController = PokemonController.getInstance();
	private static ChoosePokemonView choosePokemonView = ChoosePokemonView.getInstance();
	private static FightPokemonView fightPokemonView;
	private static PokedexView pokedexView = PokedexView.getInstace();
	private static Scanner sc = new Scanner(System.in);
	private static String opt = "s";

	public static void mainMenu() throws IOException {
		while (true) {
			System.out.println("---------( MENU INICIAL )--------- " + "\n" + "\n" + "[P] Acessar a Pokedex" + "\n"
					+ "[S] Lista de Pokemons - Simples" + "\n" + "[C] Lista de Pokemons - Completa" + "\n"
					+ "[B] Iniciar Batalha" + "\n" + "[E] Deixar o Game" + "\n" + "\n"
					+ "---------( POKEMON UFC )----------" + "\n");

			opt = sc.nextLine();

			if (opt.matches("[A-Z]{1}")) {
				opt = String.valueOf((char) ('a' + (opt.charAt(0) - 'A')));
			}

			switch (opt) {
			case "p":
				System.out.println("\n");
				System.out.println(pokemonController.simpleListPokemon());
				pokedexView.main();
				break;
			case "s":
				System.out.println("\n");
				System.out.println(pokemonController.simpleListPokemon());
				break;
			case "c":
				System.out.println("\n");
				System.out.println(pokemonController.fullListPokemon());
				break;
			case "b":
				System.out.println("\n");
				choosePokemonView.choosePokemonMenu();
				fightPokemonView = FightPokemonView.getInstance();
				fightPokemonView.fightPokemonMenu();
				break;
			case "e":
				System.out.println("\n" + "GoodBye Mestre Pokemon!" + "\n");
				System.exit(0);
				break;
			default:
				System.out.println("Você deve inserir uma letra do menu!" + "\n" + "\n");
			}
		}
	}

	public static void main(String[] args) throws IOException {
		boolean boolSeed = false;
		try {
			boolSeed = pokemonController.seedPokemonRepository();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (boolSeed) {
			System.out.println("  [Jogo carregado com sucesso!]");
		} else {
			System.out.println("[Erro no import dos dados]");
			System.exit(1);
		}
		mainMenu();
	}
}
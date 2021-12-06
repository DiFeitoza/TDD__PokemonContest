package view;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import controller.BattleController;
import model.Player;
import model.Pokemon;

public class FightPokemonView {
	private static FightPokemonView fightPokemonView;
	private static BattleController battleController = BattleController.getInstance();
	ArrayList<String> alivePokemonsP1 = new ArrayList<String>();
	ArrayList<String> alivePokemonsP2 = new ArrayList<String>();
	ArrayList<String> winRound = new ArrayList<String>();
	ArrayList<Integer> winner = new ArrayList<Integer>();
	ArrayList<Integer> loser = new ArrayList<Integer>();
	int defeatedPokemonsP1 = 0, defeatedPokemonsP2 = 0;
	int chooseP1 = -1, chooseP2 = 0;
	int amountOfPokemon = 0;
	static Scanner sc = new Scanner(System.in);

	public static FightPokemonView getInstance() {
		if (fightPokemonView == null) {
			fightPokemonView = new FightPokemonView();
		}
		return fightPokemonView;
	}

	public static int randonNumber(int min, int max) {
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}

	void printStatusPokemon(Pokemon p) {
		System.out.println(p.getName() + "[HP " + p.getHp() + "]");
	}

	public void resultFight(ArrayList<Pokemon> pokemonsP1, ArrayList<Pokemon> pokemonsP2) {
		System.out.println("     RESULTADOS:\n");
		for (int i = 0; i < winRound.size(); i++) {
			if (winRound.get(i).equals("p")) {
				System.out.println(
						pokemonsP1.get(winner.get(i)).getName() + " <Win> " + pokemonsP2.get(loser.get(i)).getName());
			} else {
				System.out.println(
						pokemonsP1.get(loser.get(i)).getName() + " <Lose> " + pokemonsP2.get(winner.get(i)).getName());
			}
		}

		if (defeatedPokemonsP1 < amountOfPokemon) {
			System.out.println("\n----------------------------------\n"
					+ "Garry foi derrotado!\nParabéns voce é um grande mestre Pokemon!"
					+ "\n----------------------------------\n");
		} else {
			System.out.println("\n----------------------------------\n"
					+ "Nao foi dessa vez... esta pronto para a proxima batalha?"
					+ "\n----------------------------------\n");
		}
	}

	public void resultAttack(ArrayList<Pokemon> pokemonsP1, ArrayList<Pokemon> pokemonsP2) {
		Pokemon pokeFightP1 = pokemonsP1.get(chooseP1);
		Pokemon pokeFightP2 = pokemonsP2.get(chooseP2);

		if (pokeFightP1.getHp() <= 0) {
			winRound.add("m");
			winner.add(chooseP2);
			loser.add(chooseP1);
			defeatedPokemonsP1++;

			alivePokemonsP1.add(chooseP1, "------");

			System.out.println("   Ash  VS  Gary  \n");
			for (int i = 0; i < amountOfPokemon; i++) {
				System.out.println(alivePokemonsP1.get(i) + "  x  " + alivePokemonsP2.get(i));
			}

			System.out.println("\n----------------------------------\n" + "Seu " + pokeFightP1.getName()
					+ " não resistiu e retornou a pokebola!" + "\n----------------------------------\n");

			if ((amountOfPokemon - defeatedPokemonsP1) > 1) {
				System.out.println("\nQuem irá substituí-lo?\n");
				
				int i = 0;
				for (Pokemon p : pokemonsP1) {
					if (p.getHp() > 0) {
						System.out.println(i + " - " + p.getName() + "[HP " + p.getHp() + "]");
					}
					i++;
				}
				
				String input = "";
				chooseP1 = -1;
				while (chooseP1 < 0 || chooseP1 >= amountOfPokemon) {
					System.out.println("Escolha um número valido...\n");
					input = sc.nextLine();
					if (input.matches("\\d{1}")) {
						chooseP1 = Integer.parseInt(input);
						if (chooseP1 >= 0 && chooseP1 < amountOfPokemon) {
							if (pokemonsP1.get(chooseP1).getHp() > 0) {
								System.out.println("chooseP1: " + chooseP1);
								System.out.println("amountOfPokemon: " + amountOfPokemon);
								break;
							} else {
								chooseP1 = -1;
							}
						}
					}
				}
			} else {
				int i = 0;
				for (Pokemon p : pokemonsP1) {
					if (p.getHp() > 0) {
						chooseP1 = i;
						pokeFightP1 = pokemonsP1.get(chooseP1);
					}
					i++;
				}
			}
			if (defeatedPokemonsP1 < amountOfPokemon) {
				System.out.println(
						"\n----------------------------------\n" + pokeFightP1.getName() + " entrou na batalha!\n");
			}
		}

		if (pokeFightP2.getHp() <= 0) {
			winRound.add("p");
			winner.add(chooseP1);
			loser.add(chooseP2);

			alivePokemonsP2.add(chooseP2, "------");

			System.out.println("   Ash  VS  Gary  \n");
			for (int i = 0; i < amountOfPokemon; i++) {
				System.out.println(alivePokemonsP1.get(i) + "  x  " + alivePokemonsP2.get(i));
			}

			System.out.println("\n----------------------------------\n" + pokeFightP2.getName() + " foi derrotado!\n");

			defeatedPokemonsP2++;

			if (defeatedPokemonsP2 < amountOfPokemon) {
				chooseP2++;
				pokeFightP2 = pokemonsP2.get(chooseP2);
				System.out.println(pokeFightP2.getName() + " entrou para enfrenta-lo!" + "\n");
			}
		}
	}

	void itsFight(ArrayList<Pokemon> pokemonsP1, ArrayList<Pokemon> pokemonsP2) {
		chooseP2 = 0; // dont move it
		Pokemon pokeFightP1 = pokemonsP1.get(chooseP1);
		Pokemon pokeFightP2 = pokemonsP2.get(chooseP2);

		System.out.println("\n\n" + "----------------------------------\n" + "        A batalha começou!");

		while (defeatedPokemonsP1 < amountOfPokemon && defeatedPokemonsP2 < amountOfPokemon) {
			pokeFightP1 = pokemonsP1.get(chooseP1);
			pokeFightP2 = pokemonsP2.get(chooseP2);
			Double mult = battleController.damageMultiplier(pokeFightP1.getElement(), pokeFightP2.getElement());

			System.out.println("----------------------------------");
			printStatusPokemon(pokeFightP2);
			printStatusPokemon(pokeFightP1);
			System.out.println("----------------------------------\n");

			System.out.println(
					"Escolha o tipo de ataque: " + "\n" + "1 - Ataque Corporal" + "\n" + "2 - Ataque Especial");

			int typeAtkP1 = 0;
			int typeAtkP2 = randonNumber(1, 2);

			String input = sc.nextLine();
			while (!input.equals("1") && !input.equals("2")) {
				System.out.println("Ecolha 1 ou 2\n");
				input = sc.nextLine();
				if (input.equals("1") || input.equals("2")) {
					typeAtkP1 = Integer.parseInt(input);
					break;
				}
			}

			if (typeAtkP1 == 1)
				battleController.attackDamage(0.0, pokeFightP1, pokeFightP2);
			else
				battleController.attackDamage(mult, pokeFightP1, pokeFightP2);

			if (typeAtkP2 == randonNumber(1, 2))
				battleController.attackDamage(0.0, pokeFightP1, pokeFightP2);
			else
				battleController.attackDamage(mult, pokeFightP2, pokeFightP1);

			resultAttack(pokemonsP1, pokemonsP2);
		}
	}

	public void fightPokemonMenu() {
		amountOfPokemon = battleController.getAmountOfPokemon();
		Player p1 = battleController.getPlayerOne();
		Player p2 = battleController.getPlayerTwo();
		ArrayList<Pokemon> pokemonsP1 = p1.getPokemons();
		ArrayList<Pokemon> pokemonsP2 = p2.getPokemons();

		for (Pokemon pokemon : pokemonsP1) {
			alivePokemonsP1.add(pokemon.getName());
		}
		for (Pokemon pokemon : pokemonsP2) {
			alivePokemonsP2.add(pokemon.getName());
		}

		System.out.println("   Ash  VS  Gary  \n");
		for (int i = 0; i < amountOfPokemon; i++) {
			System.out.println(alivePokemonsP1.get(i) + "  x  " + alivePokemonsP2.get(i));
		}
		System.out.println("----------------------------------");

		System.out.println("\nQuem irá iniciar a batalha?\n");

		int i = 0;
		for (Pokemon p : pokemonsP1) {
			if (p.getHp() > 0) {
				System.out.println(i + " - " + p.getName() + "[HP " + p.getHp() + "]");
			}
			i++;
		}

		chooseP1 = -1;
		String input = "";
		while (chooseP1 < 0 || chooseP1 >= amountOfPokemon) {
			System.out.println("\nEscolha um número valido...\n");
			input = sc.nextLine();
			if (input.matches("\\d{1}")) {
				chooseP1 = Integer.parseInt(input);
				if (chooseP1 >= 0 && chooseP1 < amountOfPokemon) {
					if (pokemonsP1.get(chooseP1).getHp() > 0) {
						break;
					}
				} else {
					chooseP1 = -1;
				}
			}
		}
		itsFight(pokemonsP1, pokemonsP2);
		resultFight(pokemonsP1, pokemonsP2);
	}
}
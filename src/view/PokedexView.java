package view;

import java.util.Scanner;

import controller.PokedexController;

public class PokedexView {
	PokedexController pokedexController = PokedexController.getInstance();
	private static Scanner sc = new Scanner(System.in);
	
	public static PokedexView getInstace() {
		return new PokedexView();
	}
		
	public void setPokedexController(PokedexController pokedexController) {
		this.pokedexController = pokedexController;
	}
	
	public void printPokedexPokemonFromId(int id) {
		pokedexController.printPokedexPokemonFromId(id);
	}
	
	public Integer validChoicedId() {
		int chooseId;
		while (true) {
			String input = sc.nextLine();
			if (input.matches("\\d{1,3}")) {
				chooseId = Integer.parseInt(input);
				if (chooseId >= 1 && chooseId <= 151) {
					return chooseId;
				} else
					System.out.println("Escolha um Id válido entre 1 e 151:");
			} else
				System.out.println("Escolha um número entre 1 e 151:");
		}
	}
	
	public void main() {
		while(true) {	
			System.out.println("Escolha um número entre 1 e 151:");
			Integer id = validChoicedId();
			printPokedexPokemonFromId(id);
			
			System.out.println("---------( MENU POKEDEX )---------\n");
			System.out.println("A Pokedex foi gerada em nova janela");
			System.out.println("Verifique a barra de tarefas!\n");
			System.out.println("[Enter] Nova consulta");
			System.out.println("[V] Voltar ao menu inicial");
			System.out.println("\n---------( POKEMON UFC )----------");
			
			while(true) {
				String input = sc.nextLine();
				if(input.equals("") || input.equals("n") || input.equals("N")){
					break;
				} else if(input.equals("v") || input.equals("V")) {
					return;
				}
				System.out.println("Escolha uma das duas opções acima:");
			}
		}
	}
}

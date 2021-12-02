package data;

import java.util.Scanner;

import DAO.PokemonRepository;
import model.Pokemon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PokemonData {
	static PokemonData PokemonData;
    BufferedReader buff;
    Scanner sc;
    String input;
    
    public PokemonData() throws IOException {
        this.buff = new BufferedReader(new FileReader("../data/pokemon.txt"));
        this.sc = new Scanner(System.in);
        this.input = "";
     }
    
    public static PokemonData getInstance() {
    	if (PokemonData == null) {
    		try {
				PokemonData = new PokemonData();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return PokemonData;
    }

    public String nextLine() throws IOException {
        input = buff.readLine();
        if (input != null) {
            return input;
        } else {
            buff.close();
            return "levante exceção";
        }
    }
    
    Pokemon generateDataPokemon() throws IOException {
    	String name, element;
    	Integer id, hp, atack, defense, speed;
    	
    	name = sc.nextLine();
    	element = sc.nextLine();
    	id = Integer.parseInt(sc.nextLine());
    	hp = Integer.parseInt(sc.nextLine());
    	atack = Integer.parseInt(sc.nextLine());
    	defense = Integer.parseInt(sc.nextLine());
    	speed = Integer.parseInt(sc.nextLine());
    	
    	Pokemon newPokemon = new Pokemon(id, name, element, hp, atack, defense, speed);
    	return newPokemon;
    }

    public void close(){
        this.sc.close();
    }
}

package data;

import java.util.HashMap;
import java.util.Scanner;

import model.Pokemon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PokemonData {
	private static PokemonData PokemonData;
	private static HashMap<Integer, Pokemon> allPokemonsInData;
    private BufferedReader buff;
    private Scanner sc;
    private String input;
    
    public PokemonData() throws IOException {
        this.buff = new BufferedReader(new FileReader("src/data/pokemons.txt"));
        this.sc = new Scanner(System.in);
        this.input = "";
     }
    
    public static PokemonData getInstance() {
    	if (PokemonData == null) {
    		try {
				PokemonData = new PokemonData();
			} catch (IOException e) {
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
            System.out.println("buffer fechou, linha retornou null");
            return null;
        }
    }
    
    public HashMap<Integer, Pokemon> rebootData() {
    	return allPokemonsInData;
    }
    
    public HashMap<Integer, Pokemon> generateMapFromDataPokemon() throws IOException {
    	String name, element;
    	Integer id, hp, attack, defense, speed;  	
    	allPokemonsInData = new HashMap<Integer, Pokemon>();
    	
    	int amountOfPokemonInData = Integer.parseInt(nextLine());
    	    	
    	for(int i = 0; i < amountOfPokemonInData; i++) {
    		Pokemon newPokemon = new Pokemon();

    		id = Integer.parseInt(nextLine());
	    	name = nextLine();
	    	element = nextLine();
	    	attack = Integer.parseInt(nextLine());
	    	defense = Integer.parseInt(nextLine());
    		hp = Integer.parseInt(nextLine());
	    	speed = Integer.parseInt(nextLine());
	    	
	    	boolean boolId = newPokemon.setId(id);
    		boolean boolName = newPokemon.setName(name);
	    	boolean elem = newPokemon.setElement(element);
	    	boolean atk = newPokemon.setAttack(attack);
	    	boolean def = newPokemon.setDefense(defense);
	    	boolean boolHp = newPokemon.setHp(hp);
	    	boolean spd = newPokemon.setSpeed(speed);
	    		    	
	    	if(boolName && elem && boolId && boolHp && atk && def && spd) {
	    		allPokemonsInData.put(id, newPokemon);
	    	}
	    	else {
	    		System.out.println("Pokemon de ID '" + id + "' não pôde ser carregado do arquivo de dados\n");
	    	}
    	}
    	return allPokemonsInData;
    }

    public void close(){
        this.sc.close();
    }
}

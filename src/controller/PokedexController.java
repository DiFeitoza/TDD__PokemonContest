package controller;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import model.PokedexAbility;
import model.PokedexPokemon;
import service.PokedexPokeAPI;
import view.DisplayView;

public class PokedexController {
	private static PokedexController pokedexController;
	private PokedexPokeAPI pokedexPokeAPI = PokedexPokeAPI.getInstance();
	private DisplayView pokedexDisplay = DisplayView.getInstance();	

	public static PokedexController getInstance() {
		if (pokedexController == null) {
			pokedexController  = new PokedexController();
		}
		return pokedexController;
	}

	public PokedexAbility parseEffectAbilitiesFromJson(String output){
        Gson gson = new Gson();
        PokedexAbility data = gson.fromJson(new String(output.getBytes()), PokedexAbility.class); 
		return data;
	}
	
	public PokedexPokemon parsePokemonFromJson(String output) {
        Gson gson = new Gson();
        PokedexPokemon data = gson.fromJson(new String(output.getBytes()), PokedexPokemon.class);
        return data;
    }
	
	public String getPokemonEffectsAndAbilities(List<String> abilities_urls){
    	StringBuilder builder = new StringBuilder();
    	for(String abilityUrl : abilities_urls) {
    		String abilityData = pokedexPokeAPI.getDataPokeAPIFromUrl(abilityUrl);
    		PokedexAbility data = parseEffectAbilitiesFromJson(abilityData);    		
    		builder.append(data.toHtml());
    	}
    	return builder.toString();  
	}
	
    public void printPokedexPokemonFromId(int id) {
    	String pokemonUrl = "https://pokeapi.co/api/v2/pokemon/" + id;
    	String pokemonData = pokedexPokeAPI.getDataPokeAPIFromUrl(pokemonUrl);
    	PokedexPokemon pokedexPokemon = parsePokemonFromJson(pokemonData);    	
    	List<String> abilities_urls = pokedexPokemon.getAbilitiesUrls();
    	String pokemonAbilitiesAndEffects = getPokemonEffectsAndAbilities(abilities_urls);
    	try {
    		pokedexDisplay.print(pokedexPokemon, pokemonAbilitiesAndEffects);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
}

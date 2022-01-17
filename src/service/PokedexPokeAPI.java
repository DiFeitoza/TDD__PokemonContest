package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PokedexPokeAPI {
	private static PokedexPokeAPI pokemonPokeAPI;
	
	public static PokedexPokeAPI getInstance() {
		if (pokemonPokeAPI == null) {
			pokemonPokeAPI = new PokedexPokeAPI();
		}
		return pokemonPokeAPI;
	}

	public String getDataPokeAPIFromUrl(String url) {
		String output = "";
		try{
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
	    	
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Accept", "application/json");
	
	        if (conn.getResponseCode() != 200) {
	            System.out.println("Erro " + conn.getResponseCode() + " ao obter dados da URL " + url);
	        }
	
	        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
	
	        String line;
	        while ((line = br.readLine()) != null) {
	            output += line;
	        }
	        
	        conn.disconnect();
	    } catch (IOException ex) {
	        //Logger.getLogger(PokedexPokeAPI.class.getName()).log(Level.SEVERE, null, ex);
	    }
		return output;
	}
}
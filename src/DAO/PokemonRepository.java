package DAO;

import java.util.HashMap;
import java.util.Set;

import model.Pokemon;

public class PokemonRepository {
	private static PokemonRepository pokemonRepository;
	private HashMap<Integer, Pokemon> mapPokemon = new HashMap<Integer, Pokemon>();
	private int id = 0;

	public static PokemonRepository getInstance() {
		if (pokemonRepository == null) {
			pokemonRepository = new PokemonRepository();
		}
		return pokemonRepository;
	}

	public HashMap<Integer, Pokemon> getMapPokemon() {
		return mapPokemon;
	}

	public boolean setMapPokemon(HashMap<Integer, Pokemon> mapPokemon) {
		if (mapPokemon != null) {
			this.mapPokemon = mapPokemon;
			this.id += mapPokemon.size();
			return true;
		}
		return false;
	}

	public boolean resetMapPokemon() {
		this.mapPokemon = new HashMap<Integer, Pokemon>();
		this.id = 0;
		return true;
	}

	public boolean addPokemon(String name, String element, Integer attack, Integer defense, Integer hp, Integer speed) {
		Pokemon newPokemon = new Pokemon();

		boolean boolId = newPokemon.setId(this.id);
		boolean boolName = newPokemon.setName(name);
		boolean elem = newPokemon.setElement(element);
		boolean boolHp = newPokemon.setHp(hp);
		boolean atk = newPokemon.setAttack(attack);
		boolean def = newPokemon.setDefense(defense);
		boolean spd = newPokemon.setSpeed(speed);

		if (boolName && elem && boolId && boolHp && atk && def && spd) {
			mapPokemon.put(newPokemon.getId(), newPokemon);
			this.id++;
			System.out.println(newPokemon.toString());
			return true;
		} else {
			return false;
		}
	}

	public Pokemon getPokemon(Integer idPoke) {
		Set<Integer> keys = mapPokemon.keySet();
		try {
			for (Integer key : keys)
				if (key != null)
					if (key == idPoke)
						return mapPokemon.get(key);
			throw new Exception("Pokemon id " + idPoke + " não existe");
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public int getId() {
		return id;
	}

	public boolean findPokemon(int id) {
		Pokemon p = getPokemon(id);
		if (p != null) {
			System.out.println(p.toString());
			return true;
		}
		return false;
	}

	public String simpleListPokemon() {
		StringBuilder builder = new StringBuilder();
		for (Pokemon p : mapPokemon.values()) {
			builder.append("Id/Nome " + p.getId() + "-" + p.getName() + "\n");
		}
		return builder.toString();
	}

	public String fullListPokemon() {
		StringBuilder builder = new StringBuilder();
		for (Pokemon p : mapPokemon.values()) {
			builder.append(p.toString() + "\n");
		}
		return builder.toString();
	}
}
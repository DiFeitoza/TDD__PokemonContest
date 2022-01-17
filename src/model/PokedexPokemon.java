package model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class PokedexPokemon {
	private Integer id;
	private String name;
	private Integer height;
	private Integer weight;
	private List<PokemonType> types;
	private Sprite sprites;
	private List<PokemonAbility> abilities;
	
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getHeight() {
		return height;
	}

	public Integer getWeight() {
		return weight;
	}
	
	public List<PokemonType> getTypes(){
		return types;
	}

	public String getStringTypes() {
		StringBuilder builder = new StringBuilder();
		for(PokemonType t : types) {
			builder.append(t.type.name).append(" ");
		}
		return builder.toString();
	}

	public String getImageUrl() {
		String str = sprites.other.official_artwork.front_default;
		return str;
	}
	
	public List<PokemonAbility> getAbilities(){
		return abilities;
	}

	public List<String> getAbilitiesUrls(){
		List<String> ability_urls = new ArrayList<String>();
		for(PokemonAbility pokemonAbility : abilities) {
			ability_urls.add(pokemonAbility.ability.url);
		}
		return ability_urls;
	}

	public String toHtml() {
		StringBuilder builder = new StringBuilder();
		builder.append("<span style='align:center'>POKEDEX</span>")
			.append("<br><b>Id</b>: ")
			.append(id)
			.append("<br><b>Nome</b>: ")
			.append(name)
			.append("<br><b>Altura</b>: ")
			.append(height*10)
			.append("cm")
			.append("<br><b>Peso</b>: ")
			.append((weight/10))
			.append("kg")
			.append("<br><b>Tipo</b>: ")
			.append(getStringTypes())
			.append("<br>");
		return builder.toString();
	}
}

class PokemonType{
	Type type;
}

class Type{
	String name;
}

class Sprite{
	Other other;
}

class Other{
	@SerializedName("official-artwork")
	OfficialArtWork official_artwork;
}

class OfficialArtWork{
	String front_default;
}

class PokemonAbility{
	Ability ability;
}

class Ability{
	String url;
}
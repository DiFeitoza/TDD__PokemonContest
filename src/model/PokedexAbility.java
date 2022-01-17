package model;

import java.util.List;

public class PokedexAbility {
	private List<Effect> effect_entries;
	private String name;

	public String getEffect() {
		return effect_entries.get(1).effect;
	}
	
	public String getAbilityName() {
		return name;
	}

	public String toHtml() {
		StringBuilder builder = new StringBuilder();
		builder.append("<br>Abilidade: ")
			.append(getAbilityName())
			.append("<br>Efeito: ")
			.append(getEffect())
			.append("<br>");
		return builder.toString();
	}
	
	class Effect{
		String effect;
	}
}

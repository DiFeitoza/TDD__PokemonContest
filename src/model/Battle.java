package model;

import java.util.ArrayList;

public class Battle {
	private static Battle battle;
	private int countFights;
	private int amountOfPokemon;
	private Player playerOne, playerTwo;

	public static Battle getInstance() {
		if (battle == null) {
			battle = new Battle();
		}
		return battle;
	}

	public Battle() {
	}

	public int getCountFights() {
		return countFights;
	}

	public boolean setCountFights(int countFights) {
		if (countFights >= 1 && countFights <= 11) {
			this.countFights = countFights;
			return true;
		}
		return false;
	}

	public int getAmountOfPokemon() {
		return amountOfPokemon;
	}

	public boolean setAmountOfPokemon(int amountOfPokemon) {
		if (amountOfPokemon >= 1 && amountOfPokemon <= 6) {
			this.amountOfPokemon = amountOfPokemon;
			return true;
		}
		return false;
	}

	public Double damageMultiplier(String type1, String type2) {
		Double mult = 1.0;

		if (type1 == "Bug") {
			if (type2 == "Dark" || type2 == "Grass" || type2 == "Psychic") {
				mult = 2.0;
			} else if (type2 == "Fire" || type2 == "Fighting" || type2 == "Poison" || type2 == "Flying"
					|| type2 == "Ghost" || type2 == "Steel" || type2 == "Fairy") {
				mult = 0.5;
			}
		}

		else if (type1 == "Dragon") {
			if (type2 == "Dragon") {
				mult = 2.0;
			} else if (type2 == "Stell") {
				mult = 0.5;
			} else if (type2 == "Fairy") {
				mult = 0.2;
			}
		}

		else if (type1 == "Electric") {
			if (type2 == "Water" || type2 == "Flying") {
				mult = 2.0;
			} else if (type2 == "Grass" || type2 == "Electric" || type2 == "Dragon") {
				mult = 0.5;
			} else if (type2 == "Ground") {
				mult = 0.2;
			}
		}

		else if (type1 == "Fairy") {
			if (type2 == "Dark" || type2 == "Dragon" || type2 == "Fighting") {
				mult = 2.0;
			} else if (type2 == "Poison" || type2 == "Steel" || type2 == "Fire") {
				mult = 0.5;
			}
		}

		else if (type1 == "Fighting") {
			if (type2 == "Dark" || type2 == "Ice" || type2 == "Normal" || type2 == "Rock" || type2 == "Steel") {
				mult = 2.0;
			} else if (type2 == "Poison" || type2 == "Flying" || type2 == "Psychic" || type2 == "Bug"
					|| type2 == "Fairy") {
				mult = 0.5;
			} else if (type2 == "Ghost") {
				mult = 0.2;
			}
		}

		else if (type1 == "Fire") {
			if (type2 == "Bug" || type2 == "Grass" || type2 == "Ice" || type2 == "Steel") {
				mult = 2.0;
			} else if (type2 == "Fire" || type2 == "Ground" || type2 == "Rock" || type2 == "Water") {
				mult = 0.5;
			}
		}

		else if (type1 == "Flying") {
			if (type2 == "Bug" || type2 == "Fighting" || type2 == "Grass") {
				mult = 2.0;
			} else if (type2 == "Electric" || type2 == "Rock" || type2 == "Steel") {
				mult = 0.5;
			}
		}

		else if (type1 == "Ghost") {
			if (type2 == "Ghost" || type2 == "Psychic") {
				mult = 2.0;
			}
			if (type2 == "Dark") {
				mult = 0.5;
			} else if (type2 == "Normal") {
				mult = 0.2;
			}
		}

		else if (type1 == "Grass") {
			if (type2 == "Ground" || type2 == "Rock" || type2 == "Water") {
				mult = 2.0;
			} else if (type2 == "Bug" || type2 == "Fire" || type2 == "Flying" || type2 == "Ice" || type2 == "Poison"
					|| type2 == "Dragon" || type2 == "Steel") {
				mult = 0.5;
			}
		}

		else if (type1 == "Ground") {
			if (type2 == "Electric" || type2 == "Fire" || type2 == "Poison" || type2 == "Rock" || type2 == "Steel") {
				mult = 2.0;
			}
			if (type2 == "Grass" || type2 == "Bug") {
				mult = 0.5;
			} else if (type2 == "Flying") {
				mult = 0.2;
			}
		}

		else if (type1 == "Ice") {
			if (type2 == "Dragon" || type2 == "Flying" || type2 == "Grass" || type2 == "Ground") {
				mult = 2.0;
			} else if (type2 == "Grass" || type2 == "Bug") {
				mult = 0.5;
			}
		}

		else if (type1 == "Normal") {
			if (type2 == "Rock" || type2 == "Stell") {
				mult = 0.5;
			} else if (type2 == "Ghost") {
				mult = 0.2;
			}
		}

		else if (type1 == "Poison") {
			if (type2 == "Fairy" || type2 == "Grass") {
				mult = 2.0;
			} else if (type2 == "Ground" || type2 == "Poison" || type2 == "Rock" || type2 == "Ghost") {
				mult = 0.5;
			} else if (type2 == "Steel") {
				mult = 0.2;
			}
		}

		else if (type1 == "Psychic") {
			if (type2 == "Fighting" || type2 == "Poison") {
				mult = 2.0;
			} else if (type2 == "Psychic" || type2 == "Stell") {
				mult = 0.5;
			} else if (type2 == "Dark") {
				mult = 0.2;
			}
		}

		else if (type1 == "Rock") {
			if (type2 == "Bug" || type2 == "Fire" || type2 == "Flying" || type2 == "Ice") {
				mult = 2.0;
			} else if (type2 == "Fighting" || type2 == "Ground" || type2 == "Steel") {
				mult = 0.5;
			}
		}

		else if (type1 == "Steel") {
			if (type2 == "Fairy" || type2 == "Ice" || type2 == "Rock") {
				mult = 2.0;
			} else if (type2 == "Water" || type2 == "Electric" || type2 == "Steel") {
				mult = 0.5;
			}
		}

		else if (type1 == "Water") {
			if (type2 == "Fire" || type2 == "Ice" || type2 == "Rock") {
				mult = 2.0;
			} else if (type2 == "Water" || type2 == "Electric" || type2 == "Steel") {
				mult = 0.5;
			}
		}
		return mult;
	}

	public void attackDamage(double mult, Pokemon pokeAttack, Pokemon pokeDefense) {
		int atk = (int) (pokeAttack.getAttack() * mult);
		int def = pokeDefense.getDefense();
		int hp = pokeDefense.getHp();

		if (hp - (atk - def) < 0) {
			pokeDefense.setHp(0);
		} else if (atk - def < 1) {
			pokeDefense.setHp(hp - 1);
		} else {
			pokeDefense.setHp(hp - (atk - def));
		}
	}

	public Player getPlayerOne() {
		return playerOne;
	}

	public boolean setPlayerOne(String name, ArrayList<Pokemon> pokemons) {
		playerOne = new Player();
		boolean boolName = playerOne.setName(name);
		boolean boolArray = playerOne.setPokemons(pokemons);
		if (boolName && boolArray && pokemons.size() == amountOfPokemon) {
			return true;
		} else {
			playerOne = null;
			return false;
		}
	}

	public Player getPlayerTwo() {
		return playerTwo;
	}

	public boolean setPlayerTwo(String name, ArrayList<Pokemon> pokemons) {
		playerTwo = new Player();
		boolean boolName = playerTwo.setName(name);
		boolean boolArray = playerTwo.setPokemons(pokemons);
		if (boolName && boolArray && pokemons.size() == amountOfPokemon) {
			return true;
		} else {
			playerTwo = null;
			return false;
		}
	}
}

package model;

public class Pokemon {
	private Integer id;
	private String name;
	private String element;
	private Integer attack;
	// private Integer magicalAttack;
	private Integer defense;
	// private Integer magicalDefense;
	private Integer hp;
	// private Integer sp;
	private Integer speed;

	public Pokemon(Integer id, String name, String element, Integer attack, Integer defense, Integer hp, Integer speed) {
		this.id = id;
		this.name = name;
		this.element = element;
		this.attack = attack;
		this.defense = defense;
		this.hp = hp;
		this.speed = speed;
	}

	public Integer getId() {
		return id;
	}

	public boolean setId(Integer id) {
		if(id == null || id < 0) {
			return false;
		}
		this.id = id;
		return true;
	}

	public String getName() {
		return name;
	}

	public boolean setName(String name) {
		if(name != null && name.matches("[A-Z]{1}[a-z'?\\\\.?]+([- ]?[A-Z]{1}[a-z'?\\\\.?]+)?[♀♂]?") && name.length() < 20){			
			this.name = name;
			return true;
		}
		return false;
	}

	public String getElement() {
		return element;
	}

	public boolean setElement(String element) {
		if(
			element != "Bug" && 
			element != "Dragon" && 
			element != "Electric" && 
			element != "Fairy" &&
			element != "Fighting" &&
			element != "Fire" &&
			element != "Ghost" &&
			element != "Grass" &&
			element != "Ground" &&
			element != "Ice" &&
			element != "Normal" &&
			element != "Poison" &&
			element != "Psychic" &&
			element != "Rock" &&
			element != "Steel" &&
			element != "Water"
		) return false;
		this.element = element;
		return true;
	}

	public Integer getAttack() {
		return attack;
	}

	public boolean setAttack(Integer attack) {
		if(attack == null || attack < 1 || attack > 999) {
			return false;
		}
		this.attack = attack;
		return true;
	}

	public Integer getDefense() {
		return defense;
	}

	public boolean setDefense(Integer defense) {	
		if(defense == null || defense < 1 || defense > 999) {
			return false;
		}
		this.defense = defense;
		return true;
	}

	public Integer getHp() {
		return hp;
	}

	public boolean setHp(Integer hp) {
		if(hp == null || hp < 0 || hp > 999) {
			return false;
		}
		this.hp = hp;
		return true;	
	}

	public Integer getSpeed() {
		return speed;
	}

	public boolean setSpeed(Integer speed) {
		if(speed == null || speed < 1 || speed > 999) {
			return false;
		}
		this.speed = speed;
		return true;		
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Id " + this.id +  " [" + this.name + "] " + this.element + " Atk:" + this.attack + " Def:" + this.defense + " HP:" + this.hp + " Speed:" + this.speed);
		return builder.toString();
	}
}
package model;

public class Pokemon {
	private Integer id;
	private String name;
	private String element;
	private Integer attack;
	// private Integer magicalAttack;
	private Integer defese;
	// private Integer magicalDefese;
	private Integer hp;
	// private Integer sp;
	private Integer speed;

	public Pokemon(Integer id, String name, String element, Integer attack, Integer defese, Integer hp, Integer speed) {
		this.id = id;
		this.name = name;
		this.element = element;
		this.attack = attack;
		this.defese = defese;
		this.hp = hp;
		this.speed = speed;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public Integer getAttack() {
		return attack;
	}

	public void setAttack(Integer attack) {
		this.attack = attack;
	}

	public Integer getDefese() {
		return defese;
	}

	public void setDefese(Integer defese) {
		this.defese = defese;
	}

	public Integer getHp() {
		return hp;
	}

	public void setHp(Integer hp) {
		this.hp = hp;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pokemon []");
		return builder.toString();
	}
}
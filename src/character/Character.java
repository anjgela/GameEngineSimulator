package character;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import state.CharacterState; 
import command.Command;
import command.AttackCommand;
import command.HealCommand;
import skill.Skill;

public abstract class Character {
	protected String name;
	protected int health;
	public static final int MAX_HEALTH = 100;
	protected int powerStorage;
	protected static final int MAX_POWER_STORAGE = 30;
	protected List<Skill> attackSkills;
	protected List<Skill> healSkills;
	protected Skill currentSkill;
	protected CharacterState state;
	
	public Character() {
		health = MAX_HEALTH;
		powerStorage = MAX_POWER_STORAGE;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String newName) {
		name = newName;
	}

	public int getHealth() {
		return health;
	}
	
	public CharacterState getState() {
		return state;
	}
	public void setState(CharacterState newState) {
		state = newState;
	}
	
	public void setSkill(Skill skill) {
		currentSkill = skill;
	}
	
	public Skill getCurrentSkill() {
		return currentSkill;
	}
	public void takeDamage(int damage) {
		health -= damage;
	}
	
	protected void heal(int energy) {
		health += energy;
	}
	
	public boolean isAlive() {
		return health > 0;
	}
	
	public Command chooseAction(List<Character> opponents, List<Character> team) {
		Command command = new Command();
		command.setPlayer(this);
		Character target = null;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Choose action: A) Attack  B) Heal");
		String commandType = scanner.next().toUpperCase();
		switch (commandType) {
			case "A":
				for (Skill attackSkill : attackSkills) {
					int i = 1;
					System.out.print(i + ") " + attackSkill.getName() + " ");
				}
				int attackSkill = scanner.nextInt(); //1) Single 2) Multiple 3) Poisonous
				command = new AttackCommand(attackSkill);
				if (attackSkill != 2) {
					 target = chooseTarget(opponents);
					 command.setTarget(target);
				}
				command.setTarget(opponents);
				break;
				
			case "B":
				for (Skill healSkill : healSkills) {
					int i = 1;
					System.out.print(i + ")" + healSkill.getName() + " ");
				}
				int healSkill = scanner.nextInt();
				command = new HealCommand(healSkill);
				target = chooseTarget(opponents, team);
				command.setTarget(target);
				break;
		}
		scanner.close();
		return command;
	}
	
	private Character chooseTarget(List<Character> opponents) { //helper method
		String choice;
		Character target = null;
		Scanner scanner = new Scanner(System.in);
		for (Character opponent : opponents) {
			while (target != null) {
				if (opponent.isAlive()) {
					System.out.println("Do you choose " + opponent.getName() + " as your target? ");
					choice = scanner.nextLine().toLowerCase();
					if (choice == "yes") {
						target = opponent;
					}
				}
			}
		}
		scanner.close();
		return target;
	}
	private Character chooseTarget(List<Character> opponents, List<Character> team) {
		String choice;
		Character target = null;
		Scanner scanner = new Scanner(System.in);
		List<Character> characters = new ArrayList<>();
		characters.addAll(opponents);
		characters.addAll(team);
		for (Character character : characters) {
			while (target != null) {
				if (character.isAlive()) {
					System.out.println("Do you choose " + character.getName() + " as your target? ");
					choice = scanner.nextLine().toLowerCase();
					if (choice == "yes") {
						target = character;
					}
				}
			}
		}
		scanner.close();
		return target;
	}
	
	protected abstract boolean attack(Character target);
	protected abstract boolean attack(List<Character> targets);
	
	protected abstract boolean heal(Character target);
	protected abstract boolean heal(List<Character> targets);
	
}

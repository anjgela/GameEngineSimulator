package character;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import state.CharacterState; 
import state.Normal;
import command.Command;
import command.AttackCommand;
import command.HealCommand;
import skill.Skill;
import skill.TargetType;
import skill.AttackSkill;
import skill.HealSkill;
import skill.SkillDecorator;

public abstract class Character {
	protected String name;
	
	protected int health;
	public static final int MAX_HEALTH = 100;
	
	protected int powerStorage;
	public static final int MAX_POWER_STORAGE = 30;
	private final int powerRegenerationPowerPerTurn = 5;	//TODO maybe move to state
	
	protected List<Skill> attackSkills;
	protected List<Skill> healSkills;
	protected Skill currentSkill;
	
	protected CharacterState state;
	
	public Character(String name) {
		this.name = name;
		health = MAX_HEALTH;
		powerStorage = MAX_POWER_STORAGE;
		attackSkills = new ArrayList<>();
		healSkills = new ArrayList<>();
		state = new Normal(CharacterState.ID.NORMAL);
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
	
	public void takeDamage(int damage) {
		health -= damage;
		if (health < 0) {
			health = 0;
		}
	}
	
	public void heal(int energy) {
		health = Math.min(MAX_HEALTH, health += energy);
	}
	
	public int getpowerStorage() {
		return powerStorage;
	}
	
	public boolean usePowerStorage(int cost) {
		if (powerStorage >= cost) {
			powerStorage -= cost;
			return true;
		}
		return false;
	}
	
	public void restorePowerStorage(int power) {
		powerStorage = Math.min(MAX_POWER_STORAGE, powerStorage+=power);
	}
	
	public void regeneratePowerStorage() {
		powerStorage = Math.min(MAX_POWER_STORAGE, powerStorage += powerRegenerationPowerPerTurn);
	}
	
	public List<Skill> getAttackSKills() {
		return List.copyOf(attackSkills);
	}
	
	public void addAttackSkill(AttackSkill skill) {
		attackSkills.add(skill);
	}
	
	public List<Skill> getHealSkills() {
		return List.copyOf(healSkills);
	}
	
	public void addHealSkill(HealSkill skill) {
		healSkills.add(skill);
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
	
	public boolean isAlive() {
		return health > 0;
	}
	
	public Command chooseAction(List<Character> opponents, List<Character> team) {
		Command command = null;
		Character target = null;
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Choose action: A) Attack  B) Heal");
		String commandType = scanner.next().toUpperCase();
		
		switch (commandType) {
			case "A":
				int i = 1;
				for (Skill attackSkill : attackSkills) {					
					System.out.print(i + ") " + attackSkill.getName() + " ");
					i++;
				}
				int choiceAttack = scanner.nextInt();
				Skill chosenAttackSkill = attackSkills.get(choiceAttack-1);
				
				command = new AttackCommand((SkillDecorator)chosenAttackSkill);
				
				command.setPlayer(this);
				if (chosenAttackSkill.getTargetType() == TargetType.MULTIPLE) {
					command.setTargets(opponents);
					 
				} else {
					target = chooseTarget(opponents);
					command.setTarget(target);
				}
				break;
				
			case "B":
				int j = 1;
				for (Skill healSkill : healSkills) {
					System.out.print(j + ")" + healSkill.getName() + " ");
					j++;
				}
				int choiceHeal = scanner.nextInt();
				Skill chosenHealSkill = healSkills.get(choiceHeal-1);
				
				command = new HealCommand((SkillDecorator)chosenHealSkill);
				command.setPlayer(this);
				if (chosenHealSkill.getTargetType() == TargetType.MULTIPLE) {
					command.setTargets(team);
				} else {
					target = chooseTarget(opponents, team);
					command.setTarget(target);
				}
				break;
		}
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

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
		Scanner scanner = new Scanner(System.in);
		System.out.println("Choose your action: A) Attack  B) Heal");
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
				
				Character attackTarget = chooseTarget(opponents, team);
				
				AttackCommand attackCommand = new AttackCommand((SkillDecorator)chosenAttackSkill);
				attackCommand.setPlayer(this);
				attackCommand.setTarget(attackTarget);
				return attackCommand;
				
			case "B":
				int j = 1;
				for (Skill healSkill : healSkills) {
					System.out.print(j + ")" + healSkill.getName() + " ");
					j++;
				}
				int choiceHeal = scanner.nextInt();
				Skill chosenHealSkill = healSkills.get(choiceHeal-1);
				
				Character healTarget = chooseTarget(opponents, team);
				
				HealCommand healCommand = new HealCommand((SkillDecorator)chosenHealSkill);
				healCommand.setPlayer(this);
				healCommand.setTarget(healTarget);
				return healCommand;	
		}
		return null;
		
	}
	
	private Character chooseTarget(List<Character> opponents, List<Character> team) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Choose your target: ");
		for (int i=0; i < opponents.size(); i++) {
			Character opponent = opponents.get(i);
			if (opponent.isAlive()) {
				System.out.println((i+1) + ") " + opponent.getName() + 
						" [health: " + opponent.getHealth() + "] ");
			}
		}
		int choiceTarget = scanner.nextInt();
		return opponents.get(choiceTarget-1);
	}
	
	protected abstract boolean attack(Character target);
	protected abstract boolean attack(List<Character> targets);
	
	protected abstract boolean heal(Character target);
	protected abstract boolean heal(List<Character> targets);
	
}

package character;

import java.util.List;

import skill.AttackSkill;

public class Swordsman extends Character {
	
	public Swordsman(String name) {
		super(name);
		AttackSkill singleSword = new AttackSkill("singleSword");
		attackSkills.add(singleSword);
		AttackSkill multipleSword = new AttackSkill("multipleSword");
		attackSkills.add(multipleSword);
		AttackSkill poisonousSword = new AttackSkill("poisonousSword");
		attackSkills.add(poisonousSword);
	}
	
	@Override
 	protected boolean attack(Character target) {
		return false;
 		}
	@Override
 	protected boolean attack(List<Character> targets) {
		return false;
 		}
	
 	@Override
	protected boolean heal(Character target) {
		return false;
 	}
 	@Override
	protected boolean heal(List<Character> targets) {
		return false;
 	}
}

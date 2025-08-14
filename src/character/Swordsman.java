package character;

import java.util.List;

import command.Command;
import skill.AttackSkill;

public class Swordsman extends Character {
	
	public Swordsman() {
		AttackSkill singleSword = new AttackSkill("singleSword");
		attackSkills.add(singleSword);
		AttackSkill multipleSword = new AttackSkill("multipleSword");
		attackSkills.add(multipleSword);
		AttackSkill poisonousSword = new AttackSkill("poisonousSword");
		attackSkills.add(poisonousSword);
	}
	public Swordsman(String chosenName) {
		 name = chosenName; //scanned
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

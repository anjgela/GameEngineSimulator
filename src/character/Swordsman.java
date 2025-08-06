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
 		state.setHitChance(this);
 		target.getState().setMissChance(target);
 		return (state.hitChance(this) * target.getState().missChance(target)) < hitChance;
	}
	
 	@Override
	protected boolean defend(Character target) {
		state.setMissChance(this);
		target.getState().setHitChance(target);
		return (state.missChance(this) * target.getState().hitChance(target)) < missChance;
	}
	@Override
	protected void performAction(Character target) {
		// TODO Auto-generated method stub
		
	}
}

package character;

import java.util.List;

import skill.Skill;
import skill.AttackSkill;
import skill.HealSkill;
import skill.Single;
import skill.Multiple;
import skill.Poisonous;

public class Swordsman extends Character {
	
	public Swordsman(String name) {
		super(name);
		Skill singleSword = new Single(new AttackSkill("sword"));
		attackSkills.add(singleSword);
		Skill multipleSword = new Multiple(new AttackSkill("sword"));
		attackSkills.add(multipleSword);
		Skill poisonousSword = new Single(new Poisonous(new AttackSkill("sword")));
		attackSkills.add(poisonousSword);
		Skill healingSword = new Single(new HealSkill("sword"));
		healSkills.add(healingSword);
		Skill healingSwords = new Multiple(new HealSkill("sword"));
		healSkills.add(healingSwords);
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

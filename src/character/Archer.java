package character;


import java.util.List;

import skill.Skill;
import skill.AttackSkill;
import skill.HealSkill;
import skill.Single;
import skill.Multiple;
import skill.Poisonous;

public class Archer extends Character{
	public Archer(String name) {
		super(name);
		Skill singleArrow = new Single(new AttackSkill("arrow"));
		attackSkills.add(singleArrow);
		Skill rainingArrow = new Multiple(new AttackSkill("raining arrow"));
		attackSkills.add(rainingArrow);
		Skill poisonousArrow = new Single(new Poisonous(new AttackSkill("arrow"))); //Poisonous
		attackSkills.add(poisonousArrow);
		Skill healingArrow = new Single(new HealSkill("arrow")); //
		healSkills.add(healingArrow);
		Skill healingRain = new Multiple(new HealSkill("rain"));
		healSkills.add(healingRain);
	}

	@Override
	protected boolean attack(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean attack(List<Character> targets) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean heal(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean heal(List<Character> targets) {
		// TODO Auto-generated method stub
		return false;
	}
}

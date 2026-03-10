package character;

import java.util.List;

import skill.Skill;
import skill.AttackSkill;
import skill.HealSkill;
import skill.Single;
import skill.Multiple;
import skill.Poisonous;

public class Wizard extends Character{
	
	public Wizard(String name) {
		super(name);
		Skill singleMagic = new Single(new AttackSkill("magic"));
		attackSkills.add(singleMagic);
		Skill multipleMagic = new Multiple(new AttackSkill("magic"));
		attackSkills.add(multipleMagic);
		Skill poisonousMagic = new Single(new Poisonous(new AttackSkill("magic")));
		attackSkills.add(poisonousMagic);
		Skill healingMagic = new Single(new HealSkill("magic"));
		healSkills.add(healingMagic);
		Skill healingMultipleMagic = new Multiple(new HealSkill("magic"));
		healSkills.add(healingMultipleMagic);
	}
}

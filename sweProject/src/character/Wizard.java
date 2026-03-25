package character;

import skill.Skill;
import skill.Single;
import skill.Multiple;
import skill.Poisonous;
import skillFactory.AbstractSkillFactory;
import skillFactory.AttackSkillFactory;
import skillFactory.HealSkillFactory;

public class Wizard extends Character{
	
	public Wizard(String name) {
		super(name);
		
		AbstractSkillFactory attackFactory = new AttackSkillFactory();
		AbstractSkillFactory healFactory = new HealSkillFactory();
		
		Skill attack = attackFactory.createSkill("magic");
		Skill heal = healFactory.createSkill("healing magic");
		
		attackSkills.add(new Single(attack));
		attackSkills.add(new Multiple(attack));
		attackSkills.add(new Single(new Poisonous(attack)));
		
		healSkills.add(new Single(heal));
		healSkills.add(new Multiple(heal));
	}
}

package character;

import skill.Skill;
import skill.Single;
import skill.Multiple;
import skill.Poisonous;
import skillFactory.AbstractSkillFactory;
import skillFactory.AttackSkillFactory;
import skillFactory.HealSkillFactory;

public class Archer extends Character{
	public Archer(String name) {
		super(name);
		AbstractSkillFactory attackFactory = new AttackSkillFactory();
        AbstractSkillFactory healFactory = new HealSkillFactory();
        
        Skill baseAttack = attackFactory.createSkill("arrow");
        Skill rainingAttack = attackFactory.createSkill("raining arrow");
        
        Skill baseHeal = healFactory.createSkill("arrow");
        Skill rainingHeal = healFactory.createSkill("rain");
        
        attackSkills.add(new Single(baseAttack));
        attackSkills.add(new Multiple(rainingAttack));
        attackSkills.add(new Single(new Poisonous(baseAttack)));
        
        healSkills.add(new Single(baseHeal));
        healSkills.add(new Multiple(rainingHeal));
	}
}

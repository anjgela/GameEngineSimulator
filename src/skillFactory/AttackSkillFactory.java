package skillFactory;

import skill.AttackSkill;
import skill.Skill;
public class AttackSkillFactory extends AbstractSkillFactory{

	@Override
	public Skill createSkill(String name) {
		return new AttackSkill(name);
	}

	
}

package skillFactory;

import skill.HealSkill;
import skill.Skill;

public class HealSkillFactory extends AbstractSkillFactory {

	@Override
	public Skill createSkill(String name) {
		return new HealSkill(name);
	}

}

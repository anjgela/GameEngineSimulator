package skillFactory;

import skill.Skill;

public abstract class AbstractSkillFactory {
	public abstract Skill createSkill(String name);
}

package skill;

import java.util.List;

import character.Character;

public abstract class SkillDecorator implements Skill {
	protected final Skill base;
	
	public SkillDecorator(Skill base) {
		this.base = base;
	}
	
	public String getName() {
		return base.getName();
	}
	
	public TargetType getTargetType() {
		return TargetType.SINGLE;
	}
	public void apply(Character target) {
		base.apply(target);
	}
	public void apply(List<Character> targets) {
		base.apply(targets);
	}
	
	
	

}

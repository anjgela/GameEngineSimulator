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
	
	public Skill getBaseSkill() {
	    Skill current = this;
	    while (current instanceof SkillDecorator) {
	        current = ((SkillDecorator) current).base;
	    }
	    return current;
	}
	
	public void apply(Character player, Character target) {
		base.apply(player, target);
	}
	public void apply(Character player, List<Character> targets) {
		base.apply(player, targets);
	}
}

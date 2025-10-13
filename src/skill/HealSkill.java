package skill;

import java.util.List;

import character.Character;

public class HealSkill implements Skill{
	private final String name;
	private final int healing = 30;
	
	public HealSkill(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public TargetType getTargetType() {
		return TargetType.SINGLE;
	}
	
	public int getHealing() {
		return healing;
	}

	@Override
	public void apply(Character target) {
		target.heal(healing);
	}

	@Override
	public void apply(List<Character> targets) {
		for (Character target : targets) {
			target.heal(healing);
		}
	}
}
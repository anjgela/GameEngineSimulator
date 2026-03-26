package skill;

import java.util.List;

import character.Character;
import state.Enhanced;

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

	@Override
	public void apply(Character player, Character target) {
		target.heal(healing);
		if (target.getHealth() >= 90) {
			target.setState(new Enhanced(3));
		}
	}

	@Override
	public void apply(Character player, List<Character> targets) {
		for (Character target : targets) {
			this.apply(player, target);
		}
	}
	
	@Override
	public int getEffectValue() {
		return healing;
	}
}
package skill;

import java.util.List;
import character.Character;
import state.Slowed;

public class AttackSkill implements Skill{
	private final String name;
	private final int damage = 30;
	
	public AttackSkill(String name) {
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
		target.takeDamage(damage);
		if (target.getHealth() <= 20) {
			target.setState(new Slowed(1));
		}
		
	}

	@Override
	public void apply(Character player, List<Character> targets) {
		for (Character target : targets) {
			target.takeDamage(damage);
		}
	}
	
	@Override
	public int getEffectValue() {
		return damage;
	}
	
}

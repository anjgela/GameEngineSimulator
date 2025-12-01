package skill;

import java.util.List;
import character.Character;

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

	public int getDamage() {
		return damage;
	}
	
	
	@Override
	public void apply(Character player, Character target) {
		target.takeDamage(damage);
		
	}

	@Override
	public void apply(Character player, List<Character> targets) {
		for (Character target : targets) {
			target.takeDamage(damage);
		}
	}
	
}

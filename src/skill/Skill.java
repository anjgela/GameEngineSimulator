package skill;

import java.util.List;

import character.Character;

public interface Skill {
	public static final int POWER = 12;
	public String getName();
	public TargetType getTargetType();
	public void apply(Character target);
	public void apply(List<Character> targets);
}
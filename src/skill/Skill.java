package skill;

import java.util.List;

import character.Character;

public interface Skill {
	public static final int MIN_POWER = 7;
	public static final int BASE_POWER = 12;
	public static final int MAX_POWER = 17;
	public String getName();
	public void apply(Character target);
	public void apply(List<Character> target);
}
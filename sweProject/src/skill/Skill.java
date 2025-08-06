package skill;

import java.util.List;

import character.Character;

public interface Skill {
	public String getName();
	public void apply(Character target);
	public void apply(List<Character> target);
}

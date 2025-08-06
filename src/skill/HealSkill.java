package skill;

import java.util.List;

import character.Character;

public class HealSkill implements Skill{
	String name;
	
	public HealSkill(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void apply(Character target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void apply(List<Character> target) {
		// TODO Auto-generated method stub
		
	}

}

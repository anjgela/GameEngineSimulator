package skill;

import java.util.List;

import character.Character;

public class HealSkill implements Skill{
	private String name;
	private int healing;
	
	public HealSkill(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public int getHealing() {
		return healing;
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

package character;


import java.util.List;

import skill.AttackSkill;

public class Archer extends Character{
	public Archer(String name) {
		super(name);
		AttackSkill singleArrow = new AttackSkill("singleArrow");
		attackSkills.add(singleArrow);
		AttackSkill multipleArrow = new AttackSkill("multipleArrow");
		attackSkills.add(multipleArrow);
		AttackSkill poisonousArrow = new AttackSkill("posinousArrow");
		attackSkills.add(poisonousArrow);
	}

	@Override
	protected boolean attack(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean attack(List<Character> targets) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean heal(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean heal(List<Character> targets) {
		// TODO Auto-generated method stub
		return false;
	}
}

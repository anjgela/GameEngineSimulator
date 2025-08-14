package character;

import java.util.List;
import command.Command;
import skill.AttackSkill;
import skill.Skill;

public class Archer extends Character{
	public Archer() {
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

package character;

import java.util.List;

import command.Command;
import skill.AttackSkill;

public class Wizard extends Character{
	
	public Wizard() {
		AttackSkill singleMagic = new AttackSkill("singleMagic");
		attackSkills.add(singleMagic);
		AttackSkill multipleMagic = new AttackSkill("multipleMagic");
		attackSkills.add(multipleMagic);
		AttackSkill poisonousMagic = new AttackSkill("poisonousMagic");
		attackSkills.add(poisonousMagic);
	}
	@Override
	protected boolean attack(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean defend(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void performAction(Character target) {
		// TODO Auto-generated method stub
		
	}

}

package skill;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import character.Character;
import character.Swordsman;
import character.Wizard;



public class HealSkillTest {
	
	private Character player;
	private Character target;
	
	@Before
	public void setUp() {
		player = new Swordsman("player");
		target = new Wizard("target");
		Skill attackSkill = player.getAttackSkills().get(0);
		AttackSkill baseAttackSkill = (AttackSkill) ((SkillDecorator) attackSkill).getBaseSkill();
		attackSkill.apply(player, target);
		assertEquals(Character.MAX_HEALTH-baseAttackSkill.getDamage(), target.getHealth());

	}
	
	@Test
	public void applyTest() {
		Skill skill = player.getHealSkills().get(0);
		skill.apply(target, target);
		assertEquals(Character.MAX_HEALTH, target.getHealth());
	}

}

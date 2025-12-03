package skill;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import character.Character;
import character.Archer;
import character.Wizard;

public class AttackSkillTest {
	
	private Character player;
	private Character target;
	
	@Before
	public void setUp() {
		player = new Archer("player");
		target = new Wizard("target");
	}
	
	@Test
	public void applyTest() {
		Skill skill = player.getAttackSkills().get(0);
		AttackSkill baseSkill = (AttackSkill) ((SkillDecorator) skill).getBaseSkill(); 
		
		skill.apply(player, target);
		
		assertEquals(Character.MAX_HEALTH-baseSkill.getDamage(), target.getHealth());
	}

}

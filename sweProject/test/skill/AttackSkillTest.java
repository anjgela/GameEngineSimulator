package skill;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import character.Character;
import character.Archer;
import character.Wizard;
import state.CharacterState;

public class AttackSkillTest {
	
	private Character player;
	private Character target;
	private Skill skill;
	private AttackSkill baseSkill;
	
	@Before
	public void setUp() {
		player = new Archer("player");
		target = new Wizard("target");
		skill = player.getAttackSkills().get(0);
		baseSkill = (AttackSkill) ((SkillDecorator) skill).getBaseSkill();
	}
	
	@Test
	public void applyTest() {
		skill.apply(player, target);
		
		assertEquals(Character.MAX_HEALTH-baseSkill.getEffectValue(), target.getHealth());
	}
	
	@Test 
	public void skillAppliesSlowedStateTest() {
		target.takeDamage(50);
		
		skill.apply(player, target);
		
		assertEquals(CharacterState.ID.SLOWED, target.getState().getID());
	}

}

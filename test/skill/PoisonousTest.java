package skill;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import character.Character;
import character.Archer;
import character.Wizard;
import state.CharacterState;
import state.Poisoned;
import state.Enhanced;

public class PoisonousTest {
	
	private Character player;
	private Character target;
	
	@Before
	public void setUp() {
		player = new Archer("player");
		target = new Wizard("target");
	}
	
	@Test
	public void skillAppliesStateTest() {
		Skill skill = new Single(new Poisonous(new AttackSkill("arrow")));
		assertEquals(CharacterState.ID.NORMAL, target.getState().getID());
		
		skill.apply(player, target);
		
		assertEquals(CharacterState.ID.POISONED, target.getState().getID());
	}
}


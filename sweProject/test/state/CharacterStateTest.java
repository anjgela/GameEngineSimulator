package state;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import character.Character;
import character.Archer;
import skill.AttackSkill;
import skill.Poisonous;
import skill.Skill;

public class CharacterStateTest {
	
	private Character player;
	private Character target;

	@Before
	public void setUp() {
		player = new Archer("player");
		target = new Archer("target");
	}
	
	@Test
	public void changeToPoisonedTest() {
		assertEquals(CharacterState.ID.NORMAL, target.getState().getID());
		
		Skill baseAttack = new AttackSkill("arrow");
        Skill poisonousAttack = new Poisonous(baseAttack);
        
        poisonousAttack.apply(player, target);
        
        assertEquals(CharacterState.ID.POISONED, target.getState().getID());
	}
	
	@Test
	public void keepNormalTest() {
        assertEquals(CharacterState.ID.NORMAL, target.getState().getID());
        
        Skill normalAttack = new AttackSkill("arrow");
        normalAttack.apply(player, target);
        
        assertEquals("Un attacco normale non deve cambiare lo stato del target", 
                CharacterState.ID.NORMAL, target.getState().getID());
    }

}

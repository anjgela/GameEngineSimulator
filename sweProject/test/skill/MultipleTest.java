package skill;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import character.Character;
import character.Archer;
import character.Swordsman;
import character.Wizard;

public class MultipleTest {
	private Character player;
	private Character target1;
	private Character target2;
	
	@Before
	public void setUp() {
		player = new Wizard("player");
		target1 = new Archer("target1");
		target2 = new Swordsman("target2");
	}
	
	@Test
	public void applyToMultipleTargetsTest() {
		Skill skill = new Multiple(new AttackSkill("Fireball"));
		
		List<Character> targets = Arrays.asList(target1, target2);
		
		int t1InitialHealth = target1.getHealth();
		int t2InitialHealth = target2.getHealth();
		
		skill.apply(player, targets);
		
		assertTrue(target1.getHealth() < t1InitialHealth);
		assertTrue(target2.getHealth() < t2InitialHealth);
	}
}

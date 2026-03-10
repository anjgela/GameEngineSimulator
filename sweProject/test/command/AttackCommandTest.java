package command;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import engine.BattleEngine;
import character.Character;
import character.Swordsman;
import character.Archer;
import state.Normal;


public class AttackCommandTest {
	
	private BattleEngine engine;
	private Character player;
	private Character target;
	
	@Before
	public void setUp() {
		player = new Swordsman("player");
		target = new Archer("target");
		engine = BattleEngine.getInstance(null, null);
	}

	@Test
	public void executeTest() {
		player.setState(new Normal() {
			@Override
			public float getHitChance() {
				return 1.0f;
			}			
		});
		
		target.setState(new Normal() {			
			@Override
			public float getDodgeChance() {
				return 0.0f;
			}			
		});
		
		Command command = new AttackCommand(player.getAttackSkills().get(0));
		command.setPlayer(player);
		command.setTarget(target);
		int initialHealth = target.getHealth();
		
		command.execute(engine);
		
		assertTrue(target.getHealth() < initialHealth);
	}
	
	@Test
	public void dodgeTest() {
		player.setState(new Normal() {
			@Override 
			public float getHitChance() {
			return 0.0f;
			}
		});
		
		target.setState(new Normal() {
			@Override
			public float getDodgeChance() {
				return 1.0f;
			}
		});
		
		Command command = new AttackCommand(player.getAttackSkills().get(0));
		command.setPlayer(player);
		command.setTarget(target);
		int initialHealth = target.getHealth();
		
		command.execute(engine);
		
		assertEquals(initialHealth, target.getHealth());
	}
}

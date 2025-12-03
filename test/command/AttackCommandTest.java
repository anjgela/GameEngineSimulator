package command;

import static org.junit.Assert.*;

import org.junit.Test;

import engine.BattleEngine;
import character.Character;
import character.Swordsman;
import character.Archer;

public class AttackCommandTest {
	
	private BattleEngine engine;

	@Test
	public void test() {
		Character player = new Swordsman("player");
		Character target = new Archer("target");
		engine = BattleEngine.getInstance(null, null);
		Command command = new AttackCommand(player.getAttackSkills().get(0));
		command.setPlayer(player);
		command.setTarget(target);
		int initialHealth = target.getHealth();
		
		command.execute(engine);
		
		assertTrue(target.getHealth() < initialHealth);
	}

}

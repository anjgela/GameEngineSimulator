package command;

import static org.junit.Assert.*;

import org.junit.Test;

import character.Character;
import character.Wizard;
import engine.BattleEngine;

public class HealCommandTest {

	@Test
	public void executeTest() {
		Character player = new Wizard("player");
		BattleEngine engine = BattleEngine.getInstance(null, null);
		player.takeDamage(30);
		Command command = new HealCommand(player.getHealSkills().get(0));
		command.setPlayer(player);
		command.setTarget(player);
		int damagedHealth = player.getHealth();
		
		command.execute(engine);
		System.out.println(player.getHealth());
		assertTrue(player.getHealth() > damagedHealth);
	}

}

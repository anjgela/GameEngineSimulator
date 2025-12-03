package character;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CharacterTest {
	
	private static Character player; 
	
	@Before
	public void setUp() {
		player = new Archer("player");
	}

	@Test
	public void takeDamageTest() {
		int damage = 30;
	
		player.takeDamage(damage);
	
		assertEquals( Character.MAX_HEALTH-damage, player.getHealth());
	}
	
	@Test
	public void healTest() {
		int damage = 90;
		player.takeDamage(damage);
		int damagedHealth = player.getHealth();
		assertEquals(Character.MAX_HEALTH-damage, damagedHealth);
		int energy = 30;
		
		player.heal(energy);
		
		assertEquals(damagedHealth+energy, player.getHealth());		
	}

	@Test
	public void usePowerStorageTest() {
		int cost = 12;
		player.usePowerStorage(cost);
		
		assertEquals(Character.MAX_POWER_STORAGE-cost, player.getPowerStorage());
	}
	
	@Test
	public void regeneratePowerStorageTest() {
		int cost = 12;
		player.usePowerStorage(cost);
		int currentPower = player.getPowerStorage();
		assertEquals(Character.MAX_POWER_STORAGE-cost, currentPower);
		
		player.regeneratePowerStorage();
		
		assertEquals(currentPower+Character.POWER_STORAGE_REGENERATION_PER_TURN, player.getPowerStorage());
	}
	
	@Test 
	public void isAliveFalseTest() {
		player.takeDamage(10);
		
		assertTrue(player.isAlive());
	}
	
	@Test
	public void isAliveTrueTest() {
		player.takeDamage(Character.MAX_HEALTH);
		
		assertFalse(player.isAlive());
	}

}

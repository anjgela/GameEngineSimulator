package engine;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import character.Character;
import character.Archer;
import character.Swordsman;
import state.CharacterState;

public class BattleEngineTest {
	
	private static BattleEngine engine;
	private static List<Character> greenTeam;
	private static List<Character> pinkTeam;
	private static Character greenChar;
	private static Character pinkChar;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		greenChar = new Archer("green player");
		pinkChar = new Swordsman("pink enemy");
		
		greenTeam = new ArrayList<>();
		pinkTeam = new ArrayList<>();
		
		greenTeam.add(greenChar);
		pinkTeam.add(pinkChar);
		
		engine = BattleEngine.getInstance(greenTeam, pinkTeam);	
		logger = new Logger();
		engine.attach(logger);
	}
	
	@Before
	public void setUp() {
		greenChar.heal(Character.MAX_HEALTH);
		greenChar.restorePowerStorage(Character.MAX_POWER_STORAGE);
		CharacterState.reset(greenChar);
		pinkChar.heal(Character.MAX_HEALTH);
		pinkChar.restorePowerStorage(Character.MAX_POWER_STORAGE);
		CharacterState.reset(pinkChar);
	}

	@Test
	public void notifyObserversTest() {
		Event e = new Event(Event.Type.BATTLE_START, null);
		
		engine.notifyObservers(e);
		
		assertEquals(Event.Type.BATTLE_START, logger.getReceived().type());

	}
	
	@Test
	public void attackSuccedsTest() {
		StubState playerState = new StubState();
		StubState enemyState = new StubState();
		playerState.setHitChance(1.0f);
		enemyState.setDodgeChance(0.0f);
		greenChar.setState(playerState);
		pinkChar.setState(enemyState);
		
		boolean result = engine.attackSucceds(greenChar, pinkChar);
		
		assertTrue(result);
	}
	
	@Test
	public void attackFailsTest() {
		StubState playerState = new StubState();
		playerState.setHitChance(0.0f);
		greenChar.setState(playerState);
		
		boolean result = engine.attackSucceds(greenChar, pinkChar);
		
		assertFalse(result);
	}

//stubs
	public class StubState extends CharacterState {
		private float testHitChance;
		private float testDodgeChance;
		
		public StubState() {
	        super(ID.NORMAL, 999); 
	        this.testHitChance = 0.6f;
	        this.testDodgeChance = 0.3f;
	    }
	
	    public void setHitChance(float chance) {
	        this.testHitChance = chance;
	    }
	
	    public void setDodgeChance(float chance) {
	        this.testDodgeChance = chance;
	    }
	
	    @Override
	    public float getHitChance() {
	        return testHitChance;
	    }
	
	    @Override
	    public float getDodgeChance() {
	        return testDodgeChance;
	    }
	}
}
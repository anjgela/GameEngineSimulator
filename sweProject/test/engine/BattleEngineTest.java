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
		
		engine = BattleEngine.getInstance();
		engine.setupTeams(greenTeam, pinkTeam);
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
	public void attackSucceedsTest() {
		
		CharacterState playerState = new StubState(1.0f, 0.0f);
		CharacterState enemyState = new StubState(0.0f, 0.0f);
		
		greenChar.setState(playerState);
		pinkChar.setState(enemyState);
		
		boolean result = engine.attackSucceeds(greenChar, pinkChar);
		
		assertTrue(result);
	}
	
	@Test
	public void attackFailsTest() {
		CharacterState playerState = new StubState(0.0f, 0.0f);
		greenChar.setState(playerState);
		
		boolean result = engine.attackSucceeds(greenChar, pinkChar);
		
		assertFalse(result);
	}

//stubs
	public class StubState extends CharacterState {
		
		public StubState(float hitChance, float dodgeChance) {
	        super(999, hitChance, dodgeChance); 
	    }
	    
		@Override
		public ID getID() {
			return ID.NORMAL;
		}
	}
}
package command;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import character.Character;
import character.Wizard;
import engine.BattleEngine;
import skill.Skill;
import skill.SkillDecorator;
import state.CharacterState;
import skill.HealSkill;

public class HealCommandTest {
	private BattleEngine engine;
	private Character player;

	@Before
	public void setUp() {
		player = new Wizard("player");
		engine = BattleEngine.getInstance();
	}
	
	@Test
	public void executeTest() {
		player.takeDamage(60);
		int damagedHealth = player.getHealth();
		
		Skill rawSkill = player.getHealSkills().get(0);
		HealSkill baseHealSkill = (HealSkill) ((SkillDecorator) rawSkill).getBaseSkill();
		
		Command command = new HealCommand(player.getHealSkills().get(0));
		command.setPlayer(player);
		command.setTarget(player);
		
		command.execute(engine);
		
		assertEquals(player.getHealth(), damagedHealth+ baseHealSkill.getEffectValue());		
	}
	
	@Test
	public void enhancedStateTransitionTest() {
		assertEquals(CharacterState.ID.NORMAL, player.getState().getID());
		
		player.takeDamage(20); 
		
		Command command = new HealCommand(player.getHealSkills().get(0));
		command.setPlayer(player);
		command.setTarget(player);
		
		command.execute(engine);
		
		assertTrue(player.getHealth() >= 90);
		
		assertEquals(CharacterState.ID.ENHANCED, player.getState().getID());
	}
}

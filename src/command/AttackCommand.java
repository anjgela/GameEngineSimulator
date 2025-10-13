package command;

import engine.BattleEngine;
import engine.Event;
import character.Character;
import skill.AttackSkill;
import skill.Skill;
import skill.SkillDecorator;
import engine.TurnInfo;

public class AttackCommand extends Command {
	private final AttackSkill skill;
	
	public AttackCommand(SkillDecorator skill) {
		super("Attack: " + skill.getName());
		this.skill = (AttackSkill) skill.getBaseSkill();
	}

	public void execute(BattleEngine engine) { 
		int cost = Skill.POWER;
		//if to check if skill is single or multiple. if multiple : cost = POWER *3/2
		if (player.getpowerStorage() < 0) {
			player.restorePowerStorage(cost);
			engine.notifyObservers(new Event(Event.Type.SKILL_FAILED, 
					player.getName() + "lacks power for " + skill.getName()));
			return;
		}
	    if (engine.attackSucceds(player, targets)) {
	        for (Character target : targets) { 
	        	target.takeDamage(skill.getDamage());	
	        }
	        skill.apply(targets);
	        for (Character target : targets) {
	        	engine.notifyObservers(new Event(Event.Type.ATTACK_HIT,new TurnInfo(player, this, true, target, skill.getDamage())));
	        }
	    } else {
	    	for (Character target : targets) {
	    		engine.notifyObservers(new Event(Event.Type.ATTACK_DODGE, new TurnInfo(player, this, false, target, 0)));
	    		}
	    	}
	}
}
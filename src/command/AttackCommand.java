package command;

import engine.BattleEngine;
import engine.Event;
import character.Character;
import skill.Skill;
import skill.SkillDecorator;
import engine.TurnInfo;
import skill.AttackSkill;

public class AttackCommand extends Command {
	private final Skill skill;
	
	public AttackCommand(SkillDecorator skill) {
		super("Attack: " + skill.getName());
		this.skill = skill;
	}

	public void execute(BattleEngine engine) { 
		int cost = Skill.POWER;
		//if to check if skill is single or multiple. if multiple : cost = POWER *3/2
		if (player.getpowerStorage() < cost) {
			player.restorePowerStorage(cost);
			engine.notifyObservers(new Event(Event.Type.SKILL_FAILED, 
					player.getName() + "lacks power for " + skill.getName()));
			return;
		}
		
		player.usePowerStorage(cost);
		
	    if (engine.attackSucceds(player, targets)) {
	        skill.apply(player, targets);
	        for (Character target : targets) {
	        	engine.notifyObservers(new Event(Event.Type.ATTACK_HIT,new TurnInfo(player, this, true, target, findBaseDamage(this.skill))));
	        }
	    } else {
	    	for (Character target : targets) {
	    		engine.notifyObservers(new Event(Event.Type.ATTACK_DODGE, new TurnInfo(player, this, false, target, 0)));
	    		}
	    	}
	}
	
//helper methods
	private int findBaseDamage(Skill s) {
		Skill current = s;
		while (current instanceof SkillDecorator) {
			current = ((SkillDecorator) current).getBaseSkill();
		}
		
		if (current instanceof AttackSkill) {
			return ((AttackSkill) current).getDamage();
		}
		return 0;
	}
}
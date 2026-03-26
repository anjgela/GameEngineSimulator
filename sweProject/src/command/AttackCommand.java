package command;

import java.util.ArrayList;
import java.util.List;

import engine.BattleEngine;
import engine.Event;
import character.Character;
import skill.Skill;
import skill.SkillDecorator;
import engine.TurnInfo;
import skill.AttackSkill;

public class AttackCommand extends Command {
	private final Skill skill;
	
	public AttackCommand(Skill skill) {
		super("Attack: " + skill.getName());
		this.skill = skill;
	}

	@Override
	public List<Event> execute(BattleEngine engine) { 
		List<Event> generatedEvents = new ArrayList<>();
		int cost = Skill.POWER;
		//if to check if skill is single or multiple. if multiple : cost = POWER *3/2
		if (player.getPowerStorage() < cost) {
			generatedEvents.add(new Event(Event.Type.SKILL_FAILED, 
					player.getName() + "lacks power for " + skill.getName()));
			return generatedEvents;
		}
		
		player.usePowerStorage(cost);
		player.regeneratePowerStorage();
		
		if (engine.attackSucceeds(player, targets)) {
	        skill.apply(player, targets);
	        for (Character target : targets) {
	        	generatedEvents.add(new Event(Event.Type.ATTACK_HIT, new TurnInfo(player, this, true, target, skill.getEffectValue())));
	        }
	    } else {
	    	for (Character target : targets) {
	    		generatedEvents.add(new Event(Event.Type.ATTACK_DODGE, new TurnInfo(player, this, false, target, 0)));
	    		}
	    	}
	    return generatedEvents;
	}
	
}
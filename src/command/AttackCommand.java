package command;

import engine.BattleEngine;
import engine.Event;
import character.Character;
import skill.AttackSkill;
import skill.Skill;

public class AttackCommand extends Command {
	private final AttackSkill skill;
	
	public AttackCommand(AttackSkill skill) {
		super("Attack: " + skill.getName());
		this.skill = skill;
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
	        engine.notifyObservers(new Event(Event.Type.ATTACK_HIT, targets)); 
	        //engine.notifyObservers(new Event(Event.Type.ATTACK_HIT, new TurnInfo(player, this, true, targets, damage)));
	    } else {
	        engine.notifyObservers(new Event(Event.Type.ATTACK_DODGE, targets));
	        //engine.notifyObservers(new Event(Event.Type.ATTACK_HIT, new TurnInfo(player, this, false, targets, 0)));
	    }
	}
	
	@Override
    public String getLogMessage() {
        String msg = player.getName() + " uses " + skill.getName() + " on ";
        for (Character target : targets) {
        	msg = msg + target.getName() + ", ";
        }
        return msg.substring(0, msg.length() - 2);
    }
}
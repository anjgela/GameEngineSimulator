package command;

import engine.BattleEngine;
import engine.Event;
import character.Character;
import skill.AttackSkill;

public class AttackCommand extends Command {
	public AttackCommand(int type) {
		switch (type) {
		case 1:
			//single
			break;
		case 2:
			//multiple
			break;
		case 3:
			//poisonous
			break;
		}
	}
	//TODO create helper method to put execute in
	
	public void execute(BattleEngine engine) { 
	    if (engine.attackSucceds(player, targets)) {
	        for (Character target : targets) { 
	        	target.takeDamage(((AttackSkill)player.getCurrentSkill()).getDamage()); //FIXME
	        }
	        engine.notifyObservers(new Event(Event.Type.ATTACK_HIT, targets));
	    } else {
	        engine.notifyObservers(new Event(Event.Type.ATTACK_MISS, targets));
	    }
	}
}
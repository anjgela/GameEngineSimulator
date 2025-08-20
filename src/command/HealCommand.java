package command;

import skill.HealSkill;
import skill.Skill;
import character.Character;
import engine.BattleEngine;
import engine.Event;

public class HealCommand extends Command{
	private final HealSkill skill;
	
	public HealCommand(HealSkill skill) {
		super("Heal: " + skill.getName());
		this.skill = skill;
	}
	
	@Override
    public void execute(BattleEngine engine) {
        int cost = Skill.POWER;
        if (player.getpowerStorage() < 0) {
        	player.restorePowerStorage(cost);
            engine.notifyObservers(new Event(Event.Type.SKILL_FAILED, 
                    player.getName() + " lacks power for " + skill.getName()));
            return;
        }

        int healing = skill.getHealing();
        for (Character target : targets) {
        	target.heal(healing);
        }
        skill.apply(targets);
        engine.notifyObservers(new Event(Event.Type.SKILL_USED, targets));
        //engine.notifyObservers(new Event(Event.Type.SKILL_USED, new engine.TurnInfo(player, this, true, target, healed)));
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

 /*FIXME make attackcommand and healcommand on one target, 
then make engine.start() call several command.execute() on each target if multiple
  */

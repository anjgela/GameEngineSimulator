package command;

import skill.HealSkill;
import skill.Skill;
import skill.SkillDecorator;
import character.Character;
import engine.BattleEngine;
import engine.Event;
import engine.TurnInfo;

public class HealCommand extends Command{
	private final HealSkill skill;
	
	public HealCommand(SkillDecorator skill) {
		super("Heal: " + skill.getName());
		this.skill = (HealSkill) skill.getBaseSkill();
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
        skill.apply(targets);
        for (Character target : targets) {
        	engine.notifyObservers(new Event(Event.Type.SKILL_USED, new TurnInfo(player, this, true, target, skill.getHealing())));
        	}
        }
}

 /*FIXME make attackcommand and healcommand on one target, 
then make engine.start() call several command.execute() on each target if multiple
  */

package command;

import skill.AttackSkill;
import skill.HealSkill;
import skill.Skill;
import skill.SkillDecorator;
import character.Character;
import engine.BattleEngine;
import engine.Event;
import engine.TurnInfo;

public class HealCommand extends Command{
	private final Skill skill;
	
	public HealCommand(Skill skill) {
		super("Heal: " + skill.getName());
		this.skill = skill;
	}
	
	@Override
    public void execute(BattleEngine engine) {
        int cost = Skill.POWER;
        if (player.getPowerStorage() < 0) {
            engine.notifyObservers(new Event(Event.Type.SKILL_FAILED, 
                    player.getName() + " lacks power for " + skill.getName()));
            return;
        }
    	player.restorePowerStorage(cost);
    	
    	skill.apply(player, targets);
    	for (Character target : targets) {
        	engine.notifyObservers(new Event(Event.Type.HEAL,new TurnInfo(player, this, true, target, findBaseHealing(this.skill))));
    	}
    }
//helper methods
    private int findBaseHealing(Skill s) {
    	Skill current = s;
    	while (current instanceof SkillDecorator) {
    		current = ((SkillDecorator) current).getBaseSkill();
    	}
    	
    	if (current instanceof HealSkill) {
    		return ((HealSkill) current).getHealing();
    	}
    	return 0;
    }
        
}

 /*FIXME make attackcommand and healcommand on one target, 
then make engine.start() call several command.execute() on each target if multiple
  */

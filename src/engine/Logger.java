package engine;
import java.util.List;

import character.Character;
import observer.Observer;

public class Logger implements Observer{

	@Override
	public void update(Object object) {
		if (object instanceof Event) {
			Event event = (Event) object;
			switch (event.type()) {
				case BATTLE_START: 
					System.out.println("START FIGHTING!");
					break;
				case TURN_END:
					TurnInfo turnEndInfo = (TurnInfo) event.payload();
					System.out.println("TURN END: " + turnEndInfo.player().getName() + " used " + turnEndInfo.command().getLogMessage());
					break;
				case CHARACTER_DEATH:
					Character player = (Character) event.payload();
					System.out.println(player.getName() + " died");
					break;
				case BATTLE_OVER:
					String winners = (String) event.payload();
					System.out.print(winners + " won!");
					break;
				case ATTACK_HIT:
					TurnInfo attackHitInfo = (TurnInfo) event.payload();
					System.out.println(attackHitInfo.player().getName() + " hit " + attackHitInfo.target().getName() + " for " + attackHitInfo.value());
					break;
				case ATTACK_DODGE:
					TurnInfo attackDodgeInfo = (TurnInfo) event.payload();
					System.out.println(attackDodgeInfo.target().getName() + " dodged " + attackDodgeInfo.player().getName() + "'s attack");
					break;
				case SKILL_FAILED:
					System.out.println("Skill failed: " + event.payload());
					break;
				case SKILL_USED:
					TurnInfo skillUsedInfo = (TurnInfo) event.payload();
					System.out.println(skillUsedInfo.player().getName() + " healed " + skillUsedInfo.target().getName() + " for " + skillUsedInfo.value());
					break;
			}
		}
	}
}
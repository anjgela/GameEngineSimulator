package engine;
import character.Character;
import command.Command;


public record TurnInfo(
		Character player, 
		Command command, 
		boolean success, //success of the action
		Character target, //may be null, if an entire team is affected
		int value //damage or healing, 0 if none
		) {
	/*TODO maybe update with constructor to have less arguments to be passed 
	* or specific methods for specific situations: turn hand, hit, miss, heal,  
	*/
}

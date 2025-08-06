package engine;
import character.Character;
import command.Command;

public record TurnInfo(Character actor, Command command) {
	
}

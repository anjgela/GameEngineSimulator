package command;
import java.util.List;

import character.Character;

import java.util.ArrayList;

public class Command {
	Character player;
	List<Character> targets = new ArrayList<>();
	
	public void setPlayer(Character character) {
		player = character;
	}
	public void setTarget(Character character) {
		targets.add(character);
	}
	public void setTarget(List<Character> characters) {
		targets.addAll(characters);
	}
	public void execute() {
		
	}
	
}

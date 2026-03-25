package command;

import java.util.List;
import java.util.ArrayList;

import character.Character;
import engine.BattleEngine;
import engine.Event;

public abstract class Command { //to maybe later change into AbstractCommand implementing interface Command
	protected Character player;
	protected List<Character> targets = new ArrayList<>();
	protected final String name;
	
	public Command() {
		this.name = "";
	}
	
	protected Command(String name) {
		this.name = name;
	}
	
	public void setPlayer(Character character) {
		player = character;
	}
	
	public void setTarget(Character character) {
		targets.add(character);
	}
	
	public void setTargets(List<Character> characters) {
		targets.addAll(characters);
	}
	
	public String getTargets() {
		String string = "";
		for (int i=0; i<targets.size(); i++) {
			string += targets.get(i).getName();
			if (i != targets.size()-1) {
				string += ", ";
			}
		}
		return string;
	}

	public String getName() {
		return name;
	}
	public abstract List<Event> execute(BattleEngine engine); //receives BattleEngine so Command does not depend on the singleton
}
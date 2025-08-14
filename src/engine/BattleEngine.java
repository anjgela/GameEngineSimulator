package engine;

import java.util.ArrayList;
import java.util.List;

import character.Character;
import command.Command;
import observer.Observable;
import observer.Observer;

public class BattleEngine implements Observable{
	private static BattleEngine instance = null;
	private final List<Character> teamGreen;
	private final List<Character> teamPink;
	private List<Observer> observers = new ArrayList<>();
	private int currentTurnIndex;
	private final List<Character> turnOrder = new ArrayList<>();
	private List<Character> winners = new ArrayList<>();
	
	private BattleEngine(List<Character> green, List<Character> pink) {
		teamGreen = green;
		teamPink = pink;
		turnOrder.addAll(teamPink);	//populate order
		turnOrder.addAll(teamGreen);
		attach(new Logger());	//default logger
	}
	
	//singleton
	public BattleEngine getInstance(List<Character> green, List<Character> pink) {
		if (instance == null) {
			instance = new BattleEngine(green, pink);
		}
		return instance;
	}
	
	//observer methods
	@Override
	public void attach(Observer observer) {
		observers.add(observer);
	}
	
	@Override
	public void detach(Observer observer) {
		observers.remove(observer);
	}
	
	@Override
	public void notifyObservers(Event event) {
		for (Observer observer: observers) {
			observer.update(event);
		}
	}
	
	public void start() {
		notifyObservers(new Event(Event.Type.BATTLE_START, null));
		while (!isBattleOver()) {
			Character player = nextPlayerAlive();
			if (player == null) {
				break;
			}
			Command command = new Command();
			if (teamGreen.contains(player)) {
				command = player.chooseAction(teamPink, teamGreen);
			} else {
				command = player.chooseAction(teamGreen, teamPink);
			}
			
			command.execute();
			
			notifyObservers(new Event(Event.Type.TURN_END, new TurnInfo(player, command)));
			
			checkDeaths(); //death management
		}
		System.out.println("WINNING TEAM: ");
		for (Character winner : winners) {
			System.out.print(winner.getName() + " ");
		}
		
	}

	//helper methods
	
	private boolean isBattleOver() {
		int deaths = 0;
		for (Character character : teamGreen) {
			if (!character.isAlive()) {
				deaths++;
			}
		}
		if (deaths == teamGreen.size()) {
			winners = teamPink;
			return true;
		}
		else {
			deaths = 0;
			for (Character character : teamPink) {
				if (!character.isAlive()) {
					deaths++;
				}
			}
		}
		if (deaths == teamPink.size() ) {
			winners = teamGreen;
			return true;
		}
		return false;
	}

	private Character nextPlayerAlive() {
		int checked = 0;
		while (checked < turnOrder.size()) {
			Character character = turnOrder.get(currentTurnIndex);
			currentTurnIndex = (currentTurnIndex +1) % turnOrder.size(); //cycle
			checked ++;
			if (character.isAlive()) {
				return character;
			}
		}
		return null;
	}
	
	private void checkDeaths() {
		for (Character character : teamGreen) {
			if (!character.isAlive()) {
				notifyObservers(new Event(Event.Type.CHARACTER_DEATH, character));
			}
		}
		for (Character character : teamPink) {
			if (!character.isAlive()) {
				notifyObservers(new Event(Event.Type.CHARACTER_DEATH, character));
			}
		} //FIXME MAYBE REMOVE THE DEATH CHARACTERS FROM TURNORDER AND ADJUST TURNATION IN START
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

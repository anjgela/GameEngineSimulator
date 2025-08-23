package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import character.Character;
import command.Command;
import observer.Observable;
import observer.Observer;

public class BattleEngine implements Observable{
	private static BattleEngine instance = null;
	
	private final List<Character> teamGreen;
	private final List<Character> teamPink;
	public static final int MAX_PLAYERS_PER_TEAM = 3;
	
	private List<Observer> observers = new ArrayList<>();
	
	private int currentTurnIndex = 0;
	private final List<Character> turnOrder = new ArrayList<>();
	
	private String winners;
	
	private BattleEngine(List<Character> green, List<Character> pink) {
		teamGreen = green;
		teamPink = pink;
		turnOrder.addAll(teamGreen);	//populate order
		turnOrder.addAll(teamPink);
		attach(new Logger());	//default logger
	}
	
	//singleton
	public static BattleEngine getInstance(List<Character> green, List<Character> pink) {
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
	public void notifyObservers(Object object) {
		if (object instanceof Event) {
			Event event = (Event) object;
			for (Observer observer: observers) {
				observer.update(event);
			}
		}
	}
	
	public void start() {
		notifyObservers(new Event(Event.Type.BATTLE_START, null));
		while (!isBattleOver()) {
			currentTurnIndex++;
			Character player = nextPlayerAlive();
			if (player == null) {
				break;
			}
			
			Command command;
			if (teamGreen.contains(player)) {
				notifyObservers(new Event(Event.Type.TURN_START_GREEN, player));
				command = player.chooseAction(teamPink, teamGreen);
			} else {
				notifyObservers(new Event(Event.Type.TURN_START_PINK, player));
				command = player.chooseAction(teamGreen, teamPink);
			}
			
			command.execute(this);
			
			for (Character green : teamGreen) {
				if (green.isAlive()) {
					green.regeneratePowerStorage();
				}
			}
			for (Character pink : teamPink) {
				if (pink.isAlive()) {
					pink.regeneratePowerStorage();
				}
			}
			
			notifyObservers(new Event(Event.Type.TURN_END, new TurnInfo(player, command, true, null, 0)));
			
			checkDeaths(); //death management
		}
		
		notifyObservers(new Event(Event.Type.BATTLE_OVER, winners)); //winners computed in .isBattleOver()
	}
	
	//probabilistic logic
	public boolean attackSucceds(Character player, Character target) {
		float hitChance = player.getState().getHitChance();
		float dodgeChance = target.getState().getDodgeChance();
		float successChance = hitChance * (1 - dodgeChance);
		Random rand = new Random();
		return rand.nextFloat() < successChance;
	}
	
	public boolean attackSucceds(Character player, List<Character> targets) {
		float hitChance = player.getState().getHitChance();
		int successes = 0;
		for (Character target : targets) {
			float dodgeChance = target.getState().getDodgeChance();
			float successChance = hitChance * (1 - dodgeChance);
			Random rand = new Random();
			if (rand.nextFloat() < successChance) {
				successes++;
			}
		}
		if (successes > targets.size()/2) {
			return true;
		}
		 
		return false;
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
			winners = "team Pink";
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
			winners = "team Green";
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

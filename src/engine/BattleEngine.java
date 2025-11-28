package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Map;
import java.util.LinkedHashMap;

import character.Character;
import command.Command;
import observer.Observable;
import observer.Observer;

public class BattleEngine implements Observable{
	private static BattleEngine instance = null;
	
	private final List<Character> greenTeam;
	private final List<Character> pinkTeam;
	public static final int MAX_PLAYERS_PER_TEAM = 3;
	
	private List<Observer> observers = new ArrayList<>();
	
	private String currentTeam = "green";
	private int currentGreenIndex = 0;
	private int currentPinkIndex = 0;
	private Map<String, List<Character>> turnOrder = new LinkedHashMap<>();
	
	private String winners;
	
	private BattleEngine(List<Character> green, List<Character> pink) {
		greenTeam = green;
		pinkTeam = pink;
		//populate order
		turnOrder.put("green", greenTeam);
		turnOrder.put("pink", pinkTeam);
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
			Character player = nextPlayerAlive();
			if (player == null) {
				break;
			}
			
			Command command;
			if (greenTeam.contains(player)) {
				notifyObservers(new Event(Event.Type.TURN_START_GREEN, player));
				command = player.chooseAction(pinkTeam, greenTeam);
			} else {
				notifyObservers(new Event(Event.Type.TURN_START_PINK, player));
				command = player.chooseAction(greenTeam, pinkTeam);
			}
			
			command.execute(this);
			//add notifyObserver for various command types (ATTACK HIT, ATTACK DODGE, HEAL, ..
			
			for (Character green : greenTeam) {
				if (green.isAlive()) {
					green.regeneratePowerStorage();
				}
			}
			for (Character pink : pinkTeam) {
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
		for (Character character : greenTeam) {
			if (!character.isAlive()) {
				deaths++;
			}
		}
		if (deaths == greenTeam.size()) {
			winners = "team Pink";
		}
		else {
			deaths = 0;
			for (Character character : pinkTeam) {
				if (!character.isAlive()) {
					deaths++;
				}
			}
		}
		if (deaths == pinkTeam.size() ) {
			winners = "team Green";
			return true;
		}
		return false;
	}

	private Character nextPlayerAlive() {

		List<Character> greenAlive = new ArrayList<>();
		for (Character greenCharacter: turnOrder.get("green")) {
			if (greenCharacter.isAlive()) {
				greenAlive.add(greenCharacter);
			}
		}

		List<Character> pinkAlive = new ArrayList<>();
		for (Character pinkCharacter: turnOrder.get("pink")) {
			if (pinkCharacter.isAlive()) {
				pinkAlive.add(pinkCharacter);
			}
		}
		
		if (greenAlive.isEmpty() || pinkAlive.isEmpty()) {
			return null;
		}
		
		Character next;
		if (currentTeam == "green") {
			next = turnOrder.get("green").get(currentGreenIndex);
			currentGreenIndex = (currentGreenIndex+1) % greenAlive.size();
			currentTeam = "pink";
		}
		else {
			next = turnOrder.get("pink").get(currentPinkIndex);
			currentPinkIndex = (currentPinkIndex+1) % pinkAlive.size();
			currentTeam = "green";
		}
		return next;
	}
	
	private void checkDeaths() {
		turnOrder.put("green", greenTeam.stream().filter(Character::isAlive).toList());
		turnOrder.put("pink", pinkTeam.stream().filter(Character::isAlive).toList());
		
		for (Character character : greenTeam) {
			if (!character.isAlive()) {				
				notifyObservers(new Event(Event.Type.CHARACTER_DEATH, character));
			}
		}
		for (Character character : pinkTeam) {
			if (!character.isAlive()) {				
				notifyObservers(new Event(Event.Type.CHARACTER_DEATH, character));
			}
		}
	}
}
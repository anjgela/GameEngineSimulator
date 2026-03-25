package state;

import java.util.Random;

import character.Character;

public class Poisoned extends CharacterState {
	private static final int POWER_DECREASE = 10;
	
	public Poisoned(int duration) {
		super(duration, 0.5F, 0.2F);
	}
	
	@Override
	public ID getID() {
		return ID.POISONED;
	}
	
	//attack damage to other lowers
	//chances of defense lower 

}

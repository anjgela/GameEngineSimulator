package state;

import java.util.Random;

import character.Character;

public class Slowed extends CharacterState {
	
	public Slowed(int duration) {
		super(duration, 0.5F, 0.2F);
	}
	
	@Override 
	public ID getID() {
		return ID.SLOWED;
	}
	
	//chances of hitting target lower
	//chances of defense lower
	//gets triggered when target has health<=30 and gets hit by player in an enhanced state
	
}

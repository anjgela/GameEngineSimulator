package state;

import java.util.Random;

import character.Character;

public class Normal extends CharacterState {
	
	public Normal() {
		super(0.6F, 0.3F);
	}
	
	public Normal(int duration) {
		super(duration, 0.6F, 0.3F);
	}
	
	@Override
	public ID getID() {
		return ID.NORMAL;
	}	
}
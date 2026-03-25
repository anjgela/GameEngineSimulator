package state;
import java.util.Random;

import character.Character;

public class Enhanced extends CharacterState {
	private static final int POWER_INCREASE = 10;
	
	public Enhanced(int duration) {
		super(duration, 0.7F, 0.4F);
		}
	
	@Override
	public ID getID() {
		return ID.ENHANCED;
	}
	
		
	//chances of hitting target boost
	//attack damage to other boosts
}

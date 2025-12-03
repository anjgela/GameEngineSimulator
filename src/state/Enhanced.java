package state;
import java.util.Random;

import character.Character;

public class Enhanced extends CharacterState {
	private static final CharacterState.ID id = CharacterState.ID.ENHANCED;
	private static final float HIT_CHANCE = 0.7F;
	private static final float DODGE_CHANCE = 0.4F;
	private static final int POWER_INCREASE = 3;
	
	public Enhanced(int duration) {
		super(id, duration);
		}
	
	@Override
	public float getHitChance() {
		return HIT_CHANCE;
	}

	@Override
	public float getDodgeChance() {
		return DODGE_CHANCE;
	}
		
	//chances of hitting target boost
	//attack damage to other boosts
}

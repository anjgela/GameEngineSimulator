package state;

import java.util.Random;

import character.Character;

public class Poisoned extends CharacterState {
	private static final CharacterState.ID id = CharacterState.ID.POISONED;
	private static final float HIT_CHANCE = 0.5F;
	private static final float DODGE_CHANCE = 0.2F;
	private static final int POWER_DECREASE = 10;
	
	public Poisoned(int duration) {
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
	
	//attack damage to other lowers
	//chances of defense lower 

}

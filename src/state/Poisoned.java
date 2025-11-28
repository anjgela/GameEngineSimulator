package state;

import java.util.Random;

import character.Character;

public class Poisoned extends CharacterState {
	private static CharacterState.ID id = CharacterState.ID.POISONED;
	private static final float HIT_CHANCE = 0.5F;
	private static final int POWER_DECREASE = 3;
	private static final float DODGE_CHANCE = 0.2F;
	
	public Poisoned(int duration) {
		super(id, duration);
	}
	
	public Poisoned(ID id) {
		super(id);
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

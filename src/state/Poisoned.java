package state;

import java.util.Random;

import character.Character;

public class Poisoned extends CharacterState {
	private CharacterState.ID id = CharacterState.ID.POISONED;
	private static final float HIT_CHANCE = 0.5F;
	private static final int POWER_DECREASE = 3;
	private static final float DODGE_CHANCE = 0.5F;
	
	public Poisoned(ID id, int duration) {
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
	
	//attack power lowers
	//chances of defense lower 

}

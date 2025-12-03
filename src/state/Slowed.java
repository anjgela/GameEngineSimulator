package state;

import java.util.Random;

import character.Character;

public class Slowed extends CharacterState {
	private static final CharacterState.ID id = CharacterState.ID.SLOWED;
	private static final float HIT_CHANCE = 0.5F;
	private static final float DODGE_CHANCE = 0.2F;
	
	public Slowed(int duration) {
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

	//chances of hitting target lower
	//chances of defense lower
	
}

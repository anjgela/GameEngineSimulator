package state;

import java.util.Random;

import character.Character;

public class Slowed extends CharacterState {
	private CharacterState.ID id = CharacterState.ID.SLOWED;
	private static final float HIT_CHANCE = 0.5F;
	private static final float DODGE_CHANCE = 0.5F;
	
	public Slowed(ID id, int duration) {
		super(id, duration);
	}
	
	public Slowed(ID id) {
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
	
	//chances of hitting target lower
	//chances of defense lower
	
}

package state;

import java.util.Random;

import character.Character;

public class Normal extends CharacterState {
	private static final CharacterState.ID id= CharacterState.ID.NORMAL;
	private static final float HIT_CHANCE = 0.6F;
	private static final float DODGE_CHANCE = 0.3F;
	
	public Normal() {
		super(id);
	}
	
	public Normal(int duration) {
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
	
}


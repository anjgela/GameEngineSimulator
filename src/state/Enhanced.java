package state;
import java.util.Random;

import character.Character;

public class Enhanced extends CharacterState {
	private CharacterState.ID id = CharacterState.ID.ENHANCED;
	private static final float HIT_CHANCE = 0.7F;
	private static final int POWER_INCREASE = 3;
	private static final float DODGE_CHANCE = 0.7F;
	
	public Enhanced(ID id, int duration) {
		super(id, duration);
		}
	
	public Enhanced(ID id) {
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
	
	//chances of hitting target boost
	//attack damage to other boosts
}

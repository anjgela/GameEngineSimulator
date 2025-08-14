package state;
import java.util.Random;

import character.Character;

public class Enhanced implements CharacterState {
	private CharacterState.ID id = CharacterState.ID.ENHANCED;
	private static final float HIT_CHANCE = 0.7F;
	private static final int POWER_INCREASE = 3;
	private static final float MISS_CHANCE = 0.7F;
	
	@Override
	public CharacterState.ID getID() {
		return id;
	}

	@Override
	public float hitChance(Character player) {
		Random rand = new Random();
		return rand.nextFloat();
	}

	@Override
	public float missChance(Character player) {
		Random rand = new Random();
		return rand.nextFloat();
	}

	@Override
	public int attackPower(Character player) {
		//return player.getAttackPower() + POWER_INCREASE;
		return -1;
	}

	@Override
	public void setHitChance(Character player) {
		player.setHitChance(HIT_CHANCE);
	}

	@Override
	public void setMissChance(Character player) {
		player.setMissChance(MISS_CHANCE);
	}

	@Override
	public void onTurnStart() {
		// TODO Auto-generated method stub
		
	}
	
	//chances of hitting target boost
	//attack power boosts
}

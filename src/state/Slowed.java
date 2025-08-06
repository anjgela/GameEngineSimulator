package state;

import java.util.Random;

import character.Character;

public class Slowed implements CharacterState {
	private CharacterState.ID id = CharacterState.ID.SLOWED;
	private static final float HIT_CHANCE = 0.5F;
	private static final float MISS_CHANCE = 0.5F;

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
		return player.getAttackPower();
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
	

	//chances of hitting target lower
	//chances of defense lower
	
}

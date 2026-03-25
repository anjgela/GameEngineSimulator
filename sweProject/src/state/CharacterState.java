package state;


import character.Character;

public abstract class CharacterState {

	public static enum ID {NORMAL, SLOWED, POISONED, ENHANCED};
	protected int remainingTurns;
	protected final float hitChance;
	protected final float dodgeChance;
	
	protected CharacterState( int duration, float hitChance, float dodgeChance) {
		this.remainingTurns = duration;
		this.hitChance = hitChance;
		this.dodgeChance = dodgeChance;
		}
	
	protected CharacterState(float hitChance, float dodgeChance) {
		remainingTurns = 100;
		this.hitChance = hitChance;
		this.dodgeChance = dodgeChance;
	}
	
	public abstract ID getID();

	public float getHitChance() {
		return hitChance;
	}
	public float getDodgeChance() {
		return dodgeChance;
	}
		
	public void onTurnStart(Character player) {
		if (remainingTurns > 0) {
			applyState(player);
			remainingTurns--;
		}
		else {
			reset(player);
		}
	}
	
	public void applyState(Character player) {
		player.setState(this);
	}
	
	public static void reset(Character player) {
		player.setState(new Normal(100));
	}
	
	
}

	
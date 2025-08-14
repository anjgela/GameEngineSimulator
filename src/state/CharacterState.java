package state;


import character.Character;

public abstract class CharacterState {

	public static enum ID {NORMAL, SLOWED, POISONED, ENHANCED};
	protected final ID id;
	protected int remainingTurns;
	
	protected CharacterState(ID id, int duration) {
		this.id = id;
		this.remainingTurns = duration;
		}
	protected CharacterState(ID id) {
		this.id = id;
	}
	public ID getID() {
		return id;
	}
	
	public abstract float getHitChance();
	public abstract float getDodgeChance();
	
	
	public void onTurnStart(Character player) {
		if (remainingTurns > 0) {
			applyState(player);
			remainingTurns--;
		}
		else {
			onExpire(player);
		}
	}
	
	public void applyState(Character player) {
		player.setState(this);
	}
	
	public void onExpire(Character player) {
		player.setState(new Normal(ID.NORMAL, 100));
	}
	
}

	
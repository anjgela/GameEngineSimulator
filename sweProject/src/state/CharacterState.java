package state;


import character.Character;

public abstract class CharacterState {

	public static enum ID {NORMAL, SLOWED, POISONED, ENHANCED};
	private ID id;
	protected int remainingTurns;
	
	protected CharacterState(ID id, int duration) {
		this.remainingTurns = duration;
		this.id = id;
		}
	
	protected CharacterState(ID id) {
		remainingTurns = 100;
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

	
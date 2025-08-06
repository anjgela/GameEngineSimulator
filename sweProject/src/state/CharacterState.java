package state;
import character.Character;

public interface CharacterState {

	enum ID {NORMAL, SLOWED, POISONED, ENHANCED};
	
	public ID getID();
	public float hitChance(Character player);	//attack
	public float missChance(Character player);	//defense
	public int attackPower(Character player);
	
	public void setHitChance(Character player);
	public void setMissChance(Character player);
	
	public void onTurnStart();
	
	default void apply(Character player) {
		player.setState(this);
	}
	
	
}

	
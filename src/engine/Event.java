package engine;

public record Event(Type type, Object payload) {
	public enum Type {
		BATTLE_START, 
		TURN_START_GREEN,
		TURN_START_PINK,
		TURN_END, 
		CHARACTER_DEATH, 
		BATTLE_OVER, 
		ATTACK_HIT, 
		ATTACK_DODGE, 
		SKILL_USED, 
		SKILL_FAILED}
}

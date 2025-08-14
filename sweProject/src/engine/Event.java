package engine;

public record Event(Type type, Object payload) {
	public enum Type {BATTLE_START, TURN_END, CHARACTER_DEATH, BATTLE_OVER, ATTACK_HIT, ATTACK_MISS}
}

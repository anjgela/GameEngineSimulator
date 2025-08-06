package characterFactory;

import character.Character;
import character.Swordsman;

public class SwordsmanFactory extends AbstractCharacterFactory {
	public Character createCharacter() {
		return new Swordsman();
	}
}

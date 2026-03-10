package characterFactory;

import character.Character;
import character.Swordsman;

public class SwordsmanFactory extends AbstractCharacterFactory {
	public Character createCharacter(String name) {
		return new Swordsman(name);
	}
}

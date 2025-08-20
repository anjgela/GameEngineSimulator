package characterFactory;

import character.Character;
import character.Archer;

public class AcrherFactory extends AbstractCharacterFactory {
	public Character createCharacter(String name) {
		return new Archer(name);
	}
}

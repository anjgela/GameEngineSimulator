package characterFactory;

import character.Character;
import character.Archer;

public class AcrherFactory extends AbstractCharacterFactory {
	public Character createCharacter() {
		return new Archer();
	}
}

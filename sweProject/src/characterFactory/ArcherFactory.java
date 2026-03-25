package characterFactory;

import character.Character;
import character.Archer;

public class ArcherFactory extends AbstractCharacterFactory {
	public Character createCharacter(String name) {
		return new Archer(name);
	}
}

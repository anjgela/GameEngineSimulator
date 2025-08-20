package characterFactory;

import character.Character;
import character.Wizard;

public class WizardCharacter extends AbstractCharacterFactory {
	public Character createCharacter(String name) {
		return new Wizard(name);
	}
}

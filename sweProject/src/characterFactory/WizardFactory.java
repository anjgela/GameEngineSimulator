package characterFactory;

import character.Character;
import character.Wizard;

public class WizardFactory extends AbstractCharacterFactory {
	public Character createCharacter(String name) {
		return new Wizard(name);
	}
}

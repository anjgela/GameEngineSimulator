package characterFactory;

import character.Character;
import character.Wizard;

public class WizardCharacter extends AbstractCharacterFactory {
	public Character createCharacter() {
		return new Wizard();
	}
}

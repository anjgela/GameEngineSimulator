package skill;

import java.util.List;

import character.Character;
import state.CharacterState;
import state.Poisoned;

public class Poisonous extends SkillDecorator {
	String name;
	public Poisonous(Skill base) {
		super(base);
	}
	
	public void apply(Character target) {
		super.apply(target);
		target.setState(new Poisoned(3));
	}
	
    public String getName() {
        return "Poisonous " + base.getName();
    }
}

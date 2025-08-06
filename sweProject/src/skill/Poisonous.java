package skill;

import java.util.List;

import character.Character;
import state.Poisoned;

public class Poisonous extends SkillDecorator {
	String name;
	public Poisonous(Skill base) {
		super(base);
	}
	
	public void apply(Character target) {
		super.apply(target);
		target.setState(new Poisoned());
	}
	
    public String getName() {
        return "Poisonous " + base.getName();
    }

}

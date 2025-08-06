package skill;

import java.util.List;

import character.Character;

public class Multiple extends SkillDecorator {
	public Multiple(Skill base) {
        super(base);
    }

    @Override
    public String getName() {
        return "Multiple " + base.getName();
    }
}

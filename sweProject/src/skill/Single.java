package skill;

import character.Character;

public class Single extends SkillDecorator {

	public Single(Skill base) {
		super(base);
	}
	
	@Override
	public String getName() {
        return "Single " + base.getName();
    }
	
	@Override
	public TargetType getTargetType() {
		return TargetType.SINGLE;
	}

}

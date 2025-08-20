package skill;

import character.Character;

public class Single extends SkillDecorator {

	public Single(Skill base) {
		super(base);
		// TODO Auto-generated constructor stub
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

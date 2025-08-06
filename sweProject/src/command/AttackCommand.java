package command;

public class AttackCommand extends Command {
	public AttackCommand(int type) {
		switch (type) {
		case 1:
			//single
			break;
		case 2:
			//multiple
			break;
		case 3:
			//poisonous
			break;
		}
	}
	//TODO create helper method to put execute in
}
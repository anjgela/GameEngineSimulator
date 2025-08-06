package engine;
import java.util.List;

import character.Character;
import observer.Observer;

public class Logger implements Observer{

	@Override
	public void update(Event event) {
		switch (event.type()) {
			case BATTLE_START: 
				System.out.println("START FIGHTING!");
			case TURN_END:
				System.out.println(event.payload());
			case CHARACTER_DEATH:
				Character player = (Character) event.payload();
				System.out.println(player.getName() + " is dead");
			case BATTLE_OVER:
				List<Character> winners = (List<Character>) event.payload();
				System.out.println(winners.getFirst().getName());
				if (winners.size() > 1) {
					System.out.print(" and " + winners.getLast().getName());
				}
				System.out.print(" WON! ");
		}
		
	}
	
}

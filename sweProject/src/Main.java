import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import character.Archer;
import character.Character;
import character.Swordsman;
import character.Wizard;
import engine.BattleEngine;

public class Main { //TODO maybe create a new class "Menu" in engine to manage setup??
	public static void main(String[] args) {
		List<Character> teamGreen = new ArrayList<>();
		List<Character> teamPink = new ArrayList<>();
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("BUTTING HEADS "
				+ "\nChoose mode: 1) 1V1, 2)2V2, 3)3V3"
				+ "\nPlease enter the corresponding number >> ");
		int mode;
		while (true) {
			if (scanner.hasNextInt()) {
				mode = scanner.nextInt();
				scanner.nextLine();	//consume new line after int
				if (mode >= 1 && mode <= 3) {
					break;
				} else {
					scanner.nextLine();	//discard non-integer
				}
				System.out.println("Invalid number. Enter 1, 2 or 3 >>");
			}
		}
			
			Character[] players = new Character[mode*2];
			
			
			for (int i=0; i < players.length; i++) {
				if (i == 0) {//first half is one team
					System.out.println("TEAM GREEN"); 
				}
				if (i == players.length/2) { //second half is the other team
					System.out.println("TEAM PINK"); 
				}
				int p = i+1;
				String choice;
				while (true) {
					System.out.println("player" + p + " choose your fighter: A) WIZARD, B) SWORDSMAN, C) ARCHER"
							+ "\nPlease enter the corresponding letter >> ");
					choice = scanner.nextLine().trim().toUpperCase();
					if (choice.equals("A") || choice.equals("B") || choice.equals("C")) {
						break;
					}
					System.out.println("Invalid choice. Enter A, B or C >>");
				}
				
			switch (choice) {
				case "A":
					players[i] = new Wizard("");
					break;
				case "B":
					players[i] = new Swordsman("");
					break;
				case "C":
					players[i] = new Archer("");
					break;
			}
		
			System.out.println("Choose your name: ");
			String name = scanner.nextLine().trim();
			players[i].setName(name);
			scanner.nextLine();
			
			if (i < players.length/2) { //first half is one team
				teamGreen.add(players[i]);
			} else { //second half is the other team
				teamPink.add(players[i]);
			}
			if (i < players.length - 1) {
				System.out.println("Next!");
			}
		}
		BattleEngine engine = BattleEngine.getInstance(teamGreen, teamPink);
		engine.start();
	}
}
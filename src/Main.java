import java.util.Scanner;

import character.Archer;
import character.Character;
import character.Swordsman;
import character.Wizard;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("BUTTING HEADS "
				+ "\nChoose mode: 1) 1V1, 2)2V2, 3)3V3"
				+ "\nPlease enter the corresponding number>> ");
		int mode = scanner.nextInt();		
		Character[] players = new Character[mode*2];
		
		int i = 0;
		while (i < mode*2) {
			int p = ++i;
			System.out.println("player" + p + " choose your fighter: A) WIZARD, B) SWORDSMAN, C) ARCHER"
					+ "\nPlease enter the corresponding letter>> ");
			String choice = scanner.next().toUpperCase();
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

			String name = scanner.nextLine();
			players[i].setName(scanner.nextLine());
			
			if (i < mode*2-1) {
				System.out.println("Next!");
			}
			i++;
		}
	}
}

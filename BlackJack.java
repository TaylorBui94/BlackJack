/*
 * This is a blackjack casino game that will create a menu, deal 4 cards and show the remaining
 * number of cards in the deck, shuffle the deck and show cards, Play BlackAcjk, and has a menu to exit.
 * The player whas a credit amount which they can bet money as well as choose to hit or stand during the game
 * 
 * 
 * This Program was written on June 8 2018 by :
 * Jaskaran Sethi 
 * Garrett Hirose
 * Taylor Bui     
 * 
*/



import java.util.*;

public class BlackJack {

	static Scanner in = new Scanner(System.in);
	static float startingBet = 100;
	public static void main(String[] args) {

		Deck myDeck = new Deck();
		
		
		int option = menu();

		Card C;

		while (option != 5) {

			if (option == 1) {

				System.out.println("New Deck is created!");

				myDeck = new Deck();
				myDeck.display();

			} else if (option == 2) {
		
				System.out.println(myDeck.remove()); 
				
			} else if (option == 3) {

				myDeck.shuffle();

				System.out.println("Your Deck is shuffled!");
				myDeck.display();

			} else if (option == 4) {

				startGame(myDeck);

			} else {

				System.out.println("Invalid input! Please try again.");

			}

			System.out.println();

			option = menu();

		}

	}



	public static int menu() {

		System.out.println("Casinos!	BlackJack ");

		System.out.println("1) Create a new deck. ");

		System.out.println("2) Deal 4 cards and show the number of remainder cards. ");

		System.out.println("3) Shuffle the deck & show cards. ");

		System.out.println("4) Play the BlackJack game. ");

		System.out.println("5) Exit. ");

		System.out.println("Please enter your option: ");

		int option = in.nextInt();	

		return option;

	}

	public static void startGame(Deck myDeck) {

		float bet;
		
		Scanner in = new Scanner(System.in);
		Player player = new Player();
		Player dealer = new Player();
		String choice;
		
		myDeck = new Deck();
		myDeck.shuffle();
		System.out.println("You have " + startingBet + " dollars.");
		System.out.println("Place your bet...");
		bet = in.nextFloat();

		while (bet > startingBet) {
			System.out.println("Insufficient Funds, You have " + startingBet + " dollars. Try Again...");
			bet = in.nextFloat();
		}
		System.out.println("Loading...");
		System.out.println();
		Card c1 = myDeck.deal();
		player.drawCard(c1);
		
		System.out.print("Player: ");

		Card c2 = myDeck.deal();
		c2.setFaceDown();
		dealer.drawCard(c2);

		Card c3 = myDeck.deal();
		player.drawCard(c3);

		Card c4 = myDeck.deal();
		dealer.drawCard(c4);

		player.displayHand();

		System.out.println();

		System.out.print("Your total: ");
		System.out.print(player.getValue());

		System.out.println();

		if (player.getValue() == 21)
			System.out.println("BLACKJACK!!!");

		System.out.println();
		System.out.println();

		System.out.print("Dealer: ");

		dealer.displayHand();
		System.out.println();
		System.out.print("Dealer total: ");
		System.out.print(dealer.getValue());

		
		if (dealer.getValue() == 21 && player.getValue() != 21 && c2.getValue() == 11) {
			c2.setFaceUp();
			System.out.println("Dealer's hand: ");
			dealer.displayHand();

			System.out.print("Dealer's Total : ");
			System.out.println(dealer.getValue());
			System.out.println("Dealer has a BlackJack!!!");
				youLose(bet, myDeck);
		}

		System.out.println();
		System.out.println();

		System.out.println("Hit? (H)  or Stand? (S)");
		choice = in.next();
		
		if (choice.charAt(0) == 'h' || choice.charAt(0) == 'H') {
			while (choice.charAt(0) == 'h' || choice.charAt(0) == 'H') {
				int i = 0;

				Card ci = myDeck.deal();
				player.drawCard(ci);
				System.out.print("There you go-------> ");
				
				ci.display();
				System.out.println();
				System.out.println();
				System.out.print("Your Hand: ");
				player.displayHand();
				System.out.println();
				System.out.print("Your total: ");
				System.out.print(player.getValue());

				System.out.println();
				System.out.println();

				if (player.getValue() <= 21) {
					System.out.println("Hit? (H)  or Stand? (S)");
					choice = in.next();
				} else {
						youLose(bet, myDeck);
						break;
				}
			}

		}

		while (choice.charAt(0) == 'S' || choice.charAt(0) == 's') {
			int i = 0;
			c2.setFaceUp();

			System.out.print("Dealer's hand: ");
			dealer.displayHand();
			System.out.println();

			while (dealer.getValue() < 17) {
				System.out.print("Dealer draws card ");
				Card ci = myDeck.deal();
				dealer.drawCard(ci);
				ci.display();
				System.out.println();

			}
			System.out.println();
			System.out.print("Dealer's total: ");
			System.out.println(dealer.getValue());

			System.out.println();

			if (dealer.getValue() > 21 || dealer.getValue() < player.getValue()) {
				youWin(bet, myDeck);

			}
			if (dealer.getValue() == player.getValue()) {
				push(myDeck);
				break;
			}

			else {
				youLose(bet, myDeck);
			}
		}

	}

	public static void push(Deck myDeck) {
		Scanner input = new Scanner(System.in);
		int choice = 0;
		System.out.println("PUSH");
		System.out.println("Play again? Press 1");
		System.out.println("Main Menu? press [2] ");
		choice = input.nextInt();
		System.out.println();

		if (choice == 1) {
			startGame(myDeck);
		}

		else if (choice == 2) {
		menu();
		}
	}

	public static void youLose(float lostBet, Deck myDeck) {
		Scanner input = new Scanner(System.in);
		int choice = 0;

		System.out.println("YOU LOSE");

		System.out.println("You have " + moneyLeft(lostBet) + " left");

		startingBet = moneyLeft(lostBet);

		while (startingBet <= 0.0) {
			gameEnd();
			startingBet = 100;
		}
		System.out.println("Play again? Press 1");
		choice = input.nextInt();

		System.out.println();

		if (choice == 1) {
			startGame(myDeck);
		}
		else {
			menu();
		}

	}

	public static void youWin(float wonBet, Deck myDeck) {
		Scanner input = new Scanner(System.in);
		int choice = 0;
		System.out.println("YOU WIN");
		System.out.println("You have " + moneyWon(wonBet) + " dollars with you");
		startingBet = moneyWon(wonBet);

		System.out.println("Play again? Press 1");
		choice = input.nextInt();

		System.out.println();

		if (choice == 1) {
			startGame(myDeck);
		}
	}

	public static float moneyLeft(float moneyLost) {
		float bet = startingBet;
		float money;
		bet -= moneyLost;
		money = bet;

		return money;
	}

	public static float moneyWon(float moneyWon) {
		float bet = startingBet;
		float money;
		bet += moneyWon;
		money = bet;

		return money;
	}

	public static void gameEnd() {
		System.out.println("Good Game(s), We have reset your cash to 100 dollars");
	}

	
}


import java.util.Scanner;

public class run {

	static boolean gameOn = true;
	static Scanner sc = new Scanner(System.in);
	static int numOfPlayers;
	static int n = 1;
	static int hitCounter = 1;
	static int hitCounterD = 2;

	// Skapa dealer
	static Hand dealer = new Hand();

	// Skapa kortlek
	static Deck d = new Deck();

	// Skapa spelare
	static Hand player = null;
	static Hand[] players = null;

	public static void main(String[] args) {

		// Sätter antalet spelare..
		System.out.println("How many players?: ");
		numOfPlayers = sc.nextInt();

		// Skapa/blanda kortlek
		createDeck();

		// Sätta antal spelare efter user input
		nrOfPlrs();

		// Ge kort till spelarna
		dealCards();

		// Ge 2 kort till dealer
		d.deal(dealer, 2);

		// Visa spelarnas händer
		showPlrHands();

		// Flippa dealerns första kort
		dealer.cards.get(0).flipCard();

		// Visa dealerns kort
		System.out.println("\nDealers cards: \n" + dealer.showHand());

		// hit/stay/split
		if (numOfPlayers < 2) {
			playerChoice();
		} else {
			playersChoice();
		}

	}

	static void createDeck() {
		d.makeCards();
		d.shuffle();
	}

	static void nrOfPlrs() {
		if (numOfPlayers == 1) {
			player = new Hand();
		} else {
			players = new Hand[numOfPlayers];
			for (int i = 0; i < numOfPlayers; i++) {
				players[i] = new Hand();
			}
		}
	}

	static void dealCards() {
		if (numOfPlayers == 1) {
			d.deal(player, 2);
		} else {
			d.deal(players, 2);
		}
	}

	static void showPlrHands() {
		if (numOfPlayers > 1) {
			for (int i = 0; i < players.length; i++) {
				players[i].flipCard();
				System.out.println("Player " + n + ":\n" + players[i].showHand());
				n++;
			}
		} else {
			player.flipCard();
			System.out.println(player.showHand());
		}
	}

	static void hitPlayer() {
		d.deal(player, 1);
		hitCounter++;
		player.cards.get(hitCounter).flipCard();
		System.out.println(player.showHand());
	}

	static void hitDealer() {
		d.deal(dealer, 1);
		dealer.cards.get(hitCounterD).flipCard();
		System.out.println(dealer.showHand());
	}

	static void playerChoice() {
		boolean run = true;
		while (run) {
			if (player.getTotal() < 22) {
				System.out.println(player.showHand());
				System.out.println("hit or stay?");
				String input = sc.next();
				if (input.equalsIgnoreCase("hit")) {
					hitPlayer();
				} else if (input.equalsIgnoreCase("stay")) {
					dealerScore();
					run = false;
				}
			} else {
				System.out.println("You got fat! \n" + "Total points: " + player.getTotal());
				run = false;
			}
		}
	}

	static void dealerScore() {
		dealer.cards.get(1).flipCard();
		System.out.println("\nDealer has: \n" + dealer.showHand());
		while (dealer.getTotal() < 17) {
			System.out.println("Dealer has: " + dealer.getTotal() + "\nDealer have to hit..\n");
			hitDealer();
			hitCounterD += 1;
		}
		if (dealer.getTotal() > 16 && dealer.getTotal() < 22) {
			if (numOfPlayers < 2) {
				checkScore();
			} else {
				checkScores();
			}
		} else {
			System.out.println("You win! Dealer got bust!");
		}
	}

	static void checkScore() {
		if (dealer.getTotal() > player.getTotal()) {
			System.out.println("You lose!");
		} else if (dealer.getTotal() < player.getTotal()) {
			System.out.println("You win!");
		} else if (dealer.getTotal() < player.getTotal() && player.getTotal() < 22) {
			System.out.println("It´s a tie!");
		}
	}

	static void playersChoice() {
		boolean run = true;
		while (run) {
			for (int i = 0; i < players.length; i++) {
				if (players[i].getTotal() < 22) {
					System.out.println(players[i].showHand());
					i++;
					System.out.println("Player " + i + ". Hit or stay?");
					i--;
					String input = sc.next();
					if (input.equalsIgnoreCase("hit")) {
						d.deal(players[i], 1);
						hitCounter += 1;
						players[i].cards.get(hitCounter).flipCard();
						System.out.println(players[i].showHand());
						i--;
					} else if (input.equalsIgnoreCase("stay")) {
						hitCounter = 1;
						continue;
					}
				} else {
					hitCounter = 1;
					i++;
					int t = i;
					i--;
					System.out.println(
							"Player " + t + " got fat, fatso! \n" + "Total points: " + players[i].getTotal() + "\n");

					continue;
				}
			}
			run = false;
			dealerScore();
		}
	}

	static void checkScores() {
		System.out.println("Dealer has: \n" + dealer.showHand() + "\nTotal points: \n" + dealer.getTotal() + "\n");
		for (int i = 0; i < players.length; i++) {
			if (dealer.getTotal() > players[i].getTotal() && dealer.getTotal() < 22) {
				i++;
				System.out.println("Player " + i + " lose!\n");
				i--;
			} else if (dealer.getTotal() < players[i].getTotal() && players[i].getTotal() < 22) {
				i++;
				System.out.println("Player " + i + " win!\n");
				i--;
			} else if (dealer.getTotal() == players[i].getTotal() && players[i].getTotal() < 22) {
				i++;
				System.out.println("Player " + i + " ties!");
				i--;
			}
		}
	}

}

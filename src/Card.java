
public class Card {

	private Suit suit;
	private Rank rank;
	public boolean isFaceUp;

	// Constructor
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
		isFaceUp = false;
	}

	public String getSuit() {
		return suit.printSuit();
	}

	public int getRank() {
		return rank.getRank();
	}

	public String printRank() {
		// Get rank as String (for aces primarily)
		return rank.printRank();
	}

	public void flipCard() {
		isFaceUp = !isFaceUp;
	}

	public String toString() {
		String str = "";
		if (isFaceUp) {
			str += rank.printRank() + " of " + suit.printSuit();
		} else {
			str = "Face down";
		}
		return str;
	}
}

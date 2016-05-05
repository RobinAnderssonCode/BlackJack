import java.util.ArrayList;

public class Hand {

	protected ArrayList<Card> cards;

	// Constructor will initialize cards.
	public Hand() {
		cards = new ArrayList<Card>();
	}

	// Methods
	public void clear() {
		cards.clear();
	}

	public void add(Card card) {
		cards.add(card);
	}

	// Get total points of a hand
	public int getTotal() {
		boolean hasAce = false;
		int totalPts = 0;
		int aceNr = 0;
		for (int i = 0; i < cards.size(); i++) {
			totalPts += cards.get(i).getRank();
			if (cards.get(i).printRank() == "Ace") {
				hasAce = true;
				aceNr++;
			}

		}
		for (int i = 0; i < aceNr; i++) {
			if (hasAce && totalPts >= 22) {
				totalPts -= 10;
			}
		}
		return totalPts;
	}

	public String showHand() {
		String str = "";
		boolean allFaceUp = true;
		for (Card c : cards) {
			str += c.toString() + "\n";
			if (!c.isFaceUp) {
				allFaceUp = false;
			}
		}
		if (allFaceUp) {
			str += "Total points: " + getTotal() + "\n";
		}
		return str;
	}

	public void flipCard() {
		for (Card c : cards) {
			c.flipCard();
		}
	}

	public boolean give(Card card, Hand otherHand) {
		if (!cards.contains(card)) {
			return false;
		} else {
			cards.remove(card);
			otherHand.add(card);
			return true;
		}
	}
}

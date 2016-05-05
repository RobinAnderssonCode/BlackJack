import java.util.Random;

public class Deck extends Hand {
	Random rand = new Random();
	//Adds cards to the deck.
	public void makeCards() {
		for (Suit suit: Suit.values()) {
			for (Rank rank: Rank.values()) {
				Card card = new Card(rank, suit);
				// this syftar till ArrayList cards i Hand klassen
				this.add(card);
			}
		}
	}
	public void shuffle() {
		for (int i = cards.size() -1; i > 0; i--) {
			int pick = rand.nextInt(i);
			Card randCard = cards.get(pick);
			//last card in the loop
			Card lastCard = cards.get(i);
			cards.set(i, randCard);
			cards.set(pick, lastCard);
		}
	}
	// Ge kort till flera spelare.
	public void deal (Hand[] players, int perHand) {
		for (int i = 0; i < perHand; i++) {
			for (int j = 0; j < players.length; j++) {
				this.give(cards.get(0), players[j]);
			}
		}
	}
	// Ge kort till en spelare.
	public void deal(Hand hand, int perHand) {
		for (int i = 0; i < perHand; i++) {
			this.give(cards.get(0), hand);
		}
	}
	public void flipCard(Card c) {
		c.flipCard();
	}
	public void hit() {
		
	}
}

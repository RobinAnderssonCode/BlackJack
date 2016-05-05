
public enum Suit {
	HEARTS("Hearts"),
	SPADES("Spades"),
	DIAMONDS("Diamonds"),
	CLUBS("Clubs");
	
	private final String suitText;
	
	// Constructor
	private Suit(String suitText){
		this.suitText = suitText;
	}
	
	// Print suit
	public String printSuit() {
		return suitText;
	}
}

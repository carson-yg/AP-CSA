
public class Card implements Comparable<Card>{
	// need some instance variables
	private int suit;
  private int value;
  private String [] suits = {"Hearts", "Diamonds", "Spades", "Clubs"};
  private String [] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

	public Card(int suit, int value)
  {
		this.suit = suit;
    this.value = value;
	}
	
	@Override
	/** This method satisfies the Comparable interface which determines
	 * if this Object is smaller than, greater than or equal to the 
	 * specified Card c
	 * Formally, if this Card is smaller than c, a negative int is returned
	 *           if this Card is larger than c, a positive int is returned
	 *           if this Card is equal to c, zero is returned	*/
	public int compareTo(Card c)
  {
    if (this.value < c.value){
      return -1;
    } else if (this.value > c.value){
      return 1;
    }
		return 0;
	}
	
	// represents this Card in the following manner
	// if the card is the Ace of Spades, then 
	// it returns "Ace of Spades". 2 - 10 can be represented
	// as "2 of Hearts" or "Two of Hearts".  Your choice.
	@Override
	public String toString()
  {
		String s = values[value-1] + " of " + suits[suit-1];
		return s;
	}
}
import java.util.*;

public class Stack {
  // need to add some instance variables and constructor(s)
  private ArrayList <Card> myDeck;

  public Stack(){
    myDeck = new ArrayList<Card>();
  }

  // init initializes the cards if the Stack is the deck of cards

  public void init()
  {
    for (int suit = 1; suit <= 4; suit++){
      for(int value = 1; value <= 13; value++){
        myDeck.add(new Card(suit, value));
      }
    }
    shuffle();
  }
	// returns the number of Cards left in the Stack
	public int size()
  {
    int num = myDeck.size();
		return num;
	}

	/* Randomizes the cards in this deck. 
	* You must use the following algorithm:
	* If the size is < 2, return;
	* If the size == 2, "flip a coin", swap if "heads"
	* If the size > 2 repeat the following steps size() * 2 times:
	* select two different indexes (make sure they are different)
	* swap them in the ArrayList
	*/ 
	public void shuffle()
  {
    if(myDeck.size() < 2){
      return;
    } else if (myDeck.size() == 2){
      Random randomNum = new Random();
      int coin = randomNum.nextInt(2);
      if (coin == 1){
        Collections.swap(myDeck, 0, 1);
      }
    } else {
      for (int x = 0; x < myDeck.size() * 2; x++){
          int index1 = 0; 
          int index2 = 0;
          while (index1 == index2) {
            index1 = (int)(Math.random() * myDeck.size());
            index2 = (int)(Math.random() * myDeck.size());
          } 
          Collections.swap(myDeck, index1, index2);
      } 
    } 
	}

	/*
	 * Returns the "top" Card from this Stack, removing it from its  
	 *  data structure 
   */
	public Card deal()
  {
		Card c = null;
    if (myDeck.size() > 0){
      c = myDeck.remove(0); 
    } 
    return c;
	}

	/*
	 * Adds the Cards from newCards to the data structure holding Cards in this
	 * Pile.  The Cards from newCards should be added to the "bottom" of this Pile. 
   */
	public void add(List<Card> newCards)
  {
    myDeck.addAll(newCards);
	}

	public void add(Card c)
  {
		ArrayList<Card> list = new ArrayList<Card>();
		list.add(c);
		add(list);
	}
  
	/* This returns a String representation of this Pile.  It should return 
	 *  a String built out of the Cards that the pile contains
	 */

	@Override
	public String toString()
  {
		String s = "";
    for(Card aCard: myDeck)
    {
      s += aCard.toString() + "\n";
    }
		return s;
	}
}

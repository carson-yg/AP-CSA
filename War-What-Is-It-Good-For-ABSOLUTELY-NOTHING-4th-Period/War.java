import java.util.*;

/**
This class has the code for testing classes and for running a game of War.
*/
public class War {
  private static Scanner in = new Scanner(System.in);
	
  public static void main(String[] args)
  {
    System.out.println("1- Test Card class ");
    System.out.println("2- Test Deck class ");
    System.out.println("3- Run a WarGame ");
    System.out.println("Anything else to exit ");
    System.out.print("Enter your choice: ");
    String choice = in.nextLine();
    // trim white spaces from back and front
    choice = choice.trim();

    if(choice.equals("1"))
    {
      new War().testCard();
    }
    else if(choice.equals("2"))
    {
      new War().testDeck();
    }
    else if(choice.equals("3"))
    {
      new War().play();
    }
    else
    {
      System.exit(0);
    }
	}

  /**  You can add more tests into this method.  These are the basic tests it should pass*/
  private void testCard()
  {
		System.out.println("Testing the Card class");
		Card c1 = new Card(1,4);  // four of something
		Card c2 = new Card(3,8);  // 8 of something
		
		System.out.println("Card 1: " + c1);
		System.out.println("Card 2: " + c2);

		if(c1.compareTo(c2) < 0)
			System.out.println(c2 + " is bigger");
		else if(c1.compareTo(c2) > 0)
			System.out.println(c1 + " is bigger");
		else
			System.out.println(c1 + " equals " + c2);
	}

	private void testDeck()
  {
		System.out.println("Testing the constructed Deck Stack");
		Stack deck = new Stack();
    deck.init();
		System.out.println("Printing 4 cards");
		for(int i = 0; i<4; i++)
    {
			System.out.println((i+1) + "  " + deck.deal());
		}

		Stack hand1 = new Stack(), hand2 = new Stack();

		while(deck.size() > 10)
    {
			hand1.add(deck.deal());
		}

		while(deck.size() > 0)
    {
			hand2.add(deck.deal());
		}

    System.out.println("Hand1: " + hand1);
    System.out.println("Hand2: " + hand2);

		while(hand1.size() > 0 && hand2.size() > 0)
    {
			Card c1 = hand1.deal(), c2 = hand2.deal();
			if(c1.compareTo(c2) > 0)
				System.out.println(c1 + " is bigger than " + c2 + " so player 1 wins");
			else if(c1.compareTo(c2) < 0)
				System.out.println(c2 + " is bigger than " + c1 + " so player 2 wins");
			else
				System.out.println(c1 + " and " + c2 + " are the same, so tie");
		}

    System.out.println("Hand1: "+hand1);
    System.out.println("Hand2: "+hand2);
	}

  private void play(){
    // Starts a new Game of War 
      WarGame wg = new WarGame();
		  wg.playAGame();
  }
	
}

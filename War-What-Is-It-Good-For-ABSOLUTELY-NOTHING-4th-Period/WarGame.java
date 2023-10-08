import java.util.*;


public class WarGame  // plays a game of War with two players
{
  // probably need more instance variables
	private Stack deck;
	private Player one, two;
	private int numRounds = 0;
	private boolean gameOver = false;
	private Scanner in = new Scanner(System.in);
  private Card card1;
  private Card card2;
  private ArrayList <Card> temp;
  private int winner;

	public int playAGame()
  {
		deck = new Stack();
    deck.init();
		one = new Player();
		two = new Player();
    temp = new ArrayList <Card>();
		dealCardsToPlayers();
		while(!gameOver)
    {
			numRounds++;

			System.out.println("\nRounds Played: "+ numRounds + " Hit Enter to continue.");
			
			playRound();
			displayRound();
			checkGameOver();
			
			// uncomment the line below to stop after every round
			// in.nextLine();
		}

		System.out.println("Game over!");
		return numRounds;
	}

	// This deals the cards from the deck to the two players
	private void dealCardsToPlayers()
  {
    for(int i = 0; i < 26; i++)
    {
      one.addCard(deck.deal());
      two.addCard(deck.deal());
    }

	}


	/* Displays the result of the round.  This should show the cards that were
	 * played, as well as the winner of the round 
  */
	private void displayRound()
  {
    if(winner == 1){
      System.out.println("In round " + numRounds + " Player 1 won");
    } else if (winner == 2){
      System.out.println("In round " + numRounds + " Player 2 won");
    } else {
      System.out.println("In round " + numRounds + " The players were  tied");
    } 
    System.out.println("Player 1: " + card1 + " vs " + "Player 2: " + card2);

	}


	/*  This method takes a card from each player and compares the two cards.
	 *  Whichever player had the largest card puts the cards in their capturedCards stack.  
	 *  If there is a tie, the players who tied put one more card into the stack.
	 *  Whoever had the highest card wins the stack. If still a tie, repeat.  If one 
	 *  player runs out of cards, that player loses.
  */	
	private void playRound() {
    card1 = one.getCard();
    card2 = two.getCard();
    if (card1 != null && card2 != null){
      int num = card1.compareTo(card2);
      if (num > 0){
        winner = 1;
        one.addCard(card2);
        one.addCard(card1);
        for (Card c : temp){
          one.addCard(c);
        }
        temp.clear();
      } else if (num < 0){
        winner = 2;
        two.addCard(card2);
        two.addCard(card1);
        for (Card c : temp){
          two.addCard(c);
        }
        temp.clear();
      } else {
        winner = 0;
        temp.add(card1);
        temp.add(card2);
      }
    } else if (card1 == null) {
      winner = 2;
      two.addCard(card2);
    } else {
      winner = 1;
      one.addCard(card1);
    }

	}


	// Checks to see if game is over; if it is, it sets 
	// the variable gameOver to true and displays an appropriate message
	private void checkGameOver() {
    if (card1 == null || card2 == null){
      gameOver = true;
    }
	}

}

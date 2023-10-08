
public class Player {
	private Stack toPlay, capturedCards;
	
	public Player()
  {
		toPlay = new Stack();
		capturedCards = new Stack();
	}
	
	public Card getCard()
  {
		if(toPlay.size()<=0)
    {
			if(capturedCards.size()<=0)
      {
				return null;
      }
			else
      {
				// line below just randomizes the captured
				// cards so that we don't get cyclic results where
				// the game never ends
				capturedCards.shuffle();
				while(capturedCards.size()>0)
					toPlay.add(capturedCards.deal());
			}
		}
		return toPlay.deal();
	}
	public void addCard(Card c)
  {
		capturedCards.add(c);
	}
	
}

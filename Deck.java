

/* 					Deck Class					*/
import java.util.Random;


public class Deck 
{
	private int size;
	private Card[] Deck; 
	private int Top;
	private String suits = "HDSC";
	private String ranks = "A23456789TJQK";

	public Deck() 
	{
		Deck = new Card[52];
		Top = 0; 
		int count = 0;
		Card C1;
		for (int s = 0; s < suits.length(); s++) {
			for (int r = 0; r < ranks.length(); r++) {
				C1 = new Card(ranks.charAt(r), suits.charAt(s));
				Deck[count++] = C1;
			}
		}
		size = Deck.length;

	}

	public void display() 
	{	
		int index = 0;
		for (int i = 0; i < suits.length(); i++) {
			for (int y = 0; y < ranks.length(); y++) {
				Deck[index].display();
				if (Deck[index].getRank() == 'T') {
					System.out.print("  ");
				} else {
					System.out.print("   ");
				}
				index++;
			}
			System.out.println();
		}
	}
	
	public void shuffle() 
	{
		Random rand = new Random();
		int x, y;
		for (int i = 0; i < 1000; i++) 
		{
			x = rand.nextInt(52); // Total number of items.
			y = rand.nextInt(52);
			Card temp;
			temp = Deck[x];
			Deck[x] = Deck[y];
			Deck[y] = temp;
		}
		Top = 0;
	}
	
	public Card deal() 
	{
		return Deck[Top++];
	}
	public int remove() {
		size = size-4;
		System.out.println();
		return size;
	}
}






				
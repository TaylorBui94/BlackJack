

public class Player {
	
	private int playerMoney;
	private int size = 0; 
	Card[] myHand = new Card[52];
	
	public void drawCard(Card drawnCard){
		myHand[size] = drawnCard;
		size++;
	}
	
	public int getValue(){ 
		
		int value = 0;
		for (int i = 0; i<size; i++){
			value += myHand[i].getValue();
		}
		
		for (int i = 0; i<size; i++){
			if(myHand[i].getValue() == 11 && value > 21){
				value -= 10;
			}
		}	
		return value;
	}
	
	public void displayHand(){
		
	for(int i = 0; i < size; i++){
		myHand[i].display();
		System.out.print(" ");
	}
}
	
	public int win(int placedBet){
		playerMoney += placedBet;
		return playerMoney;
	}
	
	public int lose(int placedBet){
		playerMoney -= placedBet;
		return playerMoney;
	}
	
	public int playerCash(){
		return playerMoney;
	}
}

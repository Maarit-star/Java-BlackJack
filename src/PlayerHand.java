
import java.util.ArrayList;

public class PlayerHand {

    private ArrayList<Card> cards;
 
    public PlayerHand() {
        this.cards = new ArrayList<Card>();
    }

    public void takeCard(Card card) {
        this.cards.add(card);
    }

    public void printHand() {
        System.out.println("Cards in hand: " + cards);
    }

    
    public Card getCard(int i) {
        return this.cards.get(i);
    }

    public int sizeOfHand() {
        return this.cards.size();
    }

    public int getSum() {
        int sumOfHand = 0;
        int amountOfAces = 0;

        for (Card thisCard : this.cards) {
            switch (thisCard.getValue()) {
               
                case 2:
                    sumOfHand += 2;
                    break;
                case 3:
                    sumOfHand += 3;
                    break;
                case 4:
                    sumOfHand += 4;
                    break;
                case 5:
                    sumOfHand += 5;
                    break;
                case 6:
                    sumOfHand += 6;
                    break;
                case 7:
                    sumOfHand += 7;
                    break;
                case 8:
                    sumOfHand += 8;
                    break;
                case 9:
                    sumOfHand += 9;
                    break;
                case 10:
                    sumOfHand += 10;
                    break; 
                case 11:
                    sumOfHand += 10;
                    break;
                case 12:
                    sumOfHand += 10;
                    break;
                case 13:
                    sumOfHand += 10;
                    break;
                case 1:
                    amountOfAces += 1;
                    sumOfHand += 11;
                    break;
            }
        }
        for (int i = 0; i < amountOfAces; i++) {
            if (sumOfHand > 21) {
                sumOfHand -= 10;
            } 
        }
        return sumOfHand;
    }
    
    public boolean isItBlackJack() {
        getSum();
        if (getSum() == 21 && cards.size()==2) {
            return true;
        } else {
            return false;
        }
    }

    public void moveCardsToDeck(Deck toWhichMoveTheCards) {
        int thisDeckSize = this.cards.size();
        for (int i = 0; i < thisDeckSize; i++) {
            toWhichMoveTheCards.addCard(this.getCard(i));
        }
        // empty this deck
        for (int i = 0; i < thisDeckSize; i++) {
            this.cards.remove(0);
        }
    }
}

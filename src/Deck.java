
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Deck {

    private ArrayList<Card> cards;

    // poista
    private int numCards;

    public Deck(int thisDeck) {
        this.cards = new ArrayList<Card>(); 
        String heart = "Heart";
        String diamond = "Diamond";
        String spade = "Spade";
        String club = "Club";
        
        for(int i = 0; i < thisDeck; i++){
            for (int a = 1; a <= 13; a++) {
                this.cards.add(new Card(heart, a));
            }
            for (int b = 1; b <= 13; b++) {
                this.cards.add(new Card(diamond, b));
            }
            for (int c = 1; c <= 13; c++) {
                this.cards.add(new Card(spade, c));
            }
            for (int d = 1; d <= 13; d++) {
                this.cards.add(new Card(club, d));
            }
        }
    }
    
   
        public void shuffle() {
        Collections.shuffle(cards);
    }
   
    public String toString(){
        String cardsPrinted = "";
        for(Card acard : this.cards){
            cardsPrinted += "\n" + acard.toString();
        }
        return cardsPrinted;
    }
    
    // return the first card of the deck and removes it from the deck
    public Card dealCard(){
       return this.cards.remove(0);
    }   

 
    public void addCard(Card addCard){
        this.cards.add(addCard);
    }
 
}

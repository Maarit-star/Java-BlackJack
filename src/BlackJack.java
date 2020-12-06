import java.util.Scanner;
import java.util.ArrayList;

public class BlackJack {

    public static void main(String[] args) {

        // Welcome message
		// Rules
        System.out.println("Welcome to play BlackJack!");
        System.out.println("Rules: \n# If you get ace(1) during the round, the value for ace is 11. "
                + " \n# If you get more than one ace, value for those extra aces is always 1."
                + " \n# Value of cards between 2-10 is the same as the number of the card. Value of cards between 11-13 is 10. "
                + "\n# Your goal is to get 21 points. The player whose hand value is nearest to 21 wins."
                + " \n# If value of hand is more than 21 points, player loses."
                + "\n# BlackJack happens, where player's first two cards sum equals 21"
                + " -> the other card's value equals 10 (cards between 10-13) and the other card's value equals 11 (ace)");
        System.out.println("");

        // Create and shuffle the deck
        Deck deck = new Deck(2);
         
        //Let's print the deck to see if it works
        //System.out.println(deck);
        deck.shuffle();
        // Let's print the deck to see that it is shuffled
        //System.out.println(deck);

        // create player: pelaaja and deal two cards
        PlayerHand player = new PlayerHand();
        // In the beginning player has 100.00 to play
        double playerMoney = 100.00;

        Scanner reader = new Scanner(System.in);

        // Game loop
        // Game continues as long as player has money to play
        while (playerMoney > 0) {
            // how much player wants to bet
            System.out.println("You have " + playerMoney + ", how much would you like to bet?");
            double bet = reader.nextDouble();
            if (bet > playerMoney) {
                System.out.println("You can't bet more than you have money. Please leave.");
                break;
            }

            boolean endRound = false;
            
            // start of dealing the cards
            // player gets two cards
            player.takeCard(deck.dealCard());
            player.takeCard(deck.dealCard());

            while (true) {
                player.printHand();
           
                player.isItBlackJack();
                if(player.isItBlackJack()){
                   System.out.println("BLACKJACK! You win!");
                   // player gets twice the bet when blackjack
                   playerMoney += bet *2;
                   //round ends
                   endRound = true;
                   break;
                }
                System.out.println("Your hand's value: " + player.getSum());
               
               
                // If hand value is les than 21, ask if player wants more cards
                System.out.println("Do you want a new card? (1)Yes (2)No");
                int answer = reader.nextInt();

                if (answer == 1) {
                    player.takeCard(deck.dealCard());
                    System.out.println("Your new card is: " + player.getCard(player.sizeOfHand() - 1));
                    //if value is more than 21
                    if (player.getSum() > 21) {
                        System.out.println("You lose! Your hand is: " + player.getSum());
                        playerMoney -= bet;
                        endRound = true;
                        break;
                    }
                }
                // when player stops taking cards, round ends
                if (answer == 2) {
                    break;
                }
            }

            // Dealers turn. Create deck for dealer and add two cards to it
            PlayerHand dealer = new PlayerHand();
            dealer.takeCard(deck.dealCard());
            dealer.takeCard(deck.dealCard());
            System.out.println("DEALER:");
            dealer.printHand();
            // is the dealer hand bigger than player's 
            if ((dealer.getSum() > player.getSum()) && endRound == false) {
                System.out.println("dealer wins the round! You lose");
                playerMoney -= bet;
                endRound = true;
            }
            // Dealer takes a new card, if the value of dealer's hand is less than 15
            while ((dealer.getSum() < 15) && endRound == false) {
                dealer.takeCard(deck.dealCard());
                System.out.println("Dealer's new card: " + dealer.getCard(dealer.sizeOfHand() - 1));
            }
            // Dealer's hand value
            System.out.println("Dealer's hand value is: " + dealer.getSum());
            // did dealer lose
            if ((dealer.getSum() > 21) && endRound == false) {
                System.out.println("Dealer lose. You win!");
                playerMoney += bet;
                endRound = true;
            }
            // is it draw
            if ((player.getSum() == dealer.getSum()) && endRound == false) {
                System.out.println("Draw.");
                endRound = true;
            }
            if ((player.getSum() > dealer.getSum()) && endRound == false) {
                System.out.println("You win!");
                playerMoney += bet;
                endRound = true;
            } else if (endRound == false) {
                System.out.println("You lose this round.");
                playerMoney -= bet;
                endRound = true;
            }

            player.moveCardsToDeck(deck);
            dealer.moveCardsToDeck(deck);
            deck.shuffle();
            System.out.println("End of round.");
        }
        System.out.println("GAME OVER! You're out of money :(");
    }
}

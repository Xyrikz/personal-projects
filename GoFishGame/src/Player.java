/**
 * Author: Ryan Zou
 * Date: April 21, 2023
 */

import java.util.ArrayList;

public class Player {

    private String name;
    private GroupOfCards cardHand = new GroupOfCards();
    private GroupOfCards placedDown = new GroupOfCards();

    public Player(String name) {
        this.name = name;
    }

    public void takeFromDeck(CardDeck cardDeck) {
        cardHand.addToGroup(cardDeck.takeFromDeck());
    }

    public void initFromDeck(CardDeck cardDeck) {
        for (int i = 0; i < 20; i++) {
            cardHand.addToGroup(cardDeck.takeFromDeck());
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getCards() {
        ArrayList<Card> cards = new ArrayList<>();
        for (Card card : cardHand.getCardGroup()) {
            cards.add(card);
        }
        return cards;
    }

    public GroupOfCards getCardHand() {
        return this.cardHand;
    }

    public ArrayList<Card> getCardsDown() {
        return placedDown.getCardGroup();
    }

    public void placeDown() {
        for (Rank rank : Rank.values()) {
            if (cardHand.getNumRank(rank) == 4) {
                System.out.println("You have collected all cards of rank " + rank + ". They will be moved to your collected pile");
                for (Card card : this.getCards()) {
                    if (card.getRank() == rank) {
                        placedDown.addToGroup(card);
                        cardHand.removeFromGroup(card);
                    }
                }
            }
        }
    }
}


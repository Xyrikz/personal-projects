/**
 * Author: Ryan Zou
 * Date: April 21, 2023
 */

import java.util.ArrayList;
import java.util.Collections;

public class CardDeck {
    private static CardDeck onlyObject = null;

    private ArrayList<Card> cardDeck = new ArrayList<>();

    private CardDeck() {
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                Card c = new Card(r, s);
                cardDeck.add(c);
            }
        }
        Collections.shuffle(getCardDeck());
    }

    public ArrayList<Card> getCardDeck() {
        return cardDeck;
    }

    public Card takeFromDeck() {
        Card taken = cardDeck.get(getCardDeck().size() - 1);
        cardDeck.remove(getCardDeck().size() - 1);
        return taken;
    }

    public static CardDeck getInstance() {
        if (onlyObject == null) {
            onlyObject = new CardDeck();
        }
        return onlyObject;
    }
}

/**
 * Author: Ryan Zou
 * Date: April 21, 2023
 */

import java.util.ArrayList;

public class GroupOfCards {

    private ArrayList<Card> cardGroup = new ArrayList<>();

    public GroupOfCards() {}

    public ArrayList<Card> getCardGroup() {
        return this.cardGroup;
    }

    public void removeFromGroup(Card card) {
        cardGroup.remove(card);
    }

    public void addToGroup(Card card) {
        cardGroup.add(card);
    }

    public int getNumRank(Rank rank) {
        int i = 0;
        for (Card card : cardGroup) {
            if (card.getRank() == rank) {
                i++;
            }
        }
        return i;
    }

}

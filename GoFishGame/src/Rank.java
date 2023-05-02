/**
 * Author: Ryan Zou
 * Date: April 21, 2023
 */

public enum Rank {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;

    public static boolean contains(String rankString){
        if (rankString == null) {
            return false;
        }
        for (Rank rank : Rank.values()) {
            if (rankString.equals(rank.toString())) {
                return true;
            }
        }
        return false;
    }
}

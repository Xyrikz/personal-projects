/**
 * Author: Ryan Zou
 * Date: April 21, 2023
 */

public class RunGame {
    public static void main(String[] args) {
        Game game = new Game("Go Fish");
        System.out.println("Starting this " + game.getName() + " game...");
        game.start();
    }
}

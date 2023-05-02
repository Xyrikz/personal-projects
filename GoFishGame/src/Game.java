/**
 * Author: Ryan Zou
 * Date: April 21, 2023
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);

    private final String name;
    private ArrayList<Player> players = new ArrayList<>();
    private CardDeck cardDeck = null;
    private int fisher;
    private Player fishingPlayer;
    private Player chosenPlayer;
    private Rank chosenRank;


    public Game(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void addPlayer(Player player) {
        players.add(player);
    }

    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private boolean allCardsDown(ArrayList<Player> players) {
        int cardsDown = 0;
        for (Player player : players) {
            cardsDown += player.getCardsDown().size();
        }
        return (cardsDown == 52);
    }

    private void registerPlayers(int numOfPlayers) {
        for (int i = 1; i <= numOfPlayers; i++) {
            System.out.print("Enter player " + i + " name: ");
            Player player = new Player(scanner.nextLine());
            this.addPlayer(player);
        }
    }

    private boolean validatePlayerId(String playerId) {
        return isNumeric(playerId) && Integer.parseInt(playerId) < players.size();
    }

    private Player enterValidPlayerId() {
        do {
            System.out.println("Enter a valid player number from the above");
            String playerId = scanner.nextLine().trim();
            boolean isValidPlayer = validatePlayerId(playerId);
            if (isValidPlayer) {
                return players.get(Integer.parseInt(playerId));
            }
        } while (true);
    }

    private void displayFisherCards() {
        System.out.println("Your turn " + fishingPlayer.getName() + ". Here are the current cards in your hand:");
        for (Card card : fishingPlayer.getCards()) {
            System.out.println(card.getRank() + " " + card.getSuit());
        }
    }

    private void requestChosenPlayer() {
        System.out.println("Enter the number of the player you would like to pick from:");
        for (int i = 0; i < players.size(); i++) {
            if (i != fisher) {
                System.out.println(i + ". " + players.get(i).getName());
            }
        }
    }

    private int cardTransaction(int numContains) {
        for (Card card : chosenPlayer.getCards()) {
            if (card.getRank() == chosenRank) {
                fishingPlayer.getCardHand().addToGroup(card);
                chosenPlayer.getCardHand().removeFromGroup(card);
                numContains++;
            }
            fishingPlayer.placeDown();
            declareWinner();
        }
        return numContains;
    }

    private boolean continueTurn() {
        int numContains = 0;
        displayFisherCards();
        requestChosenPlayer();
        chosenPlayer = enterValidPlayerId();
        chosenRank = enterValidCardRank();
        if (cardTransaction(numContains) == 0) {
            return false;
        }
        System.out.println();
        return true;
    }

    private void resetFisher() {
        if (fisher >= this.players.size()) {
            fisher = 0;
        }
    }

    private void nextPlayerTurn() {
        System.out.println("The player does not have any cards of that rank. You will be given a card from the remaining " +
                "deck and your turn will end");
        fishingPlayer.placeDown();
        System.out.println();
        System.out.println();
        fisher++;
    }

    private boolean validateCardDeckNotEmpty() {
        return !(cardDeck.getCardDeck().size() == 0);
    }

    private boolean continueGame() {
        resetFisher();
        fishingPlayer = players.get(fisher);
        do {
//            works without body
        } while (continueTurn());
        if (validateCardDeckNotEmpty()) {
            fishingPlayer.takeFromDeck(cardDeck);
        }
        nextPlayerTurn();
        return this.allCardsDown(players);
    }

    private void play() {
        fisher = 0;
        do {
//            works without body
        } while (!continueGame());
    }

    private Rank enterValidCardRank() {
        do {
            System.out.println("Enter the rank of the card you would like to ask for out of the following:");
            for (Rank rank : Rank.values()) {
                System.out.print(rank + " ");
            }
            System.out.println();
            String requestedRank = scanner.nextLine().trim().toUpperCase();
            if (Rank.contains(requestedRank)) {
                return Rank.valueOf(requestedRank);
            }
        } while (true);
    }

    private boolean validateNumOfPlayers(String numOfPlayers) {
        return isNumeric(numOfPlayers) && Integer.parseInt(numOfPlayers) < 6 && Integer.parseInt(numOfPlayers) > 1;
    }

    private int enterValidNumOfPlayers() {
        do {
            System.out.print("Enter number of players between 2 and 5: ");
            String numOfPlayers = scanner.nextLine().trim();
            if (validateNumOfPlayers(numOfPlayers)) {
                return Integer.parseInt(numOfPlayers);
            }
        } while (true);
    }

    public void start() {
        registerPlayers(enterValidNumOfPlayers());

        this.cardDeck = CardDeck.getInstance();

        for (Player player : players) {
            player.initFromDeck(cardDeck);
        }

        play();

        declareWinner();
    }

    private Player determineWinner(ArrayList<Player> players) {
        Player winner = null;
        int highestPoints = 0;
        for (Player player : players) {
            if (player.getCardsDown().size() > highestPoints) {
                winner = player;
                highestPoints = player.getCardsDown().size();
            }
        }
        return winner;
    }

    private void declareWinner() {
        if (allCardsDown(players)) {
            Player winner = determineWinner(players);
            System.out.println();
            System.out.println("The winner of this " + name + " game is " + winner.getName() + " with " + winner.getCardsDown().size() + " cards collected");
            System.exit(0);
        }
    }

}

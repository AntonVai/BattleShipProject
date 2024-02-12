package example.game;

import example.player.Bot;
import example.player.Player;
import example.util.Logger;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Game {
    private Player player1;
    private Bot player2;


    public Game() {
        initializePlayers();
    }

    private void initializePlayers() {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

        System.out.println("Введите имя первого игрока: ");
        String player1Name = scanner.nextLine();
        player1 = new Player(player1Name);


        player2 = new Bot("Bot");
    }

    public void play() {
        Logger.startTime();
        Scanner scanner = new Scanner(System.in,StandardCharsets.UTF_8);

        System.out.println("Выберите режим размещения кораблей:");
        System.out.println("1. Ручное размещение");
        System.out.println("2. Автоматическое размещение");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> {
                player1.placeShipsManually(true);
                player2.placeShipsAutomatically();
            }
            case 2 -> {
                player1.placeShipsAutomatically();
                player2.placeShipsAutomatically();
            }
            default -> {
                System.out.println("Некорректный выбор. Используется автоматическое размещение.");
                player1.placeShipsAutomatically();
                player2.placeShipsAutomatically();
            }
        }

        while (true) {
            Logger.saveBoard(player1.getName(), player1.getPlayerBoard());
            Logger.saveBoard(player2.getName(), player2.getPlayerBoard());
            System.out.println("Игровое поле Игрока 2 под именем: " + player2.getName());
            player2.getPlayerBoard().displayBoard(false);

            player1.attack(player2);
            if (!player2.hasShipsRemaining()) {
                System.out.println(player1.getName() + " победил!");
                Logger.saveBoard(player1.getName() + " победил", player1.getPlayerBoard());
                Logger.saveBoard(player2.getName() + " проиграл", player2.getPlayerBoard());
                break;
            }
            player2.attack(player1);
            if (!player1.hasShipsRemaining()) {
                System.out.println(player2.getName() + " победил!");
                Logger.saveBoard(player2.getName() + " победил", player2.getPlayerBoard());
                Logger.saveBoard(player1.getName() + " проиграл", player1.getPlayerBoard());
                break;
            }
        }
        Logger.endTime();
    }
}



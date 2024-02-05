package example.game;

import example.Player.Player;

import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;

    public Game() {
        this.player1 = new Player("Игрок 1");
        this.player2 = new Player("Игрок 2");
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите режим размещения кораблей:");
        System.out.println("1. Ручное размещение");
        System.out.println("2. Автоматическое размещение");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> {
                player1.placeShipsManually(true);
                player2.placeShipsManually(false);
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
            System.out.println("Игровое поле Игрока 1:");
            player1.playerBoard.displayBoard(true);
            System.out.println("Игровое поле Игрока 2:");
            player2.playerBoard.displayBoard(false);
            player1.attack(player2);
            if (!player2.hasShipsRemaining()) {
                System.out.println("Игрок 1 победил!");
                break;
            }

            System.out.println("Игровое поле Игрока 2:");
            player2.playerBoard.displayBoard(true);
            player2.attack(player1);
            if (!player1.hasShipsRemaining()) {
                System.out.println("Игрок 2 победил!");
                break;
            }
        }
    }
}



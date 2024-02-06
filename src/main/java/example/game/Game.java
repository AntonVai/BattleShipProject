package example.game;

import example.player.Player;

import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;

    public Game() {
        initializePlayers();
    }

    private void initializePlayers() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите имя первого игрока: ");
        String player1Name = scanner.nextLine();
        player1 = new Player(player1Name);

        System.out.println("Введите имя второго игрока: ");
        String player2Name = scanner.nextLine();
        player2 = new Player(player2Name);
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
            System.out.println("Игровое поле Игрока 1 под именем: " + player1.getName());
            player1.getPlayerBoard().displayBoard(true);
            System.out.println("Игровое поле Игрока 2 под именем: " + player2.getName());
            player2.getPlayerBoard().displayBoard(false);
            player1.attack(player2);
            if (!player2.hasShipsRemaining()) {
                System.out.println(player1.getName() + " победил!");
                break;
            }
            System.out.println("Игровое поле Игрока 2 под именем: " + player2.getName());
            player2.getPlayerBoard().displayBoard(true);
            player2.attack(player1);
            if (!player1.hasShipsRemaining()) {
                System.out.println(player2.getName() + " победил!");
                break;
            }
        }
    }
}



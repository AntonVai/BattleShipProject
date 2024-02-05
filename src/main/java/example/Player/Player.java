package example.Player;

import example.Board.GameBoard;
import example.Ship.Ship;

import java.util.Scanner;

public class Player {
    private String name;
    public GameBoard playerBoard;

    public Player(String name) {
        this.name = name;
        this.playerBoard = new GameBoard();
    }


    public void placeShipsManually(boolean showShips) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(name + ", разместите свои корабли.");
        int[] shipSizes = {Ship.SIXSHIP, Ship.FIVESHIP, Ship.FIVESHIP, Ship.FOURSHIP, Ship.FOURSHIP, Ship.FOURSHIP, Ship.THREESHIP, Ship.THREESHIP,
                Ship.THREESHIP, Ship.THREESHIP, Ship.TWOSHIP, Ship.TWOSHIP, Ship.TWOSHIP, Ship.TWOSHIP, Ship.TWOSHIP,
                Ship.ONESHIP, Ship.ONESHIP, Ship.ONESHIP, Ship.ONESHIP, Ship.ONESHIP, Ship.ONESHIP};

        playerBoard.displayBoard(showShips);

        for (int size : shipSizes) {
            boolean placed = false;
            while (!placed) {
                System.out.println("Введите координаты для корабля размером " + size + " (начальная точка, например, A1): ");
                String input = scanner.next().toUpperCase();
                if (input.length() == 2) {
                    char startCol = input.charAt(0);
                    int startRow = Integer.parseInt(input.substring(1));
                    int startColIndex = startCol - 'A';
                    int startRowIndex = startRow - 1;

                    System.out.println("Выберите направление (H - горизонтальное, V - вертикальное): ");
                    char direction = scanner.next().toUpperCase().charAt(0);

                    if ((direction == 'H' || direction == 'V') && startRow >= 1 && startRow <= 16) {
                        placed = playerBoard.tryPlaceShip(startRowIndex, startColIndex, size, direction);
                        if (placed) {
                            playerBoard.displayBoard(showShips);
                        } else {
                            System.out.println("Невозможно разместить корабль с заданными параметрами. Попробуйте снова.");
                        }
                    } else {
                        System.out.println("Некорректное направление или координаты. Пожалуйста, попробуйте снова.");
                    }
                } else {
                    System.out.println("Некорректный ввод. Пожалуйста, попробуйте снова.");
                }
            }
        }
        System.out.println(name + ", корабли размещены!");
    }

    public void placeShipsAutomatically() {
        playerBoard.autoPlaceShips();
        System.out.println(name + ", корабли размещены автоматически!");
    }

    public void attack(Player opponent) {
        Scanner scanner = new Scanner(System.in);
        boolean hit;

        do {
            System.out.println(name + ", введите координаты для атаки (например, A1): ");
            String input = scanner.next().toUpperCase();

            if (input.length() >= 2) {
                char col = input.charAt(0);
                String rowStr = input.substring(1);

                // Проверяем, является ли введенное значение числом
                if (rowStr.matches("\\d+")) {
                    int row = Integer.parseInt(rowStr);

                    hit = opponent.playerBoard.checkHit(row - 1, col - 'A');
                    if (hit) {
                        // Проверяем, был ли корабль полностью уничтожен
                        if (opponent.playerBoard.isShipDestroyed(row - 1, col - 'A')) {
                            System.out.println("Корабль убит!");
                        } else {
                            System.out.println("Ранил");
                        }
                    } else {
                        System.out.println("Мимо");
                    }

                    System.out.println("Игровое поле противника:");
                    opponent.playerBoard.displayBoard(false);
                } else {
                    System.out.println("Некорректный ввод. Пожалуйста, введите координаты в формате, например, A1.");
                    hit = false;
                }
            } else {
                System.out.println("Некорректный ввод. Пожалуйста, введите координаты в формате, например, A1.");
                hit = false;
            }
        } while (hit);
    }

    public boolean hasShipsRemaining() {
        return playerBoard.hasShipsRemaining();
    }
}


package example.board;

import example.cell.Cell;
import example.player.Player;
import example.util.ShipInit;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;


public class GameBoard {


    private final Cell[][] board;
    private final int COUNT_SIZE = 16;


    public GameBoard() {
        this.board = new Cell[COUNT_SIZE][COUNT_SIZE];
        for (int i = 0; i < COUNT_SIZE; i++) {
            for (int j = 0; j < COUNT_SIZE; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    public void placeShipsManually(Player player, boolean showShips) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        System.out.println(player.getName() + ", разместите свои корабли.");
        int[] shipSizes = ShipInit.initShips();
        displayBoard(showShips);

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

                    if ((direction == 'H' || direction == 'V') && startRow >= 1 && startRow <= COUNT_SIZE) {
                        placed = tryPlaceShip(startRowIndex, startColIndex, size, direction);
                        if (placed) {
                            displayBoard(showShips);
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
        System.out.println(player.getName() + ", корабли размещены!");
    }


    public void autoPlaceShips() {
        Random random = new Random();
        int[] shipSizes = ShipInit.initShips();
        for (int size : shipSizes) {
            boolean placed = false;
            while (!placed) {
                int x = random.nextInt(COUNT_SIZE);
                int y = random.nextInt(COUNT_SIZE);
                char direction = (random.nextBoolean()) ? 'H' : 'V';
                placed = tryPlaceShip(x, y, size, direction);
            }
        }
    }

    public boolean tryPlaceShip(int x, int y, int size, char direction) {
        if (x >= 0 && x < COUNT_SIZE && y >= 0 && y < COUNT_SIZE) {
            // Проверяем наличие других кораблей вокруг места установки нового корабля
            for (int i = x - 1; i <= x + size; i++) {
                for (int j = y - 1; j <= y + size; j++) {
                    if (i >= 0 && i < COUNT_SIZE && j >= 0 && j < COUNT_SIZE) {
                        if (!"пустая".equals(board[i][j].getState())) {
                            return false;
                        }
                    }
                }
            }

            if (direction == 'H' && y + size <= COUNT_SIZE) {
                for (int i = 0; i < size; i++) {
                    board[x][y + i].setState("корабль");
                }
                return true;
            } else if (direction == 'V' && x + size <= COUNT_SIZE) {
                for (int i = 0; i < size; i++) {
                    board[x + i][y].setState("корабль");
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    public boolean checkHit(int row, int col) {
        Cell targetCell = board[row][col];
        String currentState = targetCell.getState();

        if (currentState.equals("пустая")) {
            targetCell.setState("мимо");
            return false; // Промах
        } else if (currentState.equals("корабль")) {
            targetCell.setState("ранил");
            return true; // Попадание
        } else {
            return false; // Промах, если ячейка уже была ранее атакована
        }
    }


    public void displayBoard(boolean showShips) {
        System.out.print("    ");
        for (char c = 'A'; c <= 'P'; c++) {
            System.out.print(c + "  ");
        }
        System.out.println();

        for (int i = 0; i < COUNT_SIZE; i++) {
            System.out.printf("%2d ", (i + 1));
            for (int j = 0; j < COUNT_SIZE; j++) {
                System.out.print(" " + getCellSymbol(i, j, showShips) + " ");
            }
            System.out.println();
        }
    }

    public String getCellSymbol(int x, int y, boolean showShips) {
        String state = board[x][y].getState();
        return switch (state) {
            case "пустая" -> "-";
            case "корабль" -> showShips ? "■" : "-";
            case "ранил" -> "X";
            default -> ".";
        };
    }

    public boolean hasShipsRemaining() {
        for (int i = 0; i < COUNT_SIZE; i++) {
            for (int j = 0; j < COUNT_SIZE; j++) {
                if ("корабль".equals(board[i][j].getState())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isShipDestroyed(int row, int col) {
        // Проверяем, остались ли другие клетки корабля вокруг
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < COUNT_SIZE && j >= 0 && j < COUNT_SIZE) {
                    if (!(i == row && j == col) && board[i][j].getState().equals("корабль")) {
                        return false;
                    }
                }
            }
        }
        return true; // Если других клеток корабля не осталось, считаем его убитым
    }
}


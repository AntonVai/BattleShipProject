package example.Board;

import example.Ship.Ship;
import example.cell.Cell;

import java.util.Random;

public class GameBoard {

    private final Cell[][] board;


    public GameBoard() {
        this.board = new Cell[16][16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                board[i][j] = new Cell();
            }
        }
    }



    public void autoPlaceShips() {
        Random random = new Random();
        int[] shipSizes = {Ship.SIXSHIP, Ship.FIVESHIP, Ship.FIVESHIP, Ship.FOURSHIP, Ship.FOURSHIP, Ship.FOURSHIP, Ship.THREESHIP, Ship.THREESHIP,
                Ship.THREESHIP, Ship.THREESHIP, Ship.TWOSHIP, Ship.TWOSHIP, Ship.TWOSHIP, Ship.TWOSHIP, Ship.TWOSHIP,
                Ship.ONESHIP, Ship.ONESHIP, Ship.ONESHIP, Ship.ONESHIP, Ship.ONESHIP, Ship.ONESHIP};

        for (int size : shipSizes) {
            boolean placed = false;
            while (!placed) {
                int x = random.nextInt(16);
                int y = random.nextInt(16);
                char direction = (random.nextBoolean()) ? 'H' : 'V';
                placed = tryPlaceShip(x, y, size, direction);
            }
        }
    }
    public boolean tryPlaceShip(int x, int y, int size, char direction) {
        if (x >= 0 && x < 16 && y >= 0 && y < 16) {
            // Проверяем наличие других кораблей вокруг места установки нового корабля
            for (int i = x - 1; i <= x + size; i++) {
                for (int j = y - 1; j <= y + size; j++) {
                    if (i >= 0 && i < 16 && j >= 0 && j < 16) {
                        if (!"пустая".equals(board[i][j].getState())) {
                            return false;
                        }
                    }
                }
            }

            if (direction == 'H' && y + size <= 16) {
                for (int i = 0; i < size; i++) {
                    board[x][y + i].setState("корабль");
                }
                return true;
            } else if (direction == 'V' && x + size <= 16) {
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

        for (int i = 0; i < 16; i++) {
            System.out.printf("%2d ", (i + 1));
            for (int j = 0; j < 16; j++) {
                System.out.print(" " + getCellSymbol(i, j, showShips) + " ");
            }
            System.out.println();
        }
    }

    private String getCellSymbol(int x, int y, boolean showShips) {
        String state = board[x][y].getState();
        if (!showShips && "корабль".equals(state)) {
            return "-";
        } else if ("пустая".equals(state)) {
            return "-";
        } else if ("корабль".equals(state)) {
            return "1";
        } else if ("ранил".equals(state)) {
            return "X";
        } else {
            return ".";
        }
    }

    public boolean hasShipsRemaining() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
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
                if (i >= 0 && i < 16 && j >= 0 && j < 16) {
                    if (!(i == row && j == col) && board[i][j].getState().equals("корабль")) {
                        return false;
                    }
                }
            }
        }
        return true; // Если других клеток корабля не осталось, считаем его убитым
    }

}


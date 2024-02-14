package example.model;


import example.util.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Bot extends Player {
    private final List<String> botCords = new ArrayList<>();
    private int lastHitRow = -1;
    private int lastHitCol = -1;
    private boolean searchingMode = true; // Режим поиска корабля

    public Bot(String name) {
        super(name);
    }

    @Override
    public void attack(Player opponent) {
        Random random = new Random();
        boolean hit = true;

        while (hit) {
            int row;
            int col;

            if (searchingMode) {
                row = random.nextInt(16);
                col = random.nextInt(16);
            } else {
                // Режим атаки вокруг последнего попадания
                Optional<int[]> nextCoords = smartAttack();
                if (nextCoords.isEmpty()) {
                    searchingMode = true;
                    continue;
                }
                row = nextCoords.get()[0];
                col = nextCoords.get()[1];
            }

            char colChar = (char) ('A' + col);
            String cord = colChar + Integer.toString(row + 1);

            if (!botCords.contains(cord)) {
                botCords.add(cord);
                Logger.shootLog(getName(), cord);
                System.out.println("Бот стрелял по координатам: " + colChar + (row + 1));
                hit = opponent.getPlayerBoard().checkHit(row, col);

                if (hit) {
                    if (opponent.getPlayerBoard().isShipDestroyed(row, col)) {
                        System.out.println("Корабль уничтожен!");
                        searchingMode = true; // Возвращаемся в режим поиска
                    } else {
                        System.out.println("Ранил");
                        lastHitRow = row;
                        lastHitCol = col;
                        searchingMode = false; // Продолжаем атаку вокруг попадания
                    }
                } else {
                    System.out.println("Мимо");
                }
            }
        }
    }

    private Optional<int[]> smartAttack() {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Возможные направления: вверх, вниз, влево, вправо
        Random random = new Random();

        // Выбираем случайное направление из списка
        int[] direction = directions[random.nextInt(directions.length)];
        int row = lastHitRow + direction[0];
        int col = lastHitCol + direction[1];

        // Проверяем, чтобы координаты находились в пределах поля
        if (row < 0 || row >= 16 || col < 0 || col >= 16 || botCords.contains(row + " " + col)) {
            return Optional.empty();
        }
        return Optional.of(new int[]{row, col});
    }
}


package example.model;


import example.util.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot extends Player {
    private final List<String> botCords = new ArrayList<>();


    public Bot(String name) {
        super(name);
    }

    @Override
    public void attack(Player opponent) {
        Random random = new Random();
        boolean hit = true;
        while (hit) {
            int row = random.nextInt(16);
            int col = random.nextInt(16);
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
                    } else {
                        System.out.println("Ранил");
                    }
                } else {
                    System.out.println("Мимо");
                }
            }
        }
    }
}


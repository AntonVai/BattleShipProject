package example.model;

import example.Commands.PlayerCommand;
import example.Commands.Show;
import example.board.GameBoard;
import example.util.Logger;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Player {
    private final Map<String, PlayerCommand> commandMap = new HashMap<>();
    private final String name;
    private final GameBoard playerBoard;

    public Player(String name) {
        this.name = name;
        this.playerBoard = new GameBoard();
        regCom();
    }


    public void placeShipsManually(boolean showShips) {
        playerBoard.placeShipsManually(this, showShips);
    }

    public void placeShipsAutomatically() {
        playerBoard.autoPlaceShips();
        System.out.println(name + ", корабли размещены автоматически!");
    }

    public void attack(Player opponent) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        boolean hit = true;
        while (hit) {
            System.out.println(name + ", введите координаты для атаки (например, A1): ");
            String input = scanner.next().toUpperCase();

            if(input.equalsIgnoreCase("!Show")){
                System.out.println("Ваше поле");
                startCommand("!Show");
                continue;
            }


            if (input.length() >= 2) {
                char col = input.charAt(0);
                String rowStr = input.substring(1);

                if (rowStr.matches("\\d+")) {
                    int row = Integer.parseInt(rowStr);
                    Logger.shootLog(this.getName(), col + rowStr);
                    hit = opponent.playerBoard.checkHit(row - 1, col - 'A');
                    if (hit) {
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
        }
    }
    private void regCom(){
        commandMap.put("!Show", new Show());

    }
    private void startCommand(String name){
        PlayerCommand command = commandMap.get(name);
        if(command != null){
            command.command(this);
        }else {
            System.out.println("Данной команды нет в списке.");
        }
    }

    public boolean hasShipsRemaining() {
        return playerBoard.hasShipsRemaining();
    }

    public String getName() {
        return name;
    }

    public GameBoard getPlayerBoard() {
        return playerBoard;
    }
}


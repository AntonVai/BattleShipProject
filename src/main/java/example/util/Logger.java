package example.util;

import example.board.GameBoard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Logger {
    private Logger() {
    }

    private static final String FILE_PATH = "log.txt";

    public static void startTime() {
        writer("Начало игры");
    }

    public static void endTime() {
        writer("Конец игры");
    }

    public static void shootLog(String name, String cord) {
        writer("Игрок под именем: " + name + " стрелял в следующие коордианты: " + cord);
    }

    private static void writer(String str) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, StandardCharsets.UTF_8, true))) {
            LocalDateTime currentTime = LocalDateTime.now();
            String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write(str + ": " + formattedTime + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveBoard(String playerName, GameBoard gameBoard) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, StandardCharsets.UTF_8, true))) {
            writer.write("Состояние поля игрока " + playerName + ":\n");
            writer.write("   ");
            for (char c = 'A'; c <= 'P'; c++) {
                writer.write(c + " ");
            }
            writer.write("\n");
            for (int i = 0; i < 16; i++) {
                if (i < 9) {
                    writer.write(" ");
                }
                writer.write((i + 1) + " ");
                for (int j = 0; j < 16; j++) {
                    writer.write(gameBoard.getCellSymbol(i, j, true) + " ");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

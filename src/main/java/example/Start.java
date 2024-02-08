package example;

import example.game.Game;

public class Start {
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        Game game = new Game();
        game.play();
    }
}

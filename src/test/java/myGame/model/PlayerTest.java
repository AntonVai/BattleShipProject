package myGame.model;

import myGame.board.GameBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player;
    Bot bot;
    GameBoard gameBoard;

    @BeforeEach
    void setUp() {
        player = new Player("player1");
        bot = new Bot("bot");
        gameBoard = new GameBoard();
        bot.getPlayerBoard().tryPlaceShip(5, 5, 1, 'H');
    }

    @Test
    void attack() {
        InputStream inputStream = new ByteArrayInputStream("F6\nF6\n".getBytes());
        System.setIn(inputStream);
        player.attack(bot);

        assertFalse(bot.getPlayerBoard().hasShipsRemaining());

    }
}
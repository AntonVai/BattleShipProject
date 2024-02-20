package myGame.model;

import myGame.board.GameBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BotTest {
    Player player;
    Bot bot;
    GameBoard gameBoard;

    @BeforeEach
    void setUp() {
        player = new Player("player1");
        bot = new Bot("bot");
        gameBoard = new GameBoard();
        player.getPlayerBoard().tryPlaceShip(5, 5, 3, 'H');
        player.getPlayerBoard().displayBoard(true);
    }

    @Test
    void attack() {
        bot.setSearchingMode(false);
        bot.setLastHitRow(5);
        bot.setLastHitCol(5);

        bot.attack(player);
        assertTrue(player.getPlayerBoard().checkHit(5, 5) ||
                player.getPlayerBoard().checkHit(6, 5) ||
                player.getPlayerBoard().checkHit(5, 4) ||
                player.getPlayerBoard().checkHit(4, 5) ||
                player.getPlayerBoard().checkHit(5, 6) ||
                player.getPlayerBoard().checkHit(6, 6) ||
                player.getPlayerBoard().checkHit(6, 4) ||
                player.getPlayerBoard().checkHit(4, 4));
    }

}


// f6 g6 h 6
// f5 g5 h5 i6
// f7 g7 h7 e6
package example.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {
    private GameBoard gameBoard;

    @BeforeEach
    void setUp() {
        gameBoard = new GameBoard();
    }

    @Test
    void placeShipsManually() {
        //TODO
    }

    @Test
    void autoPlaceShips() {
        //TODO
    }

    @Test
    void tryPlaceShip() {
        assertTrue(gameBoard.tryPlaceShip(5,5,5,'H'));
        assertTrue(gameBoard.tryPlaceShip(10,10,3,'V'));
        assertFalse(gameBoard.tryPlaceShip(6,6,100,'V'));
    }

    @Test
    void checkHit() {
        gameBoard.tryPlaceShip(5,5,2,'H');
        assertTrue(gameBoard.checkHit(5,5));
        assertEquals("X",gameBoard.getCellSymbol(5,5,true));
        assertFalse(gameBoard.checkHit(5,7));
        assertEquals(".",gameBoard.getCellSymbol(5,7,true));
        assertFalse(gameBoard.isShipDestroyed(5,5));
        assertTrue(gameBoard.checkHit(5,6));
        assertTrue(gameBoard.isShipDestroyed(5,5));
    }

    @Test
    void getCellSymbol() {
       gameBoard.tryPlaceShip(5,5,5,'H');
       assertEquals("■",gameBoard.getCellSymbol(5,5,true));
       assertEquals("-",gameBoard.getCellSymbol(5,5,false));
    }

    @Test
    void hasShipsRemaining() {
        gameBoard.tryPlaceShip(5,5,2,'H');
        gameBoard.tryPlaceShip(10,10,2,'H');

        gameBoard.checkHit(5,5);
        gameBoard.checkHit(5,6);

        assertTrue(gameBoard.hasShipsRemaining());

        gameBoard.checkHit(10,10);
        gameBoard.checkHit(10,11);

        assertFalse(gameBoard.hasShipsRemaining());
    }

    @Test
    void isShipDestroyed() {
        gameBoard.tryPlaceShip(5,5,2,'H');
        gameBoard.checkHit(6,6);
        assertFalse(gameBoard.isShipDestroyed(5,5));
        gameBoard.checkHit(5,5);
        gameBoard.checkHit(5,6);
        assertTrue(gameBoard.isShipDestroyed(5,5));
    }
}
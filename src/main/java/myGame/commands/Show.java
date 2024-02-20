package myGame.commands;

import myGame.model.Player;
public class Show implements PlayerCommand{
    @Override
    public void command(Player player) {
        player.getPlayerBoard().displayBoard(true);
    }
}
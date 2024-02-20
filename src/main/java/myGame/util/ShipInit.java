package myGame.util;

import myGame.model.ShipTypes;

public final class ShipInit {
    private ShipInit() {
    }

    public static int[] initShips() {
        return new int[]{ShipTypes.SIX_SHIP.getHP(), ShipTypes.FIVE_SHIP.getHP(), ShipTypes.FIVE_SHIP.getHP(), ShipTypes.FOUR_SHIP.getHP(),
                ShipTypes.FOUR_SHIP.getHP(), ShipTypes.FOUR_SHIP.getHP(), ShipTypes.THREE_SHIP.getHP(), ShipTypes.THREE_SHIP.getHP(),
                ShipTypes.THREE_SHIP.getHP(), ShipTypes.THREE_SHIP.getHP(), ShipTypes.TWO_SHIP.getHP(), ShipTypes.TWO_SHIP.getHP(), ShipTypes.TWO_SHIP.getHP(),
                ShipTypes.TWO_SHIP.getHP(), ShipTypes.TWO_SHIP.getHP(), ShipTypes.ONE_SHIP.getHP(), ShipTypes.ONE_SHIP.getHP(),
                ShipTypes.ONE_SHIP.getHP(), ShipTypes.ONE_SHIP.getHP(), ShipTypes.ONE_SHIP.getHP(), ShipTypes.ONE_SHIP.getHP()};
    }
}

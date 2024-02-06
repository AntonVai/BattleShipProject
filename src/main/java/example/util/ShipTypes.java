package example.util;

public enum ShipTypes {
    SIX_SHIP(6),
    FIVE_SHIP(5),
    FOUR_SHIP(4),
    THREE_SHIP(3),
    TWO_SHIP(2),
    ONE_SHIP(1);
    private final int HP;

    ShipTypes(int HP) {
        this.HP = HP;
    }

    public int getHP() {
        return HP;
    }
}

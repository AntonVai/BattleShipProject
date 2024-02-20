package myGame.cell;

public class Cell {
    private CellStatus state;

    public Cell() {
        this.state = CellStatus.EMPTY;
    }

    public CellStatus getState() {
        return state;
    }

    public void setState(CellStatus state) {
        this.state = state;
    }
}

package example.cell;

public class Cell {
    private String state; // пустое место в нашем двумерном ммассиве,где потом будут корабли, попадания и промахи

    public Cell() {
        this.state = "пустая";
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

package turingMachine.enums;

public enum Direction {
    LEFT(-1),
    RIGHT(1),
    STAY(0);

    private int indexDiff;

    private Direction(int indexDiff) {
        this.indexDiff = indexDiff;
    }

    public int getIndexDiff() {
        return indexDiff;
    }
}

package turingMachine;

class Transition {

    private int stateToGo;
    private char signToWrite;
    private Direction direction;

    Transition(int stateToGo, char signToWrite, Direction direction) {
        this.stateToGo = stateToGo;
        this.signToWrite = signToWrite;
        this.direction = direction;
    }

    int getStateToGo() {
        return stateToGo;
    }

    char getSignToWrite() {
        return signToWrite;
    }

    Direction getDirection() {
        return direction;
    }
}

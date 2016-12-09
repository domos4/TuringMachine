package turingMachine.transition;

import turingMachine.enums.Direction;
import turingMachine.state.State;

public class Transition {

    private State stateToGo;
    private char signToWrite;
    private Direction direction;

    public Transition(State stateToGo, char signToWrite, Direction direction) {
        this.stateToGo = stateToGo;
        this.signToWrite = signToWrite;
        this.direction = direction;
    }

    public State getStateToGo() {
        return stateToGo;
    }

    public char getSignToWrite() {
        return signToWrite;
    }

    public Direction getDirection() {
        return direction;
    }
}

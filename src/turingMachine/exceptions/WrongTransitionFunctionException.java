package turingMachine.exceptions;

import turingMachine.state.State;

public class WrongTransitionFunctionException extends Exception {

    private State state;
    private char actualCharacter;

    public WrongTransitionFunctionException(State state, char actualCharacter) {
        this.state = state;
        this.actualCharacter = actualCharacter;
    }

    @Override
    public String getMessage() {
        return "Transition function does not contain a transition for char [" + actualCharacter
                + "] in state [" + state + "].";
    }
}

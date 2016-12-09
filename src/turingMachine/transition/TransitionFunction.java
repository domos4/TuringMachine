package turingMachine.transition;

import java.util.Map;
import turingMachine.state.State;

public class TransitionFunction {

    private Map<State, Map<Character, Transition>> container;

    TransitionFunction(Map<State, Map<Character, Transition>> container) {
        this.container = container;
    }

    public Transition get(State state, Character character) {
        if (state == null || character == null) {
            return null;
        }
        Map<Character, Transition> transitionsForState = container.get(state);
        if (transitionsForState == null) {
            return null;
        }
        return transitionsForState.get(character);
    }

}

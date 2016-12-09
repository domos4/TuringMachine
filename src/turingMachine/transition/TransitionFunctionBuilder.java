package turingMachine.transition;

import java.util.HashMap;
import java.util.Map;
import turingMachine.state.State;

public class TransitionFunctionBuilder {

    private Map<State, Map<Character, Transition>> container;

    public TransitionFunctionBuilder() {
        this.container = new HashMap<>();
    }

    public TransitionFunctionStateRowBuilder manageRow(State state) {
        Map<Character, Transition> stateRow = container.get(state);
        if (stateRow == null) {
            stateRow = new HashMap<>();
            container.put(state, stateRow);
        }
        return new TransitionFunctionStateRowBuilder(stateRow);
    }

    public TransitionFunction toTransitionFunction() {
        return new TransitionFunction(container);
    }

    public class TransitionFunctionStateRowBuilder {

        private Map<Character, Transition> stateRow;

        private TransitionFunctionStateRowBuilder(Map<Character, Transition> stateRow) {
            this.stateRow = stateRow;
        }

        public TransitionFunctionStateRowBuilder add(Character character, Transition transition) {
            stateRow.put(character, transition);
            return this;
        }
    }

}

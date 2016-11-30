package turingMachine;

import turingMachine.tape.Tape;
import java.util.ArrayList;
import java.util.Map;
import turingMachine.tape.LinkedListTape;

public class TuringMachine {

    public static final char EMPTY_CHAR = 'B';
    public static final int END_STATE = -1;

    private Tape tape;
    private int state;
    private ArrayList<Map<Character, Transition>> transitionFunction;

    public TuringMachine(ArrayList<Map<Character, Transition>> transitionFunction) {
        this.transitionFunction = transitionFunction;
    }

    public void execute(String word) throws WrongTransitionFunctionException {
        this.tape = new LinkedListTape(word);
        this.state = 0;
        while (state != END_STATE) {
            Transition transition = this.transitionFunction.get(state).get(tape.read());
            if (transition == null) {
                throw new WrongTransitionFunctionException();
            }
            this.tape.write(transition.getSignToWrite());
            this.tape.move(transition.getDirection());
            this.state = transition.getStateToGo();
        }
    }

    public String printTape() {
        return this.tape.print();
    }
}

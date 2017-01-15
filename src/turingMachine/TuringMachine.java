package turingMachine;

import turingMachine.observer.TuringMachineObserver;
import turingMachine.exceptions.WrongTransitionFunctionException;
import turingMachine.transition.Transition;
import turingMachine.tape.Tape;
import java.util.Set;
import turingMachine.state.State;
import turingMachine.tape.ArrayListTape;
import turingMachine.transition.TransitionFunction;

public class TuringMachine {

    public static final char EMPTY_CHAR = 'B';

    private Tape tape;
    private State state;
    private TransitionFunction transitionFunction;
    private State beginState;
    private Set<State> acceptStates;

    private TuringMachineObserver observer = null;

    public TuringMachine(TransitionFunction transitionFunction, State beginState, Set<State> acceptStates) {
        this.transitionFunction = transitionFunction;
        this.beginState = beginState;
        this.acceptStates = acceptStates;
    }

    public void execute(String word) throws WrongTransitionFunctionException {
        this.tape = new ArrayListTape(word);
        this.state = beginState;
        if (observer != null) {
            observer.afterStart(tape, state);
        }
        while (!acceptStates.contains(state)) {
            move();
            if (observer != null) {
                observer.afterMove(tape, state);
            }
        }
    }

    private void move() throws WrongTransitionFunctionException {
        Transition transition = this.transitionFunction.get(state, tape.read());
        if (transition == null) {
            throw new WrongTransitionFunctionException(state, tape.read());
        }
        this.tape.write(transition.getSignToWrite());
        this.tape.move(transition.getDirection());
        this.state = transition.getStateToGo();
    }

    public String printTape() {
        return this.tape.print();
    }

    public void attachObserver(TuringMachineObserver observer) {
        this.observer = observer;
    }
}

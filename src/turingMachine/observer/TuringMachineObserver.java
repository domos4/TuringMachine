package turingMachine.observer;

import turingMachine.state.State;
import turingMachine.tape.Tape;

public interface TuringMachineObserver {

    public abstract void afterStart(Tape tape, State state);

    public abstract void afterMove(Tape tape, State state);

}

package turingMachine.observer;

import turingMachine.state.State;
import turingMachine.tape.Tape;

public class ConsoleOutTuringMachineObserver implements TuringMachineObserver {

    private int waitAfterMove = 0;

    public ConsoleOutTuringMachineObserver() {
    }

    public ConsoleOutTuringMachineObserver(int waitAfterMove) {
        this.waitAfterMove = waitAfterMove;
    }

    @Override
    public void afterStart(Tape tape, State state) {
        printOnConsole(tape, state);
    }

    @Override
    public void afterMove(Tape tape, State state) {
        printOnConsole(tape, state);
        if (waitAfterMove > 0) {
            try {
                Thread.sleep(waitAfterMove);
            } catch (InterruptedException ex) {
                System.out.println("INTERRUPTED!");
            }
        }
    }

    private void printOnConsole(Tape tape, State state) {
        System.out.println(tape.printWithCurrentPosition());
        System.out.println(state);
    }

}


import java.util.Collections;
import turingMachine.enums.Direction;
import turingMachine.transition.Transition;
import turingMachine.TuringMachine;
import turingMachine.exceptions.WrongTransitionFunctionException;
import turingMachine.state.State;
import turingMachine.transition.TransitionFunctionBuilder;
import utilities.UnaryParser;

public class Main {

    public static void main(String[] args) throws WrongTransitionFunctionException {
        TransitionFunctionBuilder tfb = new TransitionFunctionBuilder();
        State states[] = {new State("q0"), new State("q1"), new State("q2"), new State("F")};

        tfb.manageRow(states[0])
                .add('1', new Transition(states[1], 'x', Direction.LEFT))
                .add('x', new Transition(states[0], 'x', Direction.RIGHT))
                .add('B', new Transition(states[2], 'B', Direction.LEFT));

        tfb.manageRow(states[1])
                .add('x', new Transition(states[1], 'x', Direction.LEFT))
                .add('B', new Transition(states[0], 'x', Direction.RIGHT));

        tfb.manageRow(states[2])
                .add('x', new Transition(states[2], '1', Direction.LEFT))
                .add('B', new Transition(states[3], 'B', Direction.RIGHT));

        TuringMachine turingMachineFor2n = new TuringMachine(tfb.toTransitionFunction(), states[0],
                Collections.singleton(states[3]));
        long startTime = System.currentTimeMillis();
        turingMachineFor2n.execute(UnaryParser.parseToUnary(2548));
        long executeTime = System.currentTimeMillis() - startTime;
        System.out.println("Turing machine executed in " + executeTime + "ms.");
        System.out.println("RESULT: " + UnaryParser.parseToDecimal(turingMachineFor2n.printTape()));
    }
}

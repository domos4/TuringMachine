
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import turingMachine.enums.Direction;
import turingMachine.transition.Transition;
import turingMachine.TuringMachine;
import turingMachine.exceptions.WrongTransitionFunctionException;
import utilities.UnaryParser;

public class Main {

    public static void main(String[] args) throws WrongTransitionFunctionException {
        ArrayList<Map<Character, Transition>> transitionFunction = new ArrayList<>();

        transitionFunction.add(0, new HashMap<>());
        transitionFunction.get(0).put('1', new Transition(1, 'x', Direction.LEFT));
        transitionFunction.get(0).put('x', new Transition(0, 'x', Direction.RIGHT));
        transitionFunction.get(0).put('B', new Transition(2, 'B', Direction.LEFT));

        transitionFunction.add(1, new HashMap<>());
        transitionFunction.get(1).put('x', new Transition(1, 'x', Direction.LEFT));
        transitionFunction.get(1).put('B', new Transition(0, 'x', Direction.RIGHT));

        transitionFunction.add(2, new HashMap<>());
        transitionFunction.get(2).put('x', new Transition(2, '1', Direction.LEFT));
        transitionFunction.get(2).put('B', new Transition(-1, 'B', Direction.RIGHT));

        TuringMachine turingMachineFor2n = new TuringMachine(transitionFunction);
        long startTime = System.currentTimeMillis();
        turingMachineFor2n.execute(UnaryParser.parseToUnary(2548));
        long executeTime = System.currentTimeMillis() - startTime;
        System.out.println("Turing machine executed in " + executeTime + "ms.");
        System.out.println("RESULT: " + UnaryParser.parseToDecimal(turingMachineFor2n.printTape()));
    }
}

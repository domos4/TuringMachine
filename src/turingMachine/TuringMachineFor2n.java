package turingMachine;

import utilities.UnaryParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TuringMachineFor2n extends TuringMachine {

    public TuringMachineFor2n() {
        super(prepareTransitionFunction());
    }

    public String getResult() {
        return this.tape.toString();
    }

    public int function2n(int n) {
        String word = UnaryParser.parseToUnary(n);
        try {
            this.execute(word);
        } catch (WrongTransitionFunctionException e) {
            e.printStackTrace();
        }
        String tape = this.tape.stream().map(e -> e.toString()).reduce((acc, e) -> acc + e).get();
        return UnaryParser.parseToDecimal(tape);
    }


    private static ArrayList<Map<Character, Transition>> prepareTransitionFunction() {
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

        return transitionFunction;
    }
}

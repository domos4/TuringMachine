package turingMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

abstract class TuringMachine {
    
    public static final char EMPTY_CHAR = 'B';

    List<Character> tape;
    private MachineHead machineHead;
    private ArrayList<Map<Character, Transition>> transitionFunction;

    TuringMachine(ArrayList<Map<Character, Transition>> transitionFunction) {
        this.transitionFunction = transitionFunction;
    }

    void execute(String word) throws WrongTransitionFunctionException {
        this.tape = word.chars().mapToObj(c->(char) c).collect(Collectors.toList());
        this.machineHead = new MachineHead(tape, 0, 0);
        while(machineHead.getState() != -1) {
//            System.out.println(String.valueOf(tape));
            machineHead.move(this.transitionFunction.get(machineHead.getState()).get(machineHead.getCharacter()));
        }
    }
}


import java.util.Collections;
import turingMachine.enums.Direction;
import turingMachine.transition.Transition;
import turingMachine.TuringMachine;
import turingMachine.exceptions.WrongTransitionFunctionException;
import turingMachine.observer.ConsoleOutTuringMachineObserver;
import turingMachine.state.State;
import turingMachine.transition.TransitionFunctionBuilder;
import utilities.UnaryParser;

public class Main {

    public static void main(String[] args) throws WrongTransitionFunctionException, InterruptedException {
        int n = 3;
        int m = 3;
        if (args.length > 1) {
            n = Integer.parseInt(args[0]);
            m = Integer.parseInt(args[1]);
        }
        TuringMachine multiplyingTuringMachine = turingMachineForMultiplyingTwoNaturalNumbers();
        multiplyingTuringMachine.attachObserver(new ConsoleOutTuringMachineObserver(100));
        long startTime = System.currentTimeMillis();
        multiplyingTuringMachine.execute(UnaryParser.parseToUnary(n) + "#" + UnaryParser.parseToUnary(m));
        long executeTime = System.currentTimeMillis() - startTime;
        System.out.println("Turing machine executed in " + executeTime + "ms.");
        System.out.println("RESULT: " + UnaryParser.parseToDecimal(multiplyingTuringMachine.printTape()));
    }

    private static TuringMachine turingMachineForMultiplyingTwoNaturalNumbers() {
        TransitionFunctionBuilder tfb = new TransitionFunctionBuilder();
        State states[] = {new State("q0", "czy pierwszy argument to zero?"),
            new State("q1", "przygotowanie drugiego argumentu do kolejnych obliczeń"),
            new State("q2", "wyszukanie kolejnej 1 w pierwszym argumencie"),
            new State("q3", "przejście na koniec drugiego argumentu"),
            new State("q4", "wyszukanie kolejnego elementu do skopiowania"),
            new State("q5", "kopiowanie znalezionego elementu na koniec wyniku"),
            new State("q6", "nie znaleziono, przejście na koniec drugiego argumentu"),
            new State("q7", "czyszczenie miejsca na wynik"),
            new State("q8", "wyszukanie kolejnego elementu wyniku do przeniesienia na początek taśmy / przejście na koniec"),
            new State("q9", "wyszukanie miejsca na początku taśmy do wpisania elementu"),
            new State("q10", "wpisanie przenoszonego elementu"),
            new State("q11", "sprzątanie"),
            new State("q12", "przejście za pierwszy argument"),
            new State("qF", "koniec :)")};
        tfb.manageRow(states[0])
                .add('1', new Transition(states[12], '1', Direction.RIGHT))
                .add('#', new Transition(states[8], 'C', Direction.RIGHT));
        tfb.manageRow(states[1])
                .add('1', new Transition(states[1], 'X', Direction.RIGHT))
                .add('#', new Transition(states[2], '#', Direction.LEFT))
                .add('B', new Transition(states[1], '!', Direction.LEFT))
                .add('X', new Transition(states[1], 'X', Direction.LEFT))
                .add('C', new Transition(states[1], 'X', Direction.LEFT));
        tfb.manageRow(states[2])
                .add('1', new Transition(states[3], 'C', Direction.RIGHT))
                .add('B', new Transition(states[7], 'B', Direction.RIGHT))
                .add('C', new Transition(states[2], 'C', Direction.LEFT));
        tfb.manageRow(states[3])
                .add('#', new Transition(states[3], '#', Direction.RIGHT))
                .add('X', new Transition(states[3], 'X', Direction.RIGHT))
                .add('C', new Transition(states[3], 'C', Direction.RIGHT))
                .add('!', new Transition(states[4], '!', Direction.LEFT))
                .add('Z', new Transition(states[3], 'Z', Direction.LEFT));
        tfb.manageRow(states[4])
                .add('#', new Transition(states[6], '#', Direction.RIGHT))
                .add('X', new Transition(states[5], 'C', Direction.RIGHT))
                .add('C', new Transition(states[4], 'C', Direction.LEFT));
        tfb.manageRow(states[5])
                .add('B', new Transition(states[3], 'Z', Direction.LEFT))
                .add('C', new Transition(states[5], 'C', Direction.RIGHT))
                .add('!', new Transition(states[5], '!', Direction.RIGHT))
                .add('Z', new Transition(states[5], 'Z', Direction.RIGHT));
        tfb.manageRow(states[6])
                .add('C', new Transition(states[6], 'C', Direction.RIGHT))
                .add('!', new Transition(states[1], '!', Direction.LEFT));
        tfb.manageRow(states[7])
                .add('1', new Transition(states[7], 'C', Direction.RIGHT))
                .add('#', new Transition(states[7], 'C', Direction.RIGHT))
                .add('X', new Transition(states[7], 'C', Direction.RIGHT))
                .add('C', new Transition(states[7], 'C', Direction.RIGHT))
                .add('!', new Transition(states[8], 'C', Direction.RIGHT));
        tfb.manageRow(states[8])
                .add('1', new Transition(states[8], 'C', Direction.RIGHT))
                .add('B', new Transition(states[11], 'B', Direction.LEFT))
                .add('C', new Transition(states[8], 'C', Direction.RIGHT))
                .add('Z', new Transition(states[9], 'C', Direction.LEFT));
        tfb.manageRow(states[9])
                .add('1', new Transition(states[10], '1', Direction.RIGHT))
                .add('B', new Transition(states[10], 'B', Direction.RIGHT))
                .add('C', new Transition(states[9], 'C', Direction.LEFT));
        tfb.manageRow(states[10])
                .add('C', new Transition(states[8], '1', Direction.RIGHT));
        tfb.manageRow(states[11])
                .add('1', new Transition(states[11], '1', Direction.LEFT))
                .add('B', new Transition(states[13], 'B', Direction.RIGHT))
                .add('C', new Transition(states[11], 'B', Direction.LEFT));
        tfb.manageRow(states[12])
                .add('1', new Transition(states[12], '1', Direction.RIGHT))
                .add('#', new Transition(states[1], '#', Direction.RIGHT));
        return new TuringMachine(tfb.toTransitionFunction(), states[0],
                Collections.singleton(states[13]));
    }

}

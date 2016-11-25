import turingMachine.TuringMachineFor2n;
import turingMachine.WrongTransitionFunctionException;

public class Main {

    public static void main(String[] args) throws WrongTransitionFunctionException {
        TuringMachineFor2n turingMachine1 = new TuringMachineFor2n();
        System.out.println(turingMachine1.function2n(2548));
    }
}

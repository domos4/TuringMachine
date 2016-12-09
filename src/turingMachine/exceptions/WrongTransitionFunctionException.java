package turingMachine.exceptions;

public class WrongTransitionFunctionException extends Exception {

    @Override
    public String getMessage() {
        return "turingMachine.Transition function contains empty fields.";
    }
}

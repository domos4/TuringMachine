package turingMachine.tape;

import java.util.Stack;
import java.util.stream.Collectors;
import turingMachine.Direction;
import static turingMachine.TuringMachine.EMPTY_CHAR;

/**
 *
 * @author jk
 */
public class StackTape implements Tape {

    private Stack<Character> left;
    private Stack<Character> right;
    private char current;

    public StackTape(String beginState) {
        this.left = new Stack<>();
        this.right = new Stack<>();
        char[] chars = beginState.toCharArray();
        this.current = chars[0];
        for (int i = chars.length - 1; i > 0; i--) {
            right.add(chars[i]);
        }
    }

    @Override
    public char read() {
        return this.current;
    }

    @Override
    public void write(char character) {
        this.current = character;
    }

    @Override
    public void move(Direction direction) {
        switch (direction) {
            case LEFT:
                move(right, left);
                break;
            case RIGHT:
                move(left, right);
                break;
        }
    }

    private void move(Stack<Character> from, Stack<Character> to) {
        if (!from.empty() || current != EMPTY_CHAR) {
            from.add(current);
        }
        if (to.empty()) {
            current = EMPTY_CHAR;
        } else {
            current = to.pop();
        }
    }

    @Override
    public String print() {
        String out = left.stream().map(c -> String.valueOf(c)).collect(Collectors.joining());
        Stack<Character> tempStack = (Stack<Character>) right.clone();
        out += current;
        while (!tempStack.empty()) {
            out += tempStack.pop();
        }
        return out;
    }

}

package turingMachine;

import java.util.Stack;
import static turingMachine.TuringMachine.EMPTY_CHAR;

/**
 *
 * @author jk
 */
public class Tape {

    private Stack<Character> left;
    private Stack<Character> right;
    private char current;

    public Tape(String beginState) {
        char[] chars = beginState.toCharArray();
        this.current = chars[0];
        for (int i = chars.length; i > 0; i--) {
            right.add(chars[i]);
        }
    }

    public char read() {
        return this.current;
    }

    public void moveLeft() {
        move(right, left);
    }

    public void moveRight() {
        move(left, right);
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

}

package turingMachine.tape;

import java.util.ArrayList;
import java.util.stream.Collectors;
import turingMachine.enums.Direction;
import static turingMachine.TuringMachine.EMPTY_CHAR;

/**
 *
 * @author jk
 */
public class ArrayListTape implements Tape {

    private ArrayList<Character> container;
    private int idx;

    public ArrayListTape(String word) {
        this.container = new ArrayList<>(word.length());
        this.idx = 0;
        word.chars().forEachOrdered(c -> container.add(Character.valueOf((char) c)));
    }

    @Override
    public char read() {
        return this.container.get(this.idx);
    }

    @Override
    public void write(char character) {
        this.container.set(idx, character);
    }

    @Override
    public void move(Direction direction) {
        this.idx += direction.getIndexDiff();
        if (idx == 1 && container.get(0) == EMPTY_CHAR) {
            container.remove(0);
            idx--;
        } else if (idx == container.size() - 2 && container.get(container.size() - 1) == EMPTY_CHAR) {
            container.remove(container.size() - 1);
        } else if (idx == container.size()) {
            container.add(EMPTY_CHAR);
        } else if (idx < 0) {
            container.add(0, EMPTY_CHAR);
            idx++;
        }
    }

    @Override
    public String print() {
        return container.stream().map(c -> c.toString()).collect(Collectors.joining());
    }

}

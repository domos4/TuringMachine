package turingMachine.tape;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.stream.Collectors;
import turingMachine.Direction;
import static turingMachine.TuringMachine.EMPTY_CHAR;

/**
 *
 * @author jk
 */
public class LinkedListTape implements Tape {

    private LinkedList<Character> container;
    private ListIterator<Character> it;
    private Direction lastMove;
    private char current;

    public LinkedListTape(String word) {
        this.container = new LinkedList<>();
        word.chars().forEachOrdered(c -> container.add(Character.valueOf((char) c)));
        this.it = container.listIterator();
        this.current = it.next();
        this.lastMove = Direction.RIGHT;
    }

    @Override
    public char read() {
        return current;
    }

    @Override
    public void write(char character) {
        this.current = character;
        it.set(current);
    }

    @Override
    public void move(Direction direction) {
        if (Direction.STAY.equals(direction)) {
            return;
        }
        switch (direction) {
            case LEFT:
                if (!it.hasNext() && current == EMPTY_CHAR) {
                    it.remove();
                } else if (!lastMove.equals(direction)) {
                    it.previous();
                }
                if (!it.hasPrevious()) {
                    it.add(EMPTY_CHAR);
                }
                current = it.previous();
                break;
            case RIGHT:
                if (!it.hasPrevious() && current == EMPTY_CHAR) {
                    it.remove();
                } else if (!lastMove.equals(direction)) {
                    it.next();
                }
                if (!it.hasNext()) {
                    it.add(EMPTY_CHAR);
                    it.previous();
                }
                current = it.next();
                break;
        }
        lastMove = direction;
    }

    @Override
    public String print() {
        return container.stream().map(c -> c.toString()).collect(Collectors.joining());
    }

}

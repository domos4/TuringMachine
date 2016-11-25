package turingMachine;

import java.util.List;

class MachineHead {

    private List<Character> tape;
    private int index;
    private int state;

    MachineHead(List<Character> tape, int index, int state) {
        this.tape = tape;
        this.index = index;
        this.state = state;
    }

    char getCharacter() {
        return isHeadOnTape() ? this.tape.get(this.index) : 'B';
    }

    int getState() {
        return state;
    }

    private boolean isHeadOnTape() {
        return index >= 0 && index < tape.size();
    }

    void move(Transition transition) throws WrongTransitionFunctionException {
        if (transition == null) {
            throw new WrongTransitionFunctionException();
        }
        if (isHeadOnTape())
            this.tape.set(this.index, transition.getSignToWrite());
        else if (transition.getSignToWrite() != 'B') {
            if (this.index == -1) {
                this.index = 0;
            }
            this.tape.add(this.index, transition.getSignToWrite());
        }
        this.state = transition.getStateToGo();
        this.index += transition.getDirection().getIndexDiff();
    }
}

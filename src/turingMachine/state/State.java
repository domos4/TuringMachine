package turingMachine.state;

import java.util.Objects;

public class State {

    private String name;
    private String description;

    public State(String name) {
        this.name = name;
        this.description = null;
    }

    public State(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "State{" + "name=" + name + ", description=" + description + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final State other = (State) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

}

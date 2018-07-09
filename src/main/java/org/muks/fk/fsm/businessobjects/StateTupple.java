package org.muks.fk.fsm.businessobjects;

import org.muks.fk.fsm.metadata.States;

public class StateTupple {
    public String previous = null;
    public String current = null;

    public StateTupple() {}
    public StateTupple(String prev, String curr) {
        this.previous = prev;
        this.current = curr;
    }

    public String toString() {
        return "{PreviousState: " + this.previous + ", CurrentState: " + this.current + "}";
    }
}

package org.muks.fsm.businessobjects;

import org.muks.fsm.metadata.States;

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

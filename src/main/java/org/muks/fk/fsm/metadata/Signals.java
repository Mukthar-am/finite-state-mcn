package org.muks.fk.fsm.metadata;

import java.util.HashMap;
import java.util.Map;

public class Signals {

    Map<String, String> SIGNALS = new HashMap();

    public void add(String signal, String state) {
        this.SIGNALS.put(signal.toLowerCase(), state.toLowerCase());
    }

    public String getState(String signal) {
        return this.SIGNALS.get(signal);
    }

    public String toString() {
        return this.SIGNALS.toString();
    }
}

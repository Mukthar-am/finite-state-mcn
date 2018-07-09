package org.muks.fk.fsm.metadata;

public enum Event {
    FULLFILL_ORDER("CREATED"),
    ACTIVATE("ACTIVATED"),
    CANCEL("CANCELLED"),
    MAKE_PROGRESS("INPROGRESS"),
    COMPLETE("COMPLETED");

    private final String name;

    private Event(String s) {
        name = s;
    }

    public static String getStateByEvent(Event input) {
        String val = null;
        for (Event str : values()) {
            if (str.equals(input)) {
                val = input.name;
                break;
            }
        }
        return val;
    }
}

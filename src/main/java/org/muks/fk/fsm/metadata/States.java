package org.muks.fk.fsm.metadata;

public enum States {
    FULLFILL_ORDER("CREATED"),
    ACTIVATE("ACTIVATED"),
    INPROGRESS("INPROGRESS"),
    CANCELLED("CANCELLED"),
    COMPLETED("COMPLETED");

    private final String name;

    private States(String s) {
        name = s;
    }

    public static String valueByName(String input) {
        String val = null;
        for (States str : values()) {
            if (str.equals(input)) {
                val = input;
                break;
            }
        }
        return val;
    }

}

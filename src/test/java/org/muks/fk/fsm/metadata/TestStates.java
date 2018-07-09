package org.muks.fk.fsm.metadata;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestStates {

    @Test
    public void TestStateCalls() {
        States state = States.FULLFILL_ORDER;

        if (state.equals( States.FULLFILL_ORDER) && state.toString().equalsIgnoreCase("FULLFILL_ORDER")) {
            System.out.println("state: "+ state.toString());

        }

        Assert.assertEquals(state.toString(), "FULLFILL_ORDER");
    }
}

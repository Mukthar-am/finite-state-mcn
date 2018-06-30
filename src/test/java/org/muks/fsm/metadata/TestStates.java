package org.muks.fsm.metadata;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestStates {

    @Test
    public void TestStateCalls() {
        States state = States.FULLFILL_ORDER;

        if (state.equals( States.FULLFILL_ORDER) && state.toString().equalsIgnoreCase("created")) {
            System.out.println("state: "+ state.toString());

        }

        Assert.assertEquals(state.toString(), "created");
    }
}

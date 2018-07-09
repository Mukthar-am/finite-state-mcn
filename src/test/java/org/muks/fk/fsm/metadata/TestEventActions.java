package org.muks.fk.fsm.metadata;


import org.muks.fk.fsm.businessobjects.Packet;
import org.muks.fk.fsm.statemachine.Fsm;
import org.muks.fk.fsmdrivers.observers.OrderCancellationObserver;
import org.muks.fk.fsmdrivers.observers.OrderFullfilmentObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TestEventActions {
    private Logger LOG = LoggerFactory.getLogger(TestEventActions.class);

    @Test
    public void TestEvent() {
        Event fullfill = Event.FULLFILL_ORDER;

        Assert.assertEquals("CREATED", Event.getStateByEvent(fullfill));
    }


    @Test
    public void TestAddSignals() {
        //log.info("blkasjdflkasdjflskd");


        /**
         * FULLFILL_ORDER("CREATED"),
         *     ACTIVATE("ACTIVATED"),
         *     CANCEL("CANCELLED"),
         *     MAKE_PROGRESS("INPROGRESS"),
         *     COMPLETE("COMPLETED");
         */

        OrderFullfilmentObserver orderFullfilmentObserver = new OrderFullfilmentObserver();
        OrderCancellationObserver cancellationObserver = new OrderCancellationObserver();


        String stateDefinitionsFilePath = "/Users/mukthara/Data/git/personal/fsm/configs/states.properties";

        Fsm fsm =
                Fsm.getInstance()
                        .init(stateDefinitionsFilePath);

        fsm.subscriberOptIn(orderFullfilmentObserver);
        fsm.subscriberOptIn(cancellationObserver);

        int packet_id_incr = 1;
        final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Packet packet =
                new Packet()
                        .newPacket(packet_id_incr,
                                DATE_TIME_FORMAT.format(new Date()),
                                this.getClass().getSimpleName(),
                                this.getClass().getSimpleName() + "-" + packet_id_incr);


        LOG.info("New packet formed: " + packet.toString());

        try {
            fsm.listen("fullfill_order", packet);
            fsm.listen("cancel", packet.getId());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

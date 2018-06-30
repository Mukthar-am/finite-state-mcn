package org.muks.fsmdrivers.observers;

import org.muks.fsm.businessobjects.Packet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderFullfilmentObserver implements Subscriber {
    private Logger LOG = LoggerFactory.getLogger(OrderFullfilmentObserver.class);

    @Override
    public void stateChange(Packet packet) {
        LOG.info("State broadcast - \"Created\" order with packet ID={}", packet.getId());
    }
}

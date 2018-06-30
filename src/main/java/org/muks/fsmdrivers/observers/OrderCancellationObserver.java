package org.muks.fsmdrivers.observers;

import org.muks.fsm.businessobjects.Packet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderCancellationObserver implements Subscriber {
    private Logger LOG = LoggerFactory.getLogger(OrderFullfilmentObserver.class);

    @Override
    public void stateChange(Packet packet) {
        LOG.info("State broadcast - \"Cancelled\" packet ID={}", packet.getId());
    }
}

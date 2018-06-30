package org.muks.fsmdrivers.observers;

import org.muks.fsm.businessobjects.Packet;

public interface Subscriber {
    void stateChange(Packet packet);
}

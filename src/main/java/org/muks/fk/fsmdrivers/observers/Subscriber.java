package org.muks.fk.fsmdrivers.observers;

import org.muks.fk.fsm.businessobjects.Packet;

public interface Subscriber {
    void stateChange(Packet packet);
}

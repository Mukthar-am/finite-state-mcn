package org.muks.fk.fsm.statemachine;

import org.muks.fk.fsm.businessobjects.Packet;
import org.muks.fk.fsm.metadata.Event;

public interface FsmListener {
    void listen(String event, Packet packet);
}

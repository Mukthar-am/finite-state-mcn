package org.muks.fsm.statemachine;

import org.muks.fsm.businessobjects.Packet;
import org.muks.fsm.metadata.Event;

public interface FsmListener {
    void listen(String event, Packet packet);
}

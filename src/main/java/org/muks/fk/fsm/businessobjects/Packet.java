package org.muks.fk.fsm.businessobjects;

import org.muks.fk.fsm.metadata.States;

public class Packet {
    /**
     * Retaining all to be public for testing purpose and the implementation time is less avoid setters and getters
     */
    int PACKET_ID;
    String CREATED_ON;
    String CREATED_BY;

    String MODIFIED_ON;
    String MODIFIED_BY;

    String CUSTOMER_NAME;

    String PREVIOUS_STATE = null;
    String CURRENT_STATE = null;


    public Packet newPacket(int packetId,
                         String createdOn,
                         String createdBy,
                         String customerName) {

        this.PACKET_ID = packetId;
        this.CREATED_ON = createdOn;
        this.CREATED_BY = createdBy;
        this.CUSTOMER_NAME = customerName;

        return this;
    }

    public int getId() {
        return this.PACKET_ID;
    }

    public void setState(String statePrevious, String stateCurrent) {
        this.PREVIOUS_STATE = statePrevious;
        this.CURRENT_STATE = stateCurrent;
    }

    public StateTupple getStateInfo() {
        return new StateTupple(this.PREVIOUS_STATE, this.CURRENT_STATE);
    }


    public String toString() {
        return "[Packet: " + this.PACKET_ID +
                ", Created_on: " + this.CREATED_ON +
                ", Created_By: " + this.CREATED_BY +
                ", Modified_on: " + this.MODIFIED_ON +
                ", Modified_by: " + this.MODIFIED_BY +
                ", StateInfo: " + getStateInfo().toString() +
                ", Customer: " + this.CUSTOMER_NAME + "]";
    }



}

package org.muks.fk.fsm.statemachine;

import org.muks.fk.fsm.businessobjects.Packet;
import org.muks.fk.fsm.businessobjects.StateTupple;
import org.muks.fk.fsm.metadata.Signals;
import org.muks.fk.fsmdrivers.observers.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;



public class Fsm implements FsmListener {
    private Logger LOG = LoggerFactory.getLogger(Fsm.class);
    private List<Subscriber> subscribers = new ArrayList<>();

    /** thread safe */
    ConcurrentHashMap<Integer, Packet> PACKETS = new ConcurrentHashMap<>();

    private static Fsm instance = null;

    Signals signals = new Signals();

    private Fsm() {};

    public static Fsm getInstance() {
        if (instance == null)
            instance = new Fsm();

        return instance;
    }


    /**
     * Init state and signals from a given input properties file rather than hardcoding it.
     * @param stateDefinitions
     */
    public Fsm init(String stateDefinitions) {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream(stateDefinitions);

            // load a properties file
            prop.load(input);
            Set<Object> keys = prop.keySet();
            for (Object key : keys) {
                signals.add(key.toString(), prop.getProperty(key.toString()));
            }

        } catch (IOException ex) {
            LOG.error("Exception", ex);

        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOG.error("Exception", e);
                }
            }
        }

        LOG.info(signals.toString());

        return this;
    }


    /**
     * Listen and set
     * @param event - which is a signal
     * @param packet     - Packet object of the input message
     */
    @Override
    public void listen(String event, Packet packet) {
        String fsmState = signals.getState(event.toLowerCase());

        final Object lock = new Object();
        synchronized (lock) {
            StateTupple stateTupple = new StateTupple();
            if (this.PACKETS.containsKey(packet.getId()))
                stateTupple = getStateInfo(packet);

            LOG.info("");
            LOG.info("Setting => From: {}, To: {}", stateTupple.current, fsmState);
            packet.setState(stateTupple.current, fsmState);

            this.PACKETS.put(packet.getId(), packet);

            /** broadcast state change */
            broadcastStateChage(packet);
        }

        LOG.info("Packet being added ({}) => {}", event, packet.toString());


    }

    /**
     * Listen and set
     * @param event - which is a signal
     * @param packetId     - Packet ID of the input packet
     */
    public void listen(String event, int packetId) throws Exception {
        String fsmState = signals.getState(event.toLowerCase());

        final Object lock = new Object();
        synchronized (lock) {
            if (this.PACKETS.containsKey(packetId)) {
                Packet packet = this.PACKETS.get(packetId); /** Retrive packet */
                StateTupple stateTupple = getStateInfo(packet); /** obtain both the states */

                LOG.info("");
                LOG.info("Setting => From: {}, To: {}", stateTupple.current, fsmState);
                packet.setState(stateTupple.current, fsmState); /** set the previous and current state */

                LOG.info("Packet being added ({}) => {}", event, packet.toString());

                /** broadcast state change */
                broadcastStateChage(packet);
            } else {
                throw new Exception("Fsm cannot work on the given packet: ");
            }

        }

    }


    /**
     * Obtain state tupple of the packet to move from one state to another
     * @param packet - input packet object
     * @return  - state tupple object
     */
    private StateTupple getStateInfo(Packet packet) {
        return this.PACKETS.get(packet.getId()).getStateInfo();
    }


    /**
     * Registered subscriber to notify.
     */
    public void subscriberOptIn(Subscriber subscriber) {
        this.subscribers.add(subscriber);
    }


    /**
     * Registered subscriber to notify.
     */
    public void subscriberOptOut(Subscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    private void broadcastStateChage(Packet packet) {
        for (Subscriber subscriber : subscribers) {
            subscriber.stateChange(packet);
        }
    }


}

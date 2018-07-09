package org.muks.fk.fsmdrivers;

import org.muks.fk.fsm.businessobjects.Packet;
import org.muks.fk.fsm.metadata.Event;
import org.muks.fk.fsm.statemachine.Fsm;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OMSThread implements Runnable {
    private final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private boolean KEEP_RUNNING;
    private final String SYS_NAME = "OMSThread";

    private Fsm fsm = Fsm.getInstance();

    @Override
    public void run() {

        while (this.KEEP_RUNNING) {
            int packet_id_incr = 1;


//            Packet packet =
//                    new Packet()
//                            .create(packet_id_incr,
//                                    DATE_TIME_FORMAT.format(new Date()),
//                                    this.SYS_NAME,
//                                    "SYS_NAME" + packet_id_incr);
//
//            System.out.println(packet.toString());
//
//            Event event = Event.FULLFILL_ORDER;
//            //fsm.listen(event, packet);

        }
    }

    public void start() {
        this.KEEP_RUNNING = true;
    }

    public void stop() {
        this.KEEP_RUNNING = false;
    }
}

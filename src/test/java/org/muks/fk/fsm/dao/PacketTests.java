package org.muks.fk.fsm.dao;

import org.muks.fk.fsm.businessobjects.Packet;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PacketTests {
    SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    @Test
    public void CreatePacketTest() {

//        Packet packet = new Packet().create(
//                1,
//                DATE_TIME_FORMAT.format(new Date()),
//                this.getClass().getSimpleName(),
//                "Mukthar Ahmed"
//        );

        //System.out.println(packet.toString());
    }


}

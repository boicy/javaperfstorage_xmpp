package javaperfstorage;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

public class PerformanceStatisticsRecorder implements PacketListener {
    


    public void startRecording(XmppClient xmppClient) {
        xmppClient.pushListener(this);
    }


    public void processPacket(Packet packet) {

        Message message = (Message) packet;

        String fromUser = message.getFrom();
        String messageBody = message.getBody();

        //ok, push this straight to Mongo...


    }
}

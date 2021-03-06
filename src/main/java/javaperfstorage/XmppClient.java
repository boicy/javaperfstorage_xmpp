package javaperfstorage;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.muc.MultiUserChat;

public class XmppClient {
    private XMPPConnection connection;
    private MultiUserChat multiUserChat;

    public void connect(String xmppServerAddress, String chatroomName) {
        connect(xmppServerAddress, chatroomName, "jmeter", "jmeter");
    }

    public void connect(String xmppServerAddress, String chatroomName, String jabberUsername, String jabberPassword) {
        connection = new XMPPConnection(xmppServerAddress);
        try {
            connection.connect();
            connection.login(jabberUsername, jabberPassword);

            multiUserChat = new MultiUserChat(connection, chatroomName);
            multiUserChat.join(jabberUsername + "@" + xmppServerAddress, jabberPassword);
        } catch (XMPPException e) {
            throw new RuntimeException(e);
        }
    }


    public void sendMessage(String message) {
        try {
            multiUserChat.sendMessage(message);
        } catch (XMPPException e) {
            throw new RuntimeException(e);
        }
    }

    public void disconnect() {
        multiUserChat.leave();
        connection.disconnect();
    }

    public void pushListener(PacketListener packetListener) {
        multiUserChat.addMessageListener(packetListener);
    }
}

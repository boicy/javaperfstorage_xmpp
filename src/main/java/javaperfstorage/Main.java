package javaperfstorage;

public class Main {

    public static void main(String[] args) {
        new Main().run(
                System.getProperty("xmppServerAddress", "jalewis.thoughtworks.com"),
                System.getProperty("chatroomName", "performance@chatrooms.jalewis.thoughtworks.com"),
                System.getProperty("jabberUsername", "recorder"),
                System.getProperty("jabberPassword", "recorder")
        );
    }

    public void run(String xmppServerAddress, String chatroomName, String jabberUsername, String jabberPassword) {

        XmppClient xmppClient = new XmppClient();
        xmppClient.connect(xmppServerAddress, chatroomName, jabberUsername, jabberPassword);
        PerformanceStatisticsRecorder performanceStatisticsRecorder = new PerformanceStatisticsRecorder();
        performanceStatisticsRecorder.startRecording(xmppClient);
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

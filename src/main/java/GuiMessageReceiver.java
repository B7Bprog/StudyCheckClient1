import javafx.application.Platform;
import javafx.concurrent.Task;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

public class GuiMessageReceiver extends Task {

    static ArrayList<String> displayText = new ArrayList<String>();

    static String finalString = "";
    static String pingTest = "default";
    static int triggerCount = 0;

    DatagramSocket sock;
    byte buf[];

    public static String toString(String in) {


        in = in.substring((in.indexOf(":")) + 1, in.length());

        return in;
    }

    GuiMessageReceiver(DatagramSocket s) {
        sock = s;
        buf = new byte[1024];
    }

    @Override
    protected Object call() throws Exception {


        while (true) {
            System.out.println("Message receiver class is running");

            try {

                DatagramPacket packet = new DatagramPacket(buf, buf.length);


                sock.receive(packet);

                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("This is data: " + packet.getData());

                finalString = received;
               // byte[] bytes = packet.getData();
                //byte[] compareToThis = "[B@51e72324".getBytes();
                //System.out.println("The bytes from server data: " + bytes);
                //System.out.println("This is compareTo" + compareToThis);

                System.out.println("These are bytes from triggerText:" + GuiMessageSender.triggerText.getBytes());
                String comparable = finalString.replaceAll("\\P{Print}","");


                System.out.println("This is comparable: " + comparable);
                pingTest = comparable;
                System.out.println("THIS IS FROM THE SERVER: " + comparable);



                //if(!(finalString.getBytes().equals("[B@77d89aad"))) {
                //if((finalString.compareTo("*** Connection check - LIVE ***")) != 0){
                if(!comparable.equals("*** Connection check - LIVE ***")){
                    System.out.println("inside if and:(" + finalString + ")");
                    System.out.println("This is triggerString:(" + GuiMessageSender.triggerText + ")");
                    displayText.add(finalString);
                }

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //FX Stuff done here
                            SceneController.setMyDisplay();

                        } finally {

                        }
                    }
                });
                System.out.println("other version of string:(" +finalString +")");
            } catch (Exception e) {
                System.out.println("Something went wrong");
                System.err.println(e);
            }
        }


        //return null;
    }

}

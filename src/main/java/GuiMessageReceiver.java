import javafx.application.Platform;
import javafx.concurrent.Task;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

public class GuiMessageReceiver extends Task {

    //SceneController sceneController = new SceneController();
    static ArrayList<String> displayText = new ArrayList<String>();
    static String finalString = "";
    static String pingTest = "default";
    static int triggerCount = 0;
    //static boolean set = false;
    DatagramSocket sock;
    byte buf[];

    public static String toString(String in) {
        in = in.substring((in.indexOf(":")) + 1, in.length());
        return in;
    }

    GuiMessageReceiver(DatagramSocket s) throws SocketException {
        sock = s;
        buf = new byte[1024];
    }

    @Override
    protected Object call() throws Exception {
        SceneController sceneController = new SceneController();


        while (true) {

            try {

                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                sock.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                finalString = received;
                String comparable = finalString.replaceAll("\\P{Print}", "");
                pingTest = comparable;



                if (GuiMessageReceiver.pingTest.equals("Gergely is ON")) {
                    sceneController.setPartnerSquareToGreen();
                }
                if (GuiMessageReceiver.pingTest.equals("Gergely is OFF")) {
                    sceneController.setPartnerSquareToRed();
                }

                if (GuiMessageReceiver.pingTest.equals("*** Connection check - LIVE ***")) {
                    GuiMessageReceiver.triggerCount += 1;


                }


                if (!comparable.equals("*** Connection check - LIVE ***")) {
                    displayText.add(finalString);
                }

                if (comparable.length() > 3) {
                    GuiMessageReceiver.triggerCount++;
                }

               /* if (comparable.equals("Server000")) {
                    System.exit(0);
                }*/

                Thread.sleep(20);
                        Platform.runLater(() -> {
                            try {
                                //FX Stuff done here
                                SceneController.setMyDisplay();


                            } finally {

                            }
                        });

                        packet = null;

            } catch (Exception e) {
                System.out.println("Something went wrong");
                System.err.println(e);
            }
        }


        //return null;
    }

}

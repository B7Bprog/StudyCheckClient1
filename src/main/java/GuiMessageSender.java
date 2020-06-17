import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class GuiMessageSender {


    public final static int PORT = 88;
    private DatagramSocket sock;
    private String hostname;
    protected static String triggerText = "*** Connection check - LIVE ***";


    GuiMessageSender(DatagramSocket s, String h) {
        sock = s;

        hostname = h;
    }


    protected void sendMessage(String s) throws Exception {

        byte buf[] = s.getBytes();
        InetAddress address = InetAddress.getByName(hostname);
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, PORT);
        sock.send(packet);
    }

    public void userConnect() {

        boolean connected = false;
        do {
            try {
                sendMessage("//////////  NEW USER: " + SceneController.userName + " has joined the chat.  //////////");
                connected = true;
            } catch (Exception e) {

            }
        } while (!connected);

        String compareToThis = "";


    }

    public void send() {

        boolean connected = false;

        do {
            try {
                SceneController.userName = SceneController.userName;
                sendMessage(SceneController.userName + ": " + SceneController.messageToSend);
                connected = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!connected);

    }

    public void send2() {
        boolean connected = false;

        do {
            try {

                sendMessage(SceneController.messageToSend);
                connected = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!connected);

    }


    public void sendTrigger() {
        boolean connected = false;

        do {
            try {

                sendMessage(triggerText);
                connected = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!connected);

    }

    public void sendState(boolean state) {
        boolean connected = false;
        int counter = 0;
        do {
            try {
                if (state) {
                    sendMessage("Bela is ON");
                    counter++;
                }
                else{
                    sendMessage("Bela is OFF");
                    counter++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (counter < 3);

    }
    public void sendTotalTime(String time) throws Exception {
        sendMessage("--------------------------" + "\n" + "Bela Total Study: " + "\n" + time + "\n" + "--------------------------");
    }
    public void sendOnConfirmation() {
        boolean connected = false;

        do {
            try {
                sendMessage("Gergely ON Confirmed by Bela.");
                connected = true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!connected);

    }
    public void sendOffConfirmation() {
        boolean connected = false;

        do {
            try {
                sendMessage("Gergely OFF Confirmed by Bela.");
                connected = true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!connected);

    }


}

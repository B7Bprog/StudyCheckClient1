import javafx.concurrent.Task;

import java.net.SocketException;

public class PingListener extends Task {

    SceneController sceneController = new SceneController();

    public PingListener() throws SocketException {
    }

    @Override
    protected Object call() throws Exception {
        //int counter = 12;

        while (true) {

            Thread.sleep(95);

            if (GuiMessageReceiver.pingTest.equals("Gergely is ON")) {
                sceneController.setPartnerSquareToGreen();
            }
            if (GuiMessageReceiver.pingTest.equals("Gergely is OFF")) {
                sceneController.setPartnerSquareToRed();
            }
            if (GuiMessageReceiver.pingTest.equals("*** Connection check - LIVE ***")) {
                GuiMessageReceiver.triggerCount += 1;


            } else {

            }

        }


    }
}

import javafx.concurrent.Task;

import java.net.SocketException;

public class PingListener extends Task {

    SceneController sceneController =  new SceneController();

    public PingListener() throws SocketException {
    }

    @Override
    protected Object call() throws Exception {
        //int counter = 12;

        while (true) {
            System.out.println("pingTest is now: " + GuiMessageReceiver.pingTest);
                System.out.println("TriggerCount is now: " + GuiMessageReceiver.triggerCount);
            Thread.sleep(95);

            System.out.println("PINGLISTENER RUNNING");
            System.out.println("This is pingTest: " + GuiMessageReceiver.pingTest);

            if(GuiMessageReceiver.pingTest.equals("Gergely is ON")){
                sceneController.setPartnerSquareToGreen();
            }
            if(GuiMessageReceiver.pingTest.equals("Gergely is OFF")){
                sceneController.setPartnerSquareToRed();
            }

            if (GuiMessageReceiver.pingTest.equals("*** Connection check - LIVE ***")) {
                System.out.println("INSIDE PINGLISTENER IFFFFFFF");
                GuiMessageReceiver.triggerCount += 1;


            } else {

            }

        }


    }
}

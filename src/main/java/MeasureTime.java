import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;

import java.net.SocketException;

public class MeasureTime extends Task {

    SceneController sceneController = new SceneController();

    public MeasureTime() throws SocketException {
    }

    @Override
    protected Object call() throws Exception {



        while(true){
            Thread.sleep(5000);
            if(GuiMessageReceiver.triggerCount > 0){
                System.out.println("+++++++++Server is Online+++++++++++++");

                GuiMessageReceiver.triggerCount = 0;
                GuiMessageReceiver.pingTest = "default";

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //FX Stuff done here
                            //SceneController.mySend.setDisable(false);
                            SceneController.mySign.setFill(Color.GREEN);

                            SceneController.setMyDisplay();

                        } finally {

                        }
                    }
                });
            }else{
                System.out.println("---------------Server is offline----------------");

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            SceneController.mySign.setFill(Color.RED);
                            //sceneController.startReceiver();

                            //SceneController.setMyDisplay();
                            SceneController.setDisplayServerOffline();
                            System.out.println();



                            /*String host = "86.1.51.222";

                            DatagramSocket socket = new DatagramSocket();

                            GuiMessageReceiver guiMessageReceiver = new GuiMessageReceiver(socket);
                            new Thread(guiMessageReceiver).start();*/


                        /*} catch (SocketException e) {
                            e.printStackTrace();
                        }*/
                        } catch ( InterruptedException e) {
                            e.printStackTrace();
                        } finally {

                        }
                    }
                });
            }
        }



    }
}

import javafx.concurrent.Task;

import java.net.DatagramSocket;

public class Trigger extends Task {
    @Override
    protected Object call() throws Exception {
        do {
            System.out.println("Iterated in client triggerThread");
            Thread.sleep(2000);
            String host = "86.1.51.222";

            DatagramSocket socket = new DatagramSocket();
            GuiMessageSender guiMessageSender = new GuiMessageSender(socket, host);
            guiMessageSender.sendTrigger();
            System.out.println("Trigger message has been sent");

        }while(true);
    }
}

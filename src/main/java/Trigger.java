import javafx.concurrent.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Trigger extends Task {
    protected static long startTimeMinutes = 0;
    protected static long currentTimeMinutes = 0;
    protected static String[] splitCurrentTime;
    protected static String[] splitTimeFromFile;
    protected static int timeForMainLog = 0;
    protected static int minuteCounter = 0;

    @Override
    protected Object call() throws Exception {
        //DatagramSocket socket = new DatagramSocket();
        SceneController sceneController = new SceneController();
        do {
            

            //String host = "86.1.51.222";
            //DatagramSocket socket = new DatagramSocket();
            //GuiMessageSender guiMessageSender = new GuiMessageSender(socket, host);
            sceneController.sendTriggerMessage();

            //guiMessageSender.sendState(SceneController.currentState);

            sceneController.sendStateForTrigger();

            SimpleDateFormat formatter3 = new SimpleDateFormat("HH:mm");
            Date date3 = new Date();
            splitCurrentTime = formatter3.format(date3).split(":");
            currentTimeMinutes =  ((Long.parseLong(splitCurrentTime[0])*60)+ Long.parseLong(splitCurrentTime[1]));
            SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
            Date date1 = new Date();
            long timePassed = currentTimeMinutes - startTimeMinutes;

            try {
                LoadFile.readFile();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            System.out.println("In trigger middle");

            String totalTime = Long.toString(LoadFile.timeAtLoad + timePassed );
            System.out.println("Total time now is: " + totalTime);

            if(!LoadFile.splitWords[0].equals(formatter1.format(date1))){
                try {
                    WriteAppend.write("mainLog.txt", "\n" + LoadFile.splitWords[0] + " Total time learned: " + (Integer.toString(Integer.parseInt(LoadFile.splitWords[1]) / 60)) + "hour(s) and " + (Integer.toString(Integer.parseInt(LoadFile.splitWords[1]) % 60)) + " minute(s).");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("This wont run");
                Thread.sleep(500);
                try {
                    //LoadFile.timeAtLoad = 0;
                    WriteFile.write("currentLog.txt", formatter1.format(date1) + ";" + "0");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Thread.sleep(1000);
            if(SceneController.currentState) {

                if (LoadFile.splitWords[0].equals(formatter1.format(date1))) {
                    WriteFile.write("currentLog.txt", formatter1.format(date1) + ";" + totalTime);
                }
            }

            //guiMessageSender.sendTotalTime((Long.toString(Long.parseLong(LoadFile.splitWords[1])/60)) + " hour(s) and " + (Long.toString(Long.parseLong(LoadFile.splitWords[1])%60)) + " min(s)");
            sceneController.sendTime((Long.toString(Long.parseLong(LoadFile.splitWords[1])/60)) + " hour(s) and " + (Long.toString(Long.parseLong(LoadFile.splitWords[1])%60)) + " min(s)");
            Thread.sleep(25000);









        }while(true);

    }
}

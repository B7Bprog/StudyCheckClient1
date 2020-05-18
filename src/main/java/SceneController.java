import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.URL;
import java.util.ResourceBundle;

public class SceneController implements Initializable {




    String host = "86.1.51.222";

    DatagramSocket socket = new DatagramSocket();
    GuiMessageReceiver receiver = new GuiMessageReceiver(socket);
    GuiMessageSender guiMessageSender = new GuiMessageSender(socket, host);

    static boolean checkBoxChecked = false;
    static String userName = "";
    static String messageToSend = "";

    private static Label myDisplay;
    protected static Button mySend;
    private static Button myExit;
    private static TextField myMessage;
    private static TextField myName;
    protected static Button myConnect;
    protected static Rectangle myPartnerState;
    protected static Circle mySign;
    protected static CheckBox myCheckBox;
    protected static Rectangle myMyState;



    @FXML
    private Label display;

    @FXML
    private Rectangle myState;

    @FXML
    private Rectangle partnerState;

    @FXML
    private CheckBox checkBox;





    @FXML
    private Circle sign;

    @FXML

    private Button exit;

    public SceneController() throws SocketException {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        myCheckBox = checkBox;
        myDisplay = display;
        myExit = exit;
        myPartnerState = partnerState;
        myMyState = myState;


        mySign = sign;



        new Thread(receiver).start();
        try {
            checkServerState();
        } catch (InterruptedException | SocketException e) {
            e.printStackTrace();
        }
    }
    public void startReceiver(){
        new Thread(receiver).start();

    }

    public void sendMessage() throws InterruptedException {

        System.out.println("Send button pressed 1");
        //setMessage();
        System.out.println("Send button pressed 2");
        Thread.sleep(100);
        System.out.println("Send button pressed 3");
        guiMessageSender.send();
        System.out.println("Send button pressed 4");
        // clearMessageTextField();


    }

    public void checkServerState() throws InterruptedException, SocketException {

        Trigger trigger = new Trigger();
        PingListener pingListener = new PingListener();
        MeasureTime measureTime = new MeasureTime();
        new Thread(trigger).start();
        new Thread(pingListener).start();
        new Thread(measureTime).start();



        //new Thread(receiver).start();
        messageToSend = "Server is Live";
        guiMessageSender.send2();
        Thread.sleep(500);
        System.out.println("Ez a hossza: " + (GuiMessageReceiver.finalString).length());


        if (!((GuiMessageReceiver.finalString).length() > 0)) {
            //setConnectButtonDisabled();
            myDisplay.setText("Server is offline.");

        } else {
            mySign.setFill(Color.GREEN);
            //myConnect.setDisable(false);
        }


    }

    /*public static void getUserName() {
        userName = (myName.getText()).toUpperCase();
    }*/

    public void connect() throws SocketException {
        //getUserName();
        //if (userName.length() > 0) {
        //myMessage.setDisable(false);
        //mySend.setDisable(false);

        //setConnectButtonDisabled();


        new Thread(receiver).start();


        System.out.println("In first connect");


        //getUserName();

        guiMessageSender.userConnect();
        //}
    }



    public static void setDisplayServerOffline() throws InterruptedException {
        myDisplay.setText("");
        myDisplay.setText("Server is offline.");
        Terminator terminator = new Terminator();
        new Thread(terminator).start();
        //Thread.sleep(2000);
        //System.exit(0);
    }

    public static void setMyDisplay() {

        String showThis = "";
        for (int i = GuiMessageReceiver.displayText.size() - 1; i >= 0; i--) {
            showThis += GuiMessageReceiver.displayText.get(i) + "\n";

        }
        showThis = showThis.replaceAll("á", "\u00E1");
        showThis = showThis.replaceAll("é", "\u00E9");
        showThis = showThis.replaceAll("í", "\u00ED");
        showThis = showThis.replaceAll("ó", "o");
        showThis = showThis.replaceAll("ö", "\u00F6");
        showThis = showThis.replaceAll("ő", "o");
        showThis = showThis.replaceAll("ú", "\u00FA");
        showThis = showThis.replaceAll("ü", "\u00FC");
        showThis = showThis.replaceAll("ű", "\u0171");

        //showThis = showThis.replaceAll("","á");
        showThis = showThis.replaceAll("É", "\u00C9");
        //showThis = showThis.replaceAll("","\u00CD");
        showThis = showThis.replaceAll("Ó", "\u00D3");
        showThis = showThis.replaceAll("Ö", "\u00D6");
        // showThis = showThis.replaceAll("","\u0150");
        showThis = showThis.replaceAll("Ú", "\u00DA");
        showThis = showThis.replaceAll("Ü", "\u00DC");
        showThis = showThis.replaceAll("Ű", "\u0170");
        myDisplay.setText(showThis);
    }

    public void checkBox(){
        if(checkBoxChecked){
            checkBoxChecked = false;
        }
        else{
            checkBoxChecked = true;
        }
        if(checkBoxChecked){
            myMyState.setFill(Color.GREEN);
            guiMessageSender.sendState(true);
        }else{
            myMyState.setFill(Color.RED);
            guiMessageSender.sendState(false);
        }

    }
    public void setPartnerSquareToGreen(){
        myPartnerState.setFill(Color.GREEN);

        guiMessageSender.sendOnConfirmation();


    }

    public void setPartnerSquareToRed(){
        myPartnerState.setFill(Color.RED);

        guiMessageSender.sendOffConfirmation();

    }

    public void exit() {
        guiMessageSender.sendState(false);
        guiMessageSender.sendState(false);
        System.exit(0);
    }

}

import javafx.concurrent.Task;

public class Terminator extends Task {
    @Override
    protected Object call() throws Exception {
        Thread.sleep(50000);
        //System.exit(0);
        return null;
    }
}

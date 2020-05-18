import javafx.concurrent.Task;

public class Terminator extends Task {
    @Override
    protected Object call() throws Exception {
        Thread.sleep(5000);
        System.exit(0);
        return null;
    }
}

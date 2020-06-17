import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadFile {
    static String[] splitWords;
    static String oneLine = "";
    static int timeAtLoad = 0;
    public static void readFile() throws FileNotFoundException {
        String filename = "currentLog.txt";
        Scanner scanner = new Scanner(new File(filename));
        //String oneLine;
        while (scanner.hasNextLine()) {

            oneLine = scanner.nextLine();




            splitWords = oneLine.split(";");
            if(timeAtLoad == 0) {
                timeAtLoad = Integer.parseInt(splitWords[1]);
            }





        }

    }
}

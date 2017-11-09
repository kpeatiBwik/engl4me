package engl4me;

import java.io.FileOutputStream;
import java.io.IOException;

public abstract class Helper {
    void writeToFile(String s) {
        try {
            byte x[] = s.getBytes();
            FileOutputStream fileOutputStream = new FileOutputStream("dbbackup.csv");
            fileOutputStream.write(x);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

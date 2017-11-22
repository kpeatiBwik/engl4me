package engl4me;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

class Helper {
    void writeToFile(String s) {
        try {
            Writer fileOutputStream = new FileWriter("dbbackup.csv");
            fileOutputStream.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

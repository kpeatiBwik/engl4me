package engl4me;

import java.util.List;

public interface SupportActions {
    void write(Word word);

    List<Word> read();

    void writeFile(String s);
}

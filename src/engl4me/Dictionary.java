package engl4me;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class Dictionary {

    private List wordList;

    private final String url = "jdbc:mysql://localhost:3306/engl4me?useUnicode=true&characterEncoding=utf8";
    private final String user = "root";
    private final String password = "";

    private Connection con;
    private Statement stmt;

    List getWordList() throws CommunicationsException {
        return wordList = read();
    }

    private void setWordList(List wordList) {
        this.wordList = wordList;
    }

    void writeWordToDictionary(Word word) {
        String query = ("insert into dictionary(word, translate, comments) values('" + word.getWord() + "', '" + word.getTranslate() + "', '" + word.getComments() + "')");
        try {
            con = (Connection) DriverManager.getConnection(url, user, password);
            stmt = (Statement) con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    int getId(String s) {
        String query = ("SELECT * FROM dictionary WHERE word='" + s.toUpperCase() + "'");
        int id = 0;
        try {
            con = (Connection) DriverManager.getConnection(url, user, password);
            stmt = (Statement) con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    void writeDictionaryToFile(String s) {
        new Helper().writeToFile(s);
    }

    private List<Word> read() {
        List<Word> words = new ArrayList<>();
        String query = "select * from dictionary";
        try {
            con = (Connection) DriverManager.getConnection(url, user, password);
            stmt = (Statement) con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                words.add(new Word(rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException sqlEx) {
            System.out.println("Не могу подключится к MySQL серверу");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        setWordList(words);
        return words;
    }

    void searchByWord(String s) {
        String[] s1 = read().toString().split(",");
        for (String s2 : s1) {
            if (s2.contains(s.toUpperCase())) {
                System.out.println(s2);
            }
        }
    }
}

package engl4me;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class SupportActions {

    private final String url = "jdbc:mysql://localhost:3306/engl4me?useUnicode=true&characterEncoding=utf8";
    private final String user = "root";
    private final String password = "";

    private Connection con;
    private Statement stmt;


    void write(Word word) {
        String query = ("insert into dictionary(word, translate, comments) values('" + word.getWord() + "', '" + word.getTranslate() + "', '" + word.getComments() + "')");
        try {
            con = (Connection) DriverManager.getConnection(url, user, password);
            stmt = (Statement) con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    List read() {
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
            sqlEx.printStackTrace();
        }
        return words;
    }

    void writeFile(String s) {
        try {
            byte x[] = s.getBytes();
            FileOutputStream fileOutputStream = new FileOutputStream("dbbackup.csv");
            fileOutputStream.write(x);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

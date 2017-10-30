package engl4me;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    private static final String url = "jdbc:mysql://localhost:3306/engl4me";
    private static final String user = "root";
    private static final String password = "";

    private static Connection con;
    private static Statement stmt;
    private static Scanner sn = new Scanner(System.in);

    public static void main(String[] args) {
        String x = "1";
        while (x.equals("1") || x.equals("2")) {
            System.out.println("1 - добавить новое слово, 2 - прочитать словарь");
            x = sn.nextLine();
            switch (x) {
                case "1":
                    System.out.println("Введите новое слово:");
                    String word = sn.nextLine();
                    System.out.println("Введите перевод слова:");
                    String translate = sn.nextLine();
                    System.out.println("Введите комментарий:");
                    String comments = sn.nextLine();
                    write(word, translate, comments);
                    break;
                case "2":
                    read();
                    break;
            }
        }
    }

    private static void write(String word, String translate, String comments) {
        String query = ("insert into dictionary(word, translate, comments) values('" + word + "', '" + translate + "', '" + comments + "')");
        try {
            con = (Connection) DriverManager.getConnection(url, user, password);
            stmt = (Statement) con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    private static void read() {
        String query = "select * from dictionary";
        try {
            con = (Connection) DriverManager.getConnection(url, user, password);
            stmt = (Statement) con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt(1);
                String word = rs.getString(2);
                String translate = rs.getString(3);
                String comments = rs.getString(4);
                System.out.println("id: " + id + "; word -> " + word + "; translate -> " + translate + "; comments -> " + comments);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }
}

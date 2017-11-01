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
import java.util.Scanner;

public class Main {

    private static final String url = "jdbc:mysql://localhost:3306/engl4me?useUnicode=true&characterEncoding=utf8";
    private static final String user = "root";
    private static final String password = "";

    private static Connection con;
    private static Statement stmt;
    private static Scanner sn = new Scanner(System.in);

    public static void main(String[] args) {
        String x = "1";
        while (x.equals("1") || x.equals("2") || x.equals("3") || x.equals("4") || x.equals("5")) {
            System.out.println("1 - добавить новое слово\n2 - прочитать словарь\n3 - сделать бэкап базы\n4 - поиск слова\n5 - выход");
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
                    System.out.println(read());
                    break;
                case "3":
                    writeFile(read().toString());
                    break;
                case "4":
                    System.out.println("Введите слово которое необходимо найти:");
                    searchWord(sn.nextLine());
                    break;
                case "5":
                    System.exit(0);
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

    private static StringBuilder read() {
        List<Word> words = new ArrayList<>();
        String query = "select * from dictionary";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            con = (Connection) DriverManager.getConnection(url, user, password);
            stmt = (Statement) con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString(1);
                String word = rs.getString(2);
                String translate = rs.getString(3);
                String comments = rs.getString(4);
                words.add(new Word(rs.getString(2),rs.getString(3),rs.getString(4)));
                stringBuilder.append(word).append(";").append(translate).append(";").append(comments).append("\n");
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return stringBuilder;
    }

    private static void writeFile(String s) {
        try {
            byte x[] = s.getBytes();
            FileOutputStream fileOutputStream = new FileOutputStream("dbbackup.csv");
            fileOutputStream.write(x);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void searchWord(String s) {
        String[] s1 = read().toString().split("\n");
        for (String s2 : s1) {
            if (s2.contains(s)) {
                System.out.println(s2);
            }
        }
    }
}

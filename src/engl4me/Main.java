package engl4me;

import java.util.Scanner;

public class Main {

    private static Dictionary dictionary = new Dictionary();
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
                    dictionary.writeWordToDictionary(new Word(word.toUpperCase(), translate.toUpperCase(), comments.toUpperCase()));
                    break;
                case "2":
                    System.out.println(dictionary.getWordList().toString());
                    break;
                case "3":
                    dictionary.writeDictionaryToFile(dictionary.getWordList().toString());
                    break;
                case "4":
                    System.out.println("Введите слово которое необходимо найти:");
                    dictionary.searchByWord(sn.nextLine());
                    break;
                case "5":
                    System.exit(0);
                    break;
            }
        }
    }
}

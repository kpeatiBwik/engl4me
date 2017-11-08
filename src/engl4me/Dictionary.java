package engl4me;

public class Dictionary implements Search{
    @Override
    public void searchByWord(String s) {
            String[] s1 = supportActions.read().toString().split(",");
            for (String s2 : s1) {
                if (s2.contains(s.toUpperCase())) {
                    System.out.println(s2);
                }
            }
    }

    @Override
    public String searchByTranslate(String s) {
        return null;
    }

    @Override
    public String searchCommentByWord(String s) {
        return null;
    }
}

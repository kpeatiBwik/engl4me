package engl4me;

interface Search {

    SupportActions supportActions = new SupportActions();

    void searchByWord(String s);

    String searchByTranslate(String s);

    String searchCommentByWord(String s);

}

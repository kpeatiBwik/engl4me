package engl4me;

public class Word {
    private int id;
    private String word;
    private String translate;
    private String comments;

    public Word(String word, String translate, String comments) {
        setWord(word);
        setTranslate(translate);
        setComments(comments);
    }

    String getWord() {
        return word;
    }

    private void setWord(String word) {
        this.word = word;
    }

    String getTranslate() {
        return translate;
    }

    private void setTranslate(String translate) {
        this.translate = translate;
    }

    String getComments() {
        return comments;
    }

    private void setComments(String comments) {
        this.comments = comments;
    }

    public void updateWord(String word, String translate, String comment) {

    }

    @Override
    public String toString() {
        return getWord() + " -> " + getTranslate() + " -> " + getComments();
    }

}

package engl4me;

public class Word {
    private int id;
    private String word;
    private String translate;
    private String comments;

    public Word(String word, String translate, String comments) {
        this.word = word;
        this.translate = translate;
        this.comments = comments;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void updateWord(String word, String translate, String comment) {

    }

    @Override
    public String toString() {
        return getWord() + " -> " + getTranslate() + " -> " + getComments();
    }

}

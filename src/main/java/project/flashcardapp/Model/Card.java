package project.flashcardapp.Model;

/**
 * Card model for keeping information about question and answers
 */

public class Card {
    private String question;
    private String answer;

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    private Selector selector = new Selector();

    public String getQuestion() {
        return question;
    }

    public Card setQuestion(String question) {
        this.question = question;
        return this;
    }

    public String getAnswer() {
        return answer;
    }

    public Card setAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    public Selector getSelector() {
        return selector;
    }

}

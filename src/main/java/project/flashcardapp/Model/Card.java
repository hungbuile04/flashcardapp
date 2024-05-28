package project.flashcardapp.Model;

/**
 * Card model for keeping information about question and answers
 *
 * @author Bartlomiej Gladys
 * @Date 01/11/2018
 * @version 1.0
 */

public class Card {
    /**
     * Question field
     */
    private String question;

    /**
     * Answer field
     */
    private String answer;

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    /**
     * Selector field necessary for sorting
     */
    private Selector selector = new Selector();

    /**
     * question getter
     *
     * @return question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * question setter
     *
     * @param question
     * @return car object
     */
    public Card setQuestion(String question) {
        this.question = question;
        return this;
    }

    /**
     * answer getter
     *
     * @return answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * answer setter
     *
     * @param answer
     * @return car object
     */
    public Card setAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    /**
     * selection getter
     *
     * @return selector
     */
    public Selector getSelector() {
        return selector;
    }

}

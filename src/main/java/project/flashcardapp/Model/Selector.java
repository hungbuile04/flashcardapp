package project.flashcardapp.Model;

import java.util.Date;

/**
 * Selector model for keeping data related to card's order
 *
 * @author Bartlomiej Gladys
 * @Date 01/11/2018
 * @version 1.0
 */

public class Selector {
    /**
     * possible answers given by user
     */
    public enum AnswerType {
        CORRECT, MEDIUM, FAILURE
    }

    /**
     * date of recent object's update
     */
    private Date updatedAt = new Date();

    /**
     * default answerType for Selector
     */
    private AnswerType answerType = AnswerType.MEDIUM;

    /**
     * indicate how many times user shown related card
     */
    private int cycle = 0;

    /**
     * updateAt getter
     *
     * @return recent object's update
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * updateAt setter
     *
     * @param updatedAt recent object's update
     * @return Selector object
     */
    public Selector setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    /**
     * answerType getter
     *
     * @return last answerType
     */
    public AnswerType getAnswerType() {
        return answerType;
    }

    /**
     * answerType setter
     *
     * @param answerType set by user
     * @return Selector object
     */
    public Selector setAnswerType(AnswerType answerType) {
        this.answerType = answerType;
        return this;
    }

    /**
     * cycle getter
     *
     * @return current cycle
     */
    public int getCycle() {
        return cycle;
    }

    /**
     * cycle setter
     *
     * @param cycle
     * @return Selector object
     */
    public Selector setCycle(int cycle) {
        this.cycle = cycle;
        return this;
    }

    /**
     * Update selector after each user's action
     *
     * @param type chosen by user
     */
    public void update(AnswerType type) {
        setAnswerType(type);
        setUpdatedAt(new Date());
        cycle++;
    }

}

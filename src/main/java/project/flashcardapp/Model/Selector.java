package project.flashcardapp.Model;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * Selector model for keeping data related to card's order
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
    private Date deadlineAt = new Date();

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
    public Date getDeadlineAt() {
        return deadlineAt;
    }


    public Selector setDeadlineAt(Date deadlineAt) {
        this.deadlineAt = deadlineAt;
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
    //cài đặt hạn cho các thẻ
    public void update(AnswerType type, int easyCard, int mediumCard, int hardCard) {
        setAnswerType(type);
        if(type == AnswerType.CORRECT) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_YEAR, easyCard);
            setDeadlineAt(calendar.getTime());
        }
        if(type == AnswerType.MEDIUM) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_YEAR, mediumCard);
            setDeadlineAt(calendar.getTime());
        }
        if(type == AnswerType.FAILURE) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_YEAR, hardCard);
            setDeadlineAt(calendar.getTime());
        }
        cycle++;
    }

}

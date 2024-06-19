package project.flashcardapp.Model;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * Selector model for keeping data related to card's order
 */

public class Selector {
    public enum AnswerType {
        CORRECT, MEDIUM, FAILURE
    }
    private Date deadlineAt = new Date();
    private AnswerType answerType = AnswerType.MEDIUM;
    private int cycle = 0;

    public Date getDeadlineAt() {
        return deadlineAt;
    }


    public Selector setDeadlineAt(Date deadlineAt) {
        this.deadlineAt = deadlineAt;
        return this;
    }

    public AnswerType getAnswerType() {
        return answerType;
    }

    public Selector setAnswerType(AnswerType answerType) {
        this.answerType = answerType;
        return this;
    }

    public int getCycle() {
        return cycle;
    }

    public Selector setCycle(int cycle) {
        this.cycle = cycle;
        return this;
    }

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

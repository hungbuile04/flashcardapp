package project.flashcardapp.Model;
import project.flashcardapp.Model.Selector.AnswerType;
import java.util.Comparator;
import java.util.Date;

/**
 * Comparator class for sorting cards
 */

public class SortBySelector implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
        Selector first = o1.getSelector();
        Selector second = o2.getSelector();
        return compareSelectors(first, second);
    }

    // trả về theo độ ưu tiên: mức độ hiểu lớn hơn -> số lần học ít hơn -> deadline đến sớm hơn
    private int compareSelectors(Selector first, Selector second) {
        int isFirstWeaker = checkAnswerType(first.getAnswerType(), second.getAnswerType());
        if (isFirstWeaker != 0) {
            return isFirstWeaker;
        }

        int hasFirstLessCycles = checkCycles(first.getCycle(), second.getCycle());
        if (hasFirstLessCycles != 0) {
            return hasFirstLessCycles;
        }

        return checkUpdateDate(first.getDeadlineAt(), second.getDeadlineAt());
    }

    private int checkCycles(int first, int second) {
        return first - second;
    }

    private int checkUpdateDate(Date first, Date second) {
        return second.compareTo(first);
    }

    private int checkAnswerType(AnswerType first, AnswerType second) {
        if (first == second) {
            return 0;
        }
        boolean isFirstTheFailure = first == AnswerType.FAILURE;
        boolean isFirstWeaker = first == AnswerType.MEDIUM && second == AnswerType.CORRECT;
        return isFirstTheFailure || isFirstWeaker ? -1 : 1;
    }
}



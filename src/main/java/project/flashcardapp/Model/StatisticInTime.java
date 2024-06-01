package project.flashcardapp.Model;

public class StatisticInTime {
    public String date;
    public int newCards=0;
    public int learnedCards=0;
    public int dueCards=0;

    public StatisticInTime getStatistic(Deck ex, String WeekOrMonth){
        date = WeekOrMonth=="week"?Date.getDayofWeek():Date.getMonth();
        newCards = ex.getNewCards();
        learnedCards = ex.getLearnedCards();
        dueCards = ex.getDueCards();
        return this;
    }
}

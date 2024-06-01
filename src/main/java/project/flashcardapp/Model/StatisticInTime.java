package project.flashcardapp.Model;

public class StatisticInTime {
    public String date;
    public int newCards=0;
    public int learnedCards=0;
    public int dueCards=0;

    public static StatisticInTime plusStatistic(StatisticInTime stat1, StatisticInTime stat2) {
        StatisticInTime result = new StatisticInTime();
        result.date = stat1.date;
        result.newCards = stat1.newCards + stat2.newCards;
        result.learnedCards = stat1.learnedCards + stat2.learnedCards;
        result.dueCards = stat1.dueCards + stat2.dueCards;
        return result;
    }

    public StatisticInTime getStatistic(Deck ex, String WeekOrMonth){
        date = WeekOrMonth=="week"?Date.getDayofWeek():Date.getMonth();
        newCards = ex.getNewCards();
        learnedCards = ex.getLearnedCards();
        dueCards = ex.getDueCards();
        return this;
    }
}

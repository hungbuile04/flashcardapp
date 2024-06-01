package project.flashcardapp.Model;

import java.util.ArrayList;

public class StatisticOfDeck {

    public ArrayList<StatisticInTime> statisticsInWeek;
    public ArrayList<StatisticInTime> statisticsInMonth;


    public void storeStatistic_Week(Deck deck) {
        StatisticInTime statistic = new StatisticInTime();
        statistic.getStatistic(deck, "week");
        if (statisticsInWeek == null) statisticsInWeek.add(statistic);
        if (statistic.date != statisticsInWeek.get(0).date) statisticsInWeek.add(0, statistic);
        else statisticsInWeek.set(0, statistic);
    }

    public void storeStatistic_Month(Deck deck) {
        StatisticInTime statistic = new StatisticInTime();
        StatisticInTime substatistic = new StatisticInTime();
        statistic.getStatistic(deck, "month");
        substatistic.getStatistic(deck, "week");
        if (statisticsInMonth == null) statisticsInMonth.add(0, statistic);
        if (statistic.date != statisticsInMonth.get(0).date){
            statisticsInMonth.add(0, statistic);
            statisticsInMonth.set(1, StatisticInTime.plusStatistic(statisticsInMonth.get(1), statisticsInWeek.get(1)));
        }
        if (statistic.date == statisticsInMonth.get(0).date && substatistic.date != statisticsInWeek.get(0).date) {
            statisticsInMonth.set(0, StatisticInTime.plusStatistic(statisticsInMonth.get(0), statisticsInWeek.get(1)));
        }
    }
}

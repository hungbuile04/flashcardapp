package project.flashcardapp.Model;

import java.util.ArrayList;
import java.util.Objects;

public class StatisticOfDeck {

    public ArrayList<StatisticInTime> statisticsInWeek = new ArrayList<>();
    public ArrayList<StatisticInTime> statisticsInMonth = new ArrayList<>();

    // Lưu Stats theo tuần
    public void storeStatistic_Week(Deck deck) {
        StatisticInTime statistic = new StatisticInTime();
        statistic.getStatistic(deck, "week");

        if (statisticsInWeek.isEmpty()) {
            statisticsInWeek.add(statistic);
        } else if (!Objects.equals(statistic.date, statisticsInWeek.get(0).date)) {
            statisticsInWeek.add(0, statistic);
        } else {
            statisticsInWeek.set(0, statistic);
        }

        // Loại bỏ các mục trùng lặp theo ngày
        removeDuplicateStatistics(statisticsInWeek);
    }

    // Lưu Stats theo tháng
    public void storeStatistic_Month(Deck deck) {
        StatisticInTime statistic = new StatisticInTime();
        StatisticInTime substatistic = new StatisticInTime();
        statistic.getStatistic(deck, "month");
        substatistic.getStatistic(deck, "week");

        if (statisticsInMonth.isEmpty()) {
            statisticsInMonth.add(0, statistic);
        } else if (!Objects.equals(statistic.date, statisticsInMonth.get(0).date)) {
            statisticsInMonth.add(0, statistic);
        } else {
            statisticsInMonth.set(0, statistic);
        }

        if (!Objects.equals(substatistic.date, statisticsInWeek.get(0).date)) {
            StatisticInTime updatedStatistic = StatisticInTime.plusStatistic(statisticsInMonth.get(0), statisticsInWeek.get(1));
            statisticsInMonth.set(0, updatedStatistic);
        }

        // Loại bỏ các mục trùng lặp theo ngày
        removeDuplicateStatistics(statisticsInMonth);
    }

    // Loại bỏ các mục trùng lặp trong danh sách
    private void removeDuplicateStatistics(ArrayList<StatisticInTime> statistics) {
        ArrayList<StatisticInTime> uniqueStatistics = new ArrayList<>();
        for (StatisticInTime statistic : statistics) {
            boolean isDuplicate = false;
            for (StatisticInTime uniqueStatistic : uniqueStatistics) {
                if (Objects.equals(statistic.date, uniqueStatistic.date)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                uniqueStatistics.add(statistic);
            }
        }
        statistics.clear();
        statistics.addAll(uniqueStatistics);
    }
}

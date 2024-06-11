package project.flashcardapp.Controller.Display;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;
import project.flashcardapp.Model.Deck;
import project.flashcardapp.Model.DeckData;
import project.flashcardapp.Model.StatisticInTime;

import java.util.Collections;

public class StatisticsController {
    private Deck deck;

    @FXML
    private ComboBox<String> WeekOrMonth;
    @FXML
    private ComboBox<Deck> WhichDeck;
    @FXML
    private BarChart<String, Number> StatisticBarChart;

    @FXML
    public void initialize() throws Exception {
        //Tạo deck All lưu thông số của tất cả các deck
        Deck all = new Deck();
        all.setDeckName("All");
        for (int i=0; i<7; i++) {
            all.StatOfDeck.statisticsInMonth.add(new StatisticInTime());
            all.StatOfDeck.statisticsInWeek.add(new StatisticInTime());
        }
        for (int i=0; i<7; i++) {
            for (Deck t : DeckData.decks) {
                if (t.StatOfDeck.statisticsInMonth.isEmpty()) continue;
                if (t.StatOfDeck.statisticsInMonth.size()<=i) continue;
                StatisticInTime temp = new StatisticInTime();
                temp = all.StatOfDeck.statisticsInMonth.get(i);
                all.StatOfDeck.statisticsInMonth.set(i, StatisticInTime.plusStatistic(t.StatOfDeck.statisticsInMonth.get(i), temp));
            }
            if (all.StatOfDeck.statisticsInMonth.get(i).date=="null") all.StatOfDeck.statisticsInMonth.remove(i);
            break;
        }
        for (int i=0; i<7; i++) {
            for (Deck t : DeckData.decks) {
                if (t.StatOfDeck.statisticsInWeek.isEmpty()) continue;
                if (t.StatOfDeck.statisticsInWeek.size()<=i) continue;
                StatisticInTime temp = new StatisticInTime();
                temp = all.StatOfDeck.statisticsInWeek.get(i);
                all.StatOfDeck.statisticsInWeek.set(i, StatisticInTime.plusStatistic(t.StatOfDeck.statisticsInWeek.get(i), temp));
            }
            if (all.StatOfDeck.statisticsInWeek.get(i).date=="null") all.StatOfDeck.statisticsInWeek.remove(i);
            break;
        }

        //Thiết lập comboBox chọn hiển thị theo tuần hay tháng
        ObservableList<String> WorM = FXCollections.observableArrayList("Week", "Month");
        WeekOrMonth.setItems(WorM);
        WeekOrMonth.getSelectionModel().selectFirst();

        //Thiết lập comboBox chọn hiển thị thống kê của deck nào
        ObservableList<Deck> chosenDeck = FXCollections.observableArrayList();
        chosenDeck.add(all);
        chosenDeck.addAll(DeckData.decks);
        WhichDeck.getItems().addAll(chosenDeck);
        WhichDeck.setConverter(new StringConverter<Deck>() {
            @Override
            public String toString(Deck deck) {
                return deck.getDeckName();
            }

            @Override
            public Deck fromString(String s) {
                return null;
            }
        });
        WhichDeck.getSelectionModel().selectFirst();
        deck = WhichDeck.getSelectionModel().getSelectedItem();

        // Hiển thị biểu đồ cột
        CategoryAxis xAxis = new CategoryAxis(); xAxis.setLabel("Time");
        NumberAxis yAxis = new NumberAxis(); yAxis.setLabel("Count");
        //StatisticBarChart = new BarChart<>(xAxis, yAxis);
        StatisticBarChart.setTitle("Statistic");
        updateChart();
        if (StatisticBarChart.getData().size() == 0) {
            System.out.println("Dữ liệu cho BarChart là rỗng.");
        } else {
            System.out.println("Dữ liệu cho BarChart không rỗng.");
            System.out.println(StatisticBarChart.getData().size());
        }


        //Cập nhật biểu đồ khi thay đổi lựa chọn hiển thị
        WhichDeck.setOnAction(event -> {
            deck = WhichDeck.getSelectionModel().getSelectedItem();
            updateChart();
            if (StatisticBarChart.getData().size() == 0) {
                System.out.println("Dữ liệu cho BarChart là rỗng.");
            } else {
                System.out.println("Dữ liệu cho BarChart không rỗng.");
                System.out.println(StatisticBarChart.getData().size());
            }
        });
        WeekOrMonth.setOnAction(event -> updateChart());
    }

    //Hàm cập nhật biểu đồ
    private void updateChart() {
        StatisticBarChart.getData().clear(); // Xóa dữ liệu cũ

        if (deck != null && deck.StatOfDeck != null) {
            if (WeekOrMonth.getValue().equals("Week")) {
                XYChart.Series<String, Number> newCard = new XYChart.Series<>();
                newCard.setName("New Cards");
                for (int i = 0; i < 7 && i < (deck.StatOfDeck.statisticsInWeek.size()); i++) {
                    newCard.getData().add(new XYChart.Data<String, Number>(deck.StatOfDeck.statisticsInWeek.get(i).date, deck.StatOfDeck.statisticsInWeek.get(i).newCards));
                }
//                Collections.reverse(newCard.getData());

                XYChart.Series<String, Number> learnedCard = new XYChart.Series<>();
                learnedCard.setName("Learned Cards");
                for (int i = 0; i < 7 && i < (deck.StatOfDeck.statisticsInWeek.size()); i++) {
                    learnedCard.getData().add(new XYChart.Data<String, Number>(deck.StatOfDeck.statisticsInWeek.get(i).date, deck.StatOfDeck.statisticsInWeek.get(i).learnedCards));
                }
//                Collections.reverse(learnedCard.getData());

                XYChart.Series<String, Number> dueCard = new XYChart.Series<>();
                dueCard.setName("Due Cards");
                for (int i = 0; i < 7 && i < (deck.StatOfDeck.statisticsInWeek.size()); i++) {
                    dueCard.getData().add(new XYChart.Data<String, Number>(deck.StatOfDeck.statisticsInWeek.get(i).date, deck.StatOfDeck.statisticsInWeek.get(i).dueCards));
                }
//                Collections.reverse(dueCard.getData());

                // Kiểm tra xem các danh sách dữ liệu có rỗng không
                if (newCard.getData().isEmpty() || learnedCard.getData().isEmpty() || dueCard.getData().isEmpty()) {
                    System.out.println("Dữ liệu rỗng!");
                } else {
                    // Thêm dữ liệu vào BarChart
                    StatisticBarChart.getData().addAll(newCard, learnedCard, dueCard);
                    System.out.println("flo");
                }
                //StatisticBarChart.getData().addAll(newCard, learnedCard, dueCard);
            }

            if (WeekOrMonth.getValue().equals("Month")) {
                XYChart.Series<String, Number> newCard = new XYChart.Series<>();
                newCard.setName("New Cards");
                for (int i = 0; i < 6 && i < (deck.StatOfDeck.statisticsInMonth.size()); i++) {
                    newCard.getData().add(new XYChart.Data<String, Number>(deck.StatOfDeck.statisticsInMonth.get(i).date, deck.StatOfDeck.statisticsInMonth.get(i).newCards));
                }
//                Collections.reverse(newCard.getData());

                XYChart.Series<String, Number> learnedCard = new XYChart.Series<>();
                learnedCard.setName("Learned Cards");
                for (int i = 0; i < 6 && i < (deck.StatOfDeck.statisticsInMonth.size()); i++) {
                    learnedCard.getData().add(new XYChart.Data<String, Number>(deck.StatOfDeck.statisticsInMonth.get(i).date, deck.StatOfDeck.statisticsInMonth.get(i).learnedCards));
                }
//                Collections.reverse(learnedCard.getData());

                XYChart.Series<String, Number> dueCard = new XYChart.Series<>();
                dueCard.setName("Due Cards");
                for (int i = 0; i < 6 && i < (deck.StatOfDeck.statisticsInMonth.size()); i++) {
                    dueCard.getData().add(new XYChart.Data<String, Number>(deck.StatOfDeck.statisticsInMonth.get(i).date, deck.StatOfDeck.statisticsInMonth.get(i).dueCards));
                }
//                Collections.reverse(dueCard.getData());

                // Kiểm tra xem các danh sách dữ liệu có rỗng không
                if (newCard.getData().isEmpty() || learnedCard.getData().isEmpty() || dueCard.getData().isEmpty()) {
                    System.out.println("Dữ liệu rỗng!");
                } else {
                    // Thêm dữ liệu vào BarChart
                    StatisticBarChart.getData().addAll(newCard, learnedCard, dueCard);
                    System.out.println("flo");
                }
                //StatisticBarChart.getData().addAll(newCard, learnedCard, dueCard);
            }
        }
        else {
            System.out.println("Deck or StatsOfDeck is null");
        }
    }
}

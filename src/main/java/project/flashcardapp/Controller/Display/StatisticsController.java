package project.flashcardapp.Controller.Display;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.StringConverter;
import project.flashcardapp.Model.Deck;
import project.flashcardapp.Model.DeckData;
import project.flashcardapp.Model.StatisticInTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        for (int i=0; i<6; i++) {
            for (Deck t : DeckData.decks) {
                if (t.StatOfDeck.statisticsInMonth.isEmpty()) continue;
                if (t.StatOfDeck.statisticsInMonth.size()<=i) continue;
                StatisticInTime temp = new StatisticInTime();
                temp = all.StatOfDeck.statisticsInMonth.get(i);
                all.StatOfDeck.statisticsInMonth.set(i, StatisticInTime.plusStatistic(t.StatOfDeck.statisticsInMonth.get(i), temp));
            }
            if (all.StatOfDeck.statisticsInMonth.get(i).date=="null") break;
        }
        for (int i=0; i<7; i++) {
            for (Deck t : DeckData.decks) {
                if (t.StatOfDeck.statisticsInWeek.isEmpty()) continue;
                if (t.StatOfDeck.statisticsInWeek.size()<=i) continue;
                StatisticInTime temp = new StatisticInTime();
                temp = all.StatOfDeck.statisticsInWeek.get(i);
                all.StatOfDeck.statisticsInWeek.set(i, StatisticInTime.plusStatistic(t.StatOfDeck.statisticsInWeek.get(i), temp));
            }
            if (all.StatOfDeck.statisticsInWeek.get(i).date=="null") break;
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
        // Tắt hoạt cảnh cho BarChart
        StatisticBarChart.setAnimated(false);
        StatisticBarChart.getData().clear(); // Xóa dữ liệu cũ

        //Trục Y chỉ hiện số nguyên
        NumberAxis yAxis = (NumberAxis) StatisticBarChart.getYAxis();
        yAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return String.format("%d", object.intValue());
            }

            @Override
            public Number fromString(String string) {
                return Integer.parseInt(string);
            }
        });

        if (deck != null && deck.StatOfDeck != null) {
            if (WeekOrMonth.getValue().equals("Week")) {
//                XYChart.Series<String, Number> newCard = new XYChart.Series<>();
//                newCard.setName("New Cards");
//                for (int i = 0; i < 7 && i < (deck.StatOfDeck.statisticsInWeek.size()); i++) {
//                    if (deck.StatOfDeck.statisticsInWeek.get(i).date=="null") break;
//                    XYChart.Data<String, Number> data = new XYChart.Data<>(deck.StatOfDeck.statisticsInWeek.get(i).date, deck.StatOfDeck.statisticsInWeek.get(i).newCards);
//                    newCard.getData().add(data);
//                    addLabelToData(data);
//                }
////                Collections.reverse(newCard.getData());
//
//                XYChart.Series<String, Number> learnedCard = new XYChart.Series<>();
//                learnedCard.setName("Learned Cards");
//                for (int i = 0; i < 7 && i < (deck.StatOfDeck.statisticsInWeek.size()); i++) {
//                    if (deck.StatOfDeck.statisticsInWeek.get(i).date=="null") break;
//                    XYChart.Data<String, Number> data = new XYChart.Data<>(deck.StatOfDeck.statisticsInWeek.get(i).date, deck.StatOfDeck.statisticsInWeek.get(i).learnedCards);
//                    learnedCard.getData().add(data);
//                    addLabelToData(data);
//                }
////                Collections.reverse(learnedCard.getData());
//
//                XYChart.Series<String, Number> dueCard = new XYChart.Series<>();
//                dueCard.setName("Due Cards");
//                for (int i = 0; i < 7 && i < (deck.StatOfDeck.statisticsInWeek.size()); i++) {
//                    if (deck.StatOfDeck.statisticsInWeek.get(i).date=="null") break;
//                    XYChart.Data<String, Number> data = new XYChart.Data<>(deck.StatOfDeck.statisticsInWeek.get(i).date, deck.StatOfDeck.statisticsInWeek.get(i).dueCards);
//                    dueCard.getData().add(data);
//                    addLabelToData(data);
//                }
////                Collections.reverse(dueCard.getData());
                XYChart.Series<String, Number> newCard = new XYChart.Series<>();
                newCard.setName("New Cards");
                List<XYChart.Data<String, Number>> newCardData = new ArrayList<>();
                for (int i = 0; i < 7 && i < deck.StatOfDeck.statisticsInWeek.size(); i++) {
                    if (deck.StatOfDeck.statisticsInWeek.get(i).date.equals("null")) break;
                    newCardData.add(new XYChart.Data<>(deck.StatOfDeck.statisticsInWeek.get(i).date, deck.StatOfDeck.statisticsInWeek.get(i).newCards));
                }
                Collections.reverse(newCardData);
                newCard.getData().addAll(newCardData);
                for (XYChart.Data<String, Number> data : newCard.getData()) {
                    addLabelToData(data);
                }

                XYChart.Series<String, Number> learnedCard = new XYChart.Series<>();
                learnedCard.setName("Learned Cards");
                List<XYChart.Data<String, Number>> learnedCardData = new ArrayList<>();
                for (int i = 0; i < 7 && i < deck.StatOfDeck.statisticsInWeek.size(); i++) {
                    if (deck.StatOfDeck.statisticsInWeek.get(i).date.equals("null")) break;
                    learnedCardData.add(new XYChart.Data<>(deck.StatOfDeck.statisticsInWeek.get(i).date, deck.StatOfDeck.statisticsInWeek.get(i).learnedCards));
                }
                Collections.reverse(learnedCardData);
                learnedCard.getData().addAll(learnedCardData);
                for (XYChart.Data<String, Number> data : learnedCard.getData()) {
                    addLabelToData(data);
                }

                XYChart.Series<String, Number> dueCard = new XYChart.Series<>();
                dueCard.setName("Due Cards");
                List<XYChart.Data<String, Number>> dueCardData = new ArrayList<>();
                for (int i = 0; i < 7 && i < deck.StatOfDeck.statisticsInWeek.size(); i++) {
                    if (deck.StatOfDeck.statisticsInWeek.get(i).date.equals("null")) break;
                    dueCardData.add(new XYChart.Data<>(deck.StatOfDeck.statisticsInWeek.get(i).date, deck.StatOfDeck.statisticsInWeek.get(i).dueCards));
                }
                Collections.reverse(dueCardData);
                dueCard.getData().addAll(dueCardData);
                for (XYChart.Data<String, Number> data : dueCard.getData()) {
                    addLabelToData(data);
                }

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
//                XYChart.Series<String, Number> newCard = new XYChart.Series<>();
//                newCard.setName("New Cards");
//                for (int i = 0; i < 6 && i < (deck.StatOfDeck.statisticsInMonth.size()); i++) {
//                    if (deck.StatOfDeck.statisticsInMonth.get(i).date=="null") break;
//                    XYChart.Data<String, Number> data = new XYChart.Data<>(deck.StatOfDeck.statisticsInMonth.get(i).date, deck.StatOfDeck.statisticsInMonth.get(i).newCards);
//                    newCard.getData().add(data);
//                    addLabelToData(data);
//                }
////                Collections.reverse(newCard.getData());
//
//                XYChart.Series<String, Number> learnedCard = new XYChart.Series<>();
//                learnedCard.setName("Learned Cards");
//                for (int i = 0; i < 6 && i < (deck.StatOfDeck.statisticsInMonth.size()); i++) {
//                    if (deck.StatOfDeck.statisticsInMonth.get(i).date=="null") break;
//                    XYChart.Data<String, Number> data = new XYChart.Data<>(deck.StatOfDeck.statisticsInMonth.get(i).date, deck.StatOfDeck.statisticsInMonth.get(i).learnedCards);
//                    learnedCard.getData().add(data);
//                    addLabelToData(data);
//                }
////                Collections.reverse(learnedCard.getData());
//
//                XYChart.Series<String, Number> dueCard = new XYChart.Series<>();
//                dueCard.setName("Due Cards");
//                for (int i = 0; i < 6 && i < (deck.StatOfDeck.statisticsInMonth.size()); i++) {
//                    if (deck.StatOfDeck.statisticsInMonth.get(i).date=="null") break;
//                    XYChart.Data<String, Number> data = new XYChart.Data<>(deck.StatOfDeck.statisticsInMonth.get(i).date, deck.StatOfDeck.statisticsInMonth.get(i).dueCards);
//                    dueCard.getData().add(data);
//                    addLabelToData(data);
//                }
////                Collections.reverse(dueCard.getData());

                XYChart.Series<String, Number> newCard = new XYChart.Series<>();
                newCard.setName("New Cards");
                List<XYChart.Data<String, Number>> newCardData = new ArrayList<>();
                for (int i = 0; i < 6 && i < deck.StatOfDeck.statisticsInMonth.size(); i++) {
                    if (deck.StatOfDeck.statisticsInMonth.get(i).date.equals("null")) break;
                    newCardData.add(new XYChart.Data<>(deck.StatOfDeck.statisticsInMonth.get(i).date, deck.StatOfDeck.statisticsInMonth.get(i).newCards));
                }
                Collections.reverse(newCardData);
                newCard.getData().addAll(newCardData);
                for (XYChart.Data<String, Number> data : newCard.getData()) {
                    addLabelToData(data);
                }

                XYChart.Series<String, Number> learnedCard = new XYChart.Series<>();
                learnedCard.setName("Learned Cards");
                List<XYChart.Data<String, Number>> learnedCardData = new ArrayList<>();
                for (int i = 0; i < 6 && i < deck.StatOfDeck.statisticsInMonth.size(); i++) {
                    if (deck.StatOfDeck.statisticsInMonth.get(i).date.equals("null")) break;
                    learnedCardData.add(new XYChart.Data<>(deck.StatOfDeck.statisticsInMonth.get(i).date, deck.StatOfDeck.statisticsInMonth.get(i).learnedCards));
                }
                Collections.reverse(learnedCardData);
                learnedCard.getData().addAll(learnedCardData);
                for (XYChart.Data<String, Number> data : learnedCard.getData()) {
                    addLabelToData(data);
                }

                XYChart.Series<String, Number> dueCard = new XYChart.Series<>();
                dueCard.setName("Due Cards");
                List<XYChart.Data<String, Number>> dueCardData = new ArrayList<>();
                for (int i = 0; i < 6 && i < deck.StatOfDeck.statisticsInMonth.size(); i++) {
                    if (deck.StatOfDeck.statisticsInMonth.get(i).date.equals("null")) break;
                    dueCardData.add(new XYChart.Data<>(deck.StatOfDeck.statisticsInMonth.get(i).date, deck.StatOfDeck.statisticsInMonth.get(i).dueCards));
                }
                Collections.reverse(dueCardData);
                dueCard.getData().addAll(dueCardData);
                for (XYChart.Data<String, Number> data : dueCard.getData()) {
                    addLabelToData(data);
                }

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

    //Hàm thêm label vào dữ liệu
    private void addLabelToData(XYChart.Data<String, Number> data) {
        Label label = new Label(data.getYValue().toString());
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(label);
        StackPane.setAlignment(label, Pos.TOP_CENTER);

        data.setNode(stackPane);
        data.nodeProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                stackPane.setTranslateY(-label.getHeight() - 5);
            }
        });
    }
}

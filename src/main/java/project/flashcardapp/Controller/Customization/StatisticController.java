package project.flashcardapp.Controller.Customization;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;
import project.flashcardapp.Model.Deck;
import project.flashcardapp.Model.DeckData;
import project.flashcardapp.Model.StatisticOfDeck;

import java.util.Collection;
import java.util.Collections;

public class StatisticController{
    private Deck deck;

    @FXML
    private ComboBox<String> WeekOrMonth;
    @FXML
    private ComboBox<Deck> WhichDeck;
    @FXML
    private BarChart<String, Number> StatisticBarChart;

    @FXML
    public void initialize(){
        ObservableList<String> WorM = FXCollections.observableArrayList("Week", "Month");
        WeekOrMonth.setItems(WorM);
        WeekOrMonth.getSelectionModel().selectFirst();

        ObservableList<Deck> chosenDeck = FXCollections.observableArrayList();
        //chosenDeck.add(Deck_ALL);
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

        CategoryAxis xAxis = new CategoryAxis(); xAxis.setLabel("Time");
        NumberAxis yAxis = new NumberAxis(); yAxis.setLabel("Count");
        StatisticBarChart = new BarChart<>(xAxis, yAxis);
        StatisticBarChart.setTitle("Statistic");

        if (WeekOrMonth.getValue().equals("Week")) {
            XYChart.Series<String, Number> newCard = new XYChart.Series<>();
            newCard.setName("New Cards");
            for (int i = 0; i < 7 && i < (deck.StatOfDeck.statisticsInWeek.size() - 1); i++) {
                newCard.getData().add(new XYChart.Data<String, Number>(deck.StatOfDeck.statisticsInWeek.get(i).date, deck.StatOfDeck.statisticsInWeek.get(i).newCards));
            }
            Collections.reverse(newCard.getData());

            XYChart.Series<String, Number> learnedCard = new XYChart.Series<>();
            newCard.setName("Learned Cards");
            for (int i = 0; i < 7 && i < (deck.StatOfDeck.statisticsInWeek.size() - 1); i++) {
                learnedCard.getData().add(new XYChart.Data<String, Number>(deck.StatOfDeck.statisticsInWeek.get(i).date, deck.StatOfDeck.statisticsInWeek.get(i).learnedCards));
            }
            Collections.reverse(learnedCard.getData());

            XYChart.Series<String, Number> dueCard = new XYChart.Series<>();
            learnedCard.setName("Due Cards");
            for (int i = 0; i < 7 && i < (deck.StatOfDeck.statisticsInWeek.size() - 1); i++) {
                dueCard.getData().add(new XYChart.Data<String, Number>(deck.StatOfDeck.statisticsInWeek.get(i).date, deck.StatOfDeck.statisticsInWeek.get(i).dueCards));
            }
            Collections.reverse(dueCard.getData());

            StatisticBarChart.getData().addAll(newCard, learnedCard, dueCard);
        }

        if (WeekOrMonth.getValue().equals("Month")) {
            XYChart.Series<String, Number> newCard = new XYChart.Series<>();
            newCard.setName("New Cards");
            for (int i = 0; i < 7 && i < (deck.StatOfDeck.statisticsInMonth.size() - 1); i++) {
                newCard.getData().add(new XYChart.Data<String, Number>(deck.StatOfDeck.statisticsInMonth.get(i).date, deck.StatOfDeck.statisticsInMonth.get(i).newCards));
            }
            Collections.reverse(newCard.getData());

            XYChart.Series<String, Number> learnedCard = new XYChart.Series<>();
            newCard.setName("Learned Cards");
            for (int i = 0; i < 7 && i < (deck.StatOfDeck.statisticsInMonth.size() - 1); i++) {
                learnedCard.getData().add(new XYChart.Data<String, Number>(deck.StatOfDeck.statisticsInMonth.get(i).date, deck.StatOfDeck.statisticsInMonth.get(i).learnedCards));
            }
            Collections.reverse(learnedCard.getData());

            XYChart.Series<String, Number> dueCard = new XYChart.Series<>();
            learnedCard.setName("Due Cards");
            for (int i = 0; i < 7 && i < (deck.StatOfDeck.statisticsInMonth.size() - 1); i++) {
                dueCard.getData().add(new XYChart.Data<String, Number>(deck.StatOfDeck.statisticsInMonth.get(i).date, deck.StatOfDeck.statisticsInMonth.get(i).dueCards));
            }
            Collections.reverse(dueCard.getData());

            StatisticBarChart.getData().addAll(newCard, learnedCard, dueCard);
        }
    }
}

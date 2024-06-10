package project.flashcardapp.Controller.Display;

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

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class StatisticController implements Initializable {
    private Deck deck;

    @FXML
    private ComboBox<String> WeekOrMonth;
    @FXML
    private ComboBox<Deck> WhichDeck;
    @FXML
    private BarChart<String, Number> StatisticBarChart;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Deck all = new Deck();
        try {
            all.setDeckName("All");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for(Deck t: DeckData.decks){
            int temp = all.getNewCards();
            temp += t.getNewCards();
            all.setNewCards(temp);
            temp = all.getLearnedCards();
            temp += t.getLearnedCards();
            all.setLearnedCards(temp);
            temp = all.getDueCards();
            temp += t.getDueCards();
            all.setDueCards(temp);
        }

        ObservableList<String> WorM = FXCollections.observableArrayList("Week", "Month");
        WeekOrMonth.setItems(WorM);
        WeekOrMonth.getSelectionModel().selectFirst();

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

        CategoryAxis xAxis = new CategoryAxis(); xAxis.setLabel("Time");
        NumberAxis yAxis = new NumberAxis(); yAxis.setLabel("Count");
        StatisticBarChart = new BarChart<>(xAxis, yAxis);
        StatisticBarChart.setTitle("Statistic");

        if (WeekOrMonth.getValue().equals("Week")) {
            XYChart.Series<String, Number> newCard = new XYChart.Series<>();
            newCard.setName("New Cards");
            for (int i = 0; i < 7 && i < (deck.StatOfDeck.statisticsInWeek.size()); i++) {
                newCard.getData().add(new XYChart.Data<String, Number>(deck.StatOfDeck.statisticsInWeek.get(i).date, deck.StatOfDeck.statisticsInWeek.get(i).newCards));
            }
            Collections.reverse(newCard.getData());

            XYChart.Series<String, Number> learnedCard = new XYChart.Series<>();
            newCard.setName("Learned Cards");
            for (int i = 0; i < 7 && i < (deck.StatOfDeck.statisticsInWeek.size()); i++) {
                learnedCard.getData().add(new XYChart.Data<String, Number>(deck.StatOfDeck.statisticsInWeek.get(i).date, deck.StatOfDeck.statisticsInWeek.get(i).learnedCards));
            }
            Collections.reverse(learnedCard.getData());

            XYChart.Series<String, Number> dueCard = new XYChart.Series<>();
            learnedCard.setName("Due Cards");
            for (int i = 0; i < 7 && i < (deck.StatOfDeck.statisticsInWeek.size()); i++) {
                dueCard.getData().add(new XYChart.Data<String, Number>(deck.StatOfDeck.statisticsInWeek.get(i).date, deck.StatOfDeck.statisticsInWeek.get(i).dueCards));
            }
            Collections.reverse(dueCard.getData());

            StatisticBarChart.getData().addAll(newCard, learnedCard, dueCard);
        }

        if (WeekOrMonth.getValue().equals("Month")) {
            XYChart.Series<String, Number> newCard = new XYChart.Series<>();
            newCard.setName("New Cards");
            for (int i = 0; i < 6 && i < (deck.StatOfDeck.statisticsInMonth.size()); i++) {
                newCard.getData().add(new XYChart.Data<String, Number>(deck.StatOfDeck.statisticsInMonth.get(i).date, deck.StatOfDeck.statisticsInMonth.get(i).newCards));
            }
            Collections.reverse(newCard.getData());

            XYChart.Series<String, Number> learnedCard = new XYChart.Series<>();
            newCard.setName("Learned Cards");
            for (int i = 0; i < 6 && i < (deck.StatOfDeck.statisticsInMonth.size()); i++) {
                learnedCard.getData().add(new XYChart.Data<String, Number>(deck.StatOfDeck.statisticsInMonth.get(i).date, deck.StatOfDeck.statisticsInMonth.get(i).learnedCards));
            }
            Collections.reverse(learnedCard.getData());

            XYChart.Series<String, Number> dueCard = new XYChart.Series<>();
            learnedCard.setName("Due Cards");
            for (int i = 0; i < 6 && i < (deck.StatOfDeck.statisticsInMonth.size()); i++) {
                dueCard.getData().add(new XYChart.Data<String, Number>(deck.StatOfDeck.statisticsInMonth.get(i).date, deck.StatOfDeck.statisticsInMonth.get(i).dueCards));
            }
            Collections.reverse(dueCard.getData());

            StatisticBarChart.getData().addAll(newCard, learnedCard, dueCard);
        }
    }
}

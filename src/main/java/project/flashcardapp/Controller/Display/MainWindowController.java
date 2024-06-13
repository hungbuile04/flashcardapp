package project.flashcardapp.Controller.Display;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import project.flashcardapp.Model.Card;
import project.flashcardapp.Model.Deck;
import project.flashcardapp.Model.DeckData;
import project.flashcardapp.Model.Selector;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    public Label cardStudied;
    private Deck deck;
    @FXML
    private Button addCardButton;

    @FXML
    private Button settingButton;


    @FXML
    private TableView<Deck> tableDeckView;


    @FXML
    private TableColumn<Deck, String> deckName;
    @FXML
    private TableColumn<Deck, Integer> dueCards;

    @FXML
    private TableColumn<Deck, Integer> learnedCards;

    @FXML
    private TableColumn<Deck, Integer> newCards;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deckName.setCellValueFactory(new PropertyValueFactory<>("deckName"));
        dueCards.setCellValueFactory(new PropertyValueFactory<>("dueCards"));
        learnedCards.setCellValueFactory(new PropertyValueFactory<>("learnedCards"));
        newCards.setCellValueFactory(new PropertyValueFactory<>("newCards"));
        tableDeckView.setItems(DeckData.getInstance().getDecks());
        for (Deck d : DeckData.decks) d.store();
        //ấn đúp vào 1 hàng thì chuyển sang cửa sổ tương ứng
        tableDeckView.setRowFactory(tv -> {
            TableRow<Deck> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 2) {
                    Deck clickedRow = row.getItem();
                    DeckData.deck=clickedRow;
                    showDetailScene(clickedRow);
                }
            });
            return row;
        });

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjust the pattern to match your date format
        DeckData.studiedCardsToday=0;
        for (Deck d : DeckData.decks) {
            for (Card card : d.getCards().getAll()) {
                Selector selector = card.getSelector();
                try {
                    Date deadlineDate = selector.getDeadlineAt();
                    LocalDate deadline = Instant.ofEpochMilli(deadlineDate.getTime())
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
                    int daysToSubtract = 0;
                    if(selector.getAnswerType()== Selector.AnswerType.CORRECT){
                        daysToSubtract=d.getEasyCard();
                    }else if(selector.getAnswerType()==Selector.AnswerType.MEDIUM){
                        daysToSubtract=d.getMediumCard();
                    }else if(selector.getAnswerType()==Selector.AnswerType.FAILURE){
                        daysToSubtract=d.getHardCard();
                    }
                    if (today.equals(deadline.minusDays(daysToSubtract))) {
                        DeckData.studiedCardsToday++;
                    }
                } catch (Exception e) {
                    e.printStackTrace(); // Handle parsing exception
                }
            }
        }
        cardStudied.setText(DeckData.studiedCardsToday + " card(s)");
    }
    //mở cửa sổ chọn chế độ học
    private void showDetailScene(Deck deck) {
        try {
            // Tải FXML cho scene chi tiết
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/flashcardapp/deckinfo.fxml"));
            Parent detailSceneRoot = loader.load();
            Scene detailScene = new Scene(detailSceneRoot);
            Stage stage = (Stage)tableDeckView.getScene().getWindow();
            stage.setResizable(false);
            stage.setTitle(deck.getDeckName());
            stage.setScene(detailScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Sự kiện add card
    @FXML
    void addCard(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/flashcardapp/add_card.fxml"));
        Parent addCardSceneRoot = loader.load();
        Scene addCardScene = new Scene(addCardSceneRoot);
        Stage stage = (Stage) tableDeckView.getScene().getWindow();
        stage.setResizable(false);
        stage.setTitle("Add Card");
        stage.setScene(addCardScene);
        stage.show();
    }

    //Mở cửa sổ Statistics
    public void statistic(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/flashcardapp/statistics_window.fxml"));
        Parent statisticSceneRoot = loader.load();
        Scene statisticScene = new Scene(statisticSceneRoot);

        Image icon = new Image("logoApp_transparent_final.png");

        Stage stage = new Stage();
        stage.getIcons().add(icon);
        stage.setTitle("Statistic");
        stage.setScene(statisticScene);
        stage.show();
    }

//    Open stage Settings - Tuan
    @FXML
    public void switchtoSettings (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/flashcardapp/settings.fxml"));
        Parent settingsSceneRoot = loader.load();
        Scene settingsScene = new Scene(settingsSceneRoot);
        Stage stage = new Stage();
        stage.setTitle("Settings");
        stage.setScene(settingsScene);
        stage.show();
    }
}
package project.flashcardapp.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.flashcardapp.Model.Deck;
import project.flashcardapp.Model.DeckData;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    private Deck deck;
    @FXML
    private Button addCardButton;

    @FXML
    private MenuButton setting_button;


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

    public void statistic(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/flashcardapp/statistic_window.fxml"));
        Parent statisticSceneRoot = loader.load();
        Scene statisticScene = new Scene(statisticSceneRoot);
        Stage stage = new Stage();
        stage.setTitle("Statistic");
        stage.setScene(statisticScene);
        stage.show();
    }
}

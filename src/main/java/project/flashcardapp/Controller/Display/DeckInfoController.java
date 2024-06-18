package project.flashcardapp.Controller.Display;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import project.flashcardapp.Controller.LearningMode.ReviewModeController;
import project.flashcardapp.Model.Deck;
import project.flashcardapp.Model.DeckData;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

// Điều khiển cửa sổ của chi tiết bộ thẻ
public class DeckInfoController implements Initializable {
    public Label newLabel;
    public Label learnedLabel;
    public Label dueLabel;
    public Button reviewModeBtn;
    public Button testModeBtn;
    public static Deck deck;
    public Label deckName;
    public Label noteLabel;

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deck = DeckData.deck;
        deck.getCards().getAll();
        updateDeckInfo();
        deck.store();
    }
    //Hiện thông tin số thẻ đã hoàn thành, chưa hoàn thành, mới
    private void updateDeckInfo() {
        deckName.setText(deck.getDeckName());
        noteLabel.setText(deck.getLabelDescription());
        learnedLabel.setText(toString(deck.getLearnedCards()));
        dueLabel.setText(toString(deck.getDueCards()));
        newLabel.setText(toString(deck.getNewCards()));
    }

    private String toString(int a) {
        return Integer.toString(a);
    }

    //Chọn chế độ ôn tap
    @FXML
    void selectReview(MouseEvent event) throws IOException {
        if(deck.getCards().getSize()==0){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("This deck is empty!");
            alert.getButtonTypes().setAll(ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                alert.close();
            }
            return;
        }
        Stage stage = (Stage) reviewModeBtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/project/flashcardapp/review_mode.fxml"));
        Scene scene = new Scene(fxmlLoader.load());//hàm load cần gọi trước để xác định đc controller
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    //Chọn chế đo kiem tra
    @FXML
    void selectTest(MouseEvent event) throws IOException {
        if(deck.getCards().getSize()==0){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("This deck is empty!");
            alert.getButtonTypes().setAll(ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                alert.close();
            }
            return;
        }
        Stage stage = (Stage) testModeBtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/project/flashcardapp/test_mode.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void customizeDeck(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/project/flashcardapp/customize_deck.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    // Xoá deck
    public void deleteDeck(MouseEvent mouseEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Do you want to delete this deck?");
        alert.setContentText("Click OK to delete and close, or Cancel to exit.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DeckData.getInstance().getDecks().remove(deck);
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/project/flashcardapp/main_window.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("Hello!");
            stage.show();;
        }

    }

    public void backToMainWindow(MouseEvent mouseEvent) throws IOException {
        DeckData.getInstance().storeDeck();
        DeckData.getInstance().loadDeck();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/project/flashcardapp/main_window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("EngHUST Flash Card App");
        stage.show();
    }
}
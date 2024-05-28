package project.flashcardapp.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import project.flashcardapp.Model.Deck;
import project.flashcardapp.Model.DeckData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

// Điều khiển cửa sổ của chi tiết bộ thẻ
public class DeckInfoController implements Initializable {
    public Label newCards;
    public Label completedCards;
    public Label dueCards;
    public Button selectReviewModeButton;
    public Button selectTestModeButton;
    public static Deck deck;

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deck = DeckData.deck;
        updateDeckInfo();
    }
    //Hiện thông tin số thẻ đã hoàn thành, chưa hoàn thành, mới
    private void updateDeckInfo() {
        completedCards.setText(toString(deck.getLearnedCards()));
        dueCards.setText(toString(deck.getDueCards()));
        newCards.setText(toString(deck.getNewCards()));
    }

    private String toString(int a) {
        return Integer.toString(a);
    }

    //Chọn chế độ ôn tap
    @FXML
    void selectReview(MouseEvent event) throws IOException {
        Stage stage = (Stage) selectReviewModeButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/project/flashcardapp/review_mode.fxml"));
        Scene scene = new Scene(fxmlLoader.load());//hàm load cần gọi trước để xác định đc controller
        stage.setScene(scene);
        stage.show();
    }

    //Chọn chế đo kiem tra
    @FXML
    void selectTest(MouseEvent event) throws IOException {
        Stage stage = (Stage) selectReviewModeButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/project/flashcardapp/test_mode.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void customizeDeck(MouseEvent mouseEvent) {
    }

    // Xoá deck
    public void deleteDeck(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Do you want to delete this deck?");
        alert.setContentText("Click OK to delete and close, or Cancel to exit.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DeckData.getInstance().getDecks().remove(deck);
            ((Stage) newCards.getScene().getWindow()).close();
        }

    }
}





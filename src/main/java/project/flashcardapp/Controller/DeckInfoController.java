package project.flashcardapp.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

public class DeckInfoController implements Initializable {
    public Label newCards;
    public Label completedCards;
    public Label dueCards;
    public Button selectReviewModeButton;
    public Button selectTestModeButton;
    private Deck deck;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deck = DeckData.deck;
        updateDeckInfo(deck);
    }
    private void updateDeckInfo(Deck deck) {
        completedCards.setText(toString(deck.getLearnedCards()));
        dueCards.setText(toString(deck.getDueCards()));
        newCards.setText(toString(deck.getNewCards()));

    }

    private String toString(int a) {
        return Integer.toString(a);
    }

    @FXML
    void selectReview(MouseEvent event) {
        System.out.println("data/" + deck.getDeckName()+".txt");
    }

    @FXML
    void selectTest(MouseEvent event) {
        System.out.println("Test mode selected");
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





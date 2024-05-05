package project.flashcardapp.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import project.flashcardapp.Model.Deck;
import project.flashcardapp.Model.DeckData;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
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
}





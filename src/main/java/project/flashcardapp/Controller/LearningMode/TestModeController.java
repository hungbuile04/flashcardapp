package project.flashcardapp.Controller.LearningMode;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import project.flashcardapp.Controller.DeckInfoController;
import project.flashcardapp.Model.Deck;

import java.net.URL;
import java.util.ResourceBundle;

public class TestModeController implements Initializable {
    private int currentIndex = 0;
    private Deck deck;
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    @FXML
    private TextField answerField;

    @FXML
    private Button nextCard;

    @FXML
    private Button previousCard;

    @FXML
    private Label questionLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.deck = DeckInfoController.deck;
    }

    @FXML
    void showNextCard(MouseEvent event) {

    }

    @FXML
    void showPreviousCard(MouseEvent event) {

    }

}

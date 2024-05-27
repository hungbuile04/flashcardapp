package project.flashcardapp.Controller;

import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import project.flashcardapp.Model.Deck;

import java.net.URL;
import java.util.ResourceBundle;

public class ReviewModeController implements Initializable {
    @FXML
    private Label questionLabel;
    @FXML
    private Label answerLabel;
    private int currentIndex = 0;
    private boolean showingQuestion = true;

    private Deck deck;
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    @FXML
    private StackPane CardPane;

    @FXML
    private Button nextCard;

    @FXML
    private Button previousCard;

    private void updateCard() {
        questionLabel.setText(deck.getCards().getCard(currentIndex).getQuestion());
        answerLabel.setText(deck.getCards().getCard(currentIndex).getAnswer());
        showingQuestion = true;
        questionLabel.setVisible(true);
        answerLabel.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.deck = DeckInfoController.deck;
        updateCard();
    }

    @FXML
    void showNextCard(MouseEvent event) {

    }

    @FXML
    void showPreviousCard(MouseEvent event) {

    }
    private void flipCard() {
        RotateTransition rt1 = new RotateTransition(Duration.millis(300), CardPane);
        rt1.setByAngle(90);
        rt1.setOnFinished(event -> {
            questionLabel.setVisible(!showingQuestion);
            answerLabel.setVisible(showingQuestion);
            showingQuestion = !showingQuestion;
            RotateTransition rt2 = new RotateTransition(Duration.millis(300), CardPane);
            rt2.setByAngle(90);
            rt2.play();
        });

        SequentialTransition seqT = new SequentialTransition(rt1);
        seqT.play();
    }

}

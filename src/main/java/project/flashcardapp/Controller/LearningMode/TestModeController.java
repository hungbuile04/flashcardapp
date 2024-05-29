package project.flashcardapp.Controller.LearningMode;

import javafx.event.ActionEvent;
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

//Chức năng kiểm tra
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

    @FXML
    private Label resultAlert;

    @FXML
    private Label showAnswer;

    @FXML
    private Button checkButton;

    @FXML
    private Button showAnswerButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.deck = DeckInfoController.deck;
        updateCard();
    }

    private void updateCard() {
        if (deck.getCards().getSize() == 0) {
            System.out.println("Card list is empty!");
            return;
        }

        questionLabel.setText(deck.getCards().getCard(currentIndex).getQuestion());
        questionLabel.setVisible(true);
        answerField.clear();
        resultAlert.setText("");
        showAnswer.setText("");
    }

    @FXML
    void showNextCard(MouseEvent event) {
        if (currentIndex < deck.getCards().getSize() - 1) {
            currentIndex++;
            updateCard();
        }
    }

    @FXML
    void showPreviousCard(MouseEvent event) {
        if (currentIndex > 0) {
            currentIndex--;
            updateCard();
        }
    }
    public void checkAnswer(ActionEvent event){
        String as = answerField.getText();
        if (as.equals(deck.getCards().getCard(currentIndex).getAnswer())) {
            resultAlert.setText("CORRECT");
            resultAlert.setVisible(true);
        }else{
            resultAlert.setText("WRONG");
            resultAlert.setVisible(true);
        }
    }

    public void showAnswer(MouseEvent event){
        showAnswer.setText(deck.getCards().getCard(currentIndex).getAnswer());
    }


}

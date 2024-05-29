package project.flashcardapp.Controller.LearningMode;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import project.flashcardapp.Controller.DeckInfoController;
import project.flashcardapp.Model.Deck;
import project.flashcardapp.Model.Selector;

import java.net.URL;
import java.util.ResourceBundle;

//Chức năng ôn tập
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

    StackPane front;
    StackPane back;
    private void updateCard() {
        if (deck.getCards().getSize() == 0) {
            System.out.println("Card list is empty!");
            return;
        }

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

    @FXML
    private void flipCard() {
        Timeline timeline = new Timeline();

        // Nửa đầu của lật: quay tới 90 độ
        KeyFrame kf1 = new KeyFrame(Duration.seconds(0.25),
                new KeyValue(CardPane.rotateProperty(), 90));

        // Tạo một khoảng dừng để thay đổi nội dung thẻ
        PauseTransition pause = new PauseTransition(Duration.seconds(0.25));
        pause.setOnFinished(event -> {
            if (showingQuestion) {
                questionLabel.setVisible(false);
                answerLabel.setVisible(true);
            } else {
                questionLabel.setVisible(true);
                answerLabel.setVisible(false);
            }
        });

        // Nửa sau của lật: quay từ 90 đến 180 độ
        KeyFrame kf2 = new KeyFrame(Duration.seconds(0.5),
                new KeyValue(CardPane.rotateProperty(), 180));

        timeline.getKeyFrames().addAll(kf1, kf2);

        timeline.setOnFinished(event -> {
            CardPane.setRotate(0);
            showingQuestion = !showingQuestion;
        });

        timeline.play();
        pause.playFromStart();
    }

    @FXML
    void isEasy(MouseEvent event) {
        deck.getCards().getCard(currentIndex).getSelector().update(Selector.AnswerType.CORRECT,deck.getEasyCard(), deck.getMediumCard(), deck.getHardCard());
        if (currentIndex < deck.getCards().getSize() - 1) {
            currentIndex++;
            updateCard();
        }
    }

    @FXML
    void isHard(MouseEvent event) {
        deck.getCards().getCard(currentIndex).getSelector().update(Selector.AnswerType.FAILURE,deck.getEasyCard(), deck.getMediumCard(), deck.getHardCard());
        if (currentIndex < deck.getCards().getSize() - 1) {
            currentIndex++;
            updateCard();
        }
    }

    @FXML
    void isMedium(MouseEvent event) {
        deck.getCards().getCard(currentIndex).getSelector().update(Selector.AnswerType.MEDIUM,deck.getEasyCard(), deck.getMediumCard(), deck.getHardCard());
        if (currentIndex < deck.getCards().getSize() - 1) {
            currentIndex++;
            updateCard();
        }
    }
}

package project.flashcardapp.Controller.LearningMode;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import project.flashcardapp.Controller.DeckInfoController;
import project.flashcardapp.Model.Deck;
import project.flashcardapp.Model.Selector;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ReviewModeController implements Initializable {
    public Label totalCard;
    public Label hardTime;
    public Label easyTime;
    public ProgressBar progressBar;
    public Label cardLearned;
    public Label deckName;
    @FXML
    private Label questionLabel;
    @FXML
    private Label answerLabel;
    @FXML
    private Button backBtn;

    private static int currentIndex = 0;
    private boolean showingQuestion = true;
    private Deck deck;
    public void setDeck(Deck deck) {
        this.deck = deck;
    }
    private Timeline flipForward, flipBackward;
    private boolean isFlipped = false;
    private final double FLIP_SECS = 0.3;

    @FXML
    private StackPane CardPane;

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

        if (isFlipped) {
            flipBackward.play();
            isFlipped = false;
        }

        // Cập nhật giá trị của ProgressBar
        double progress = (double) (currentIndex) / deck.getCards().getSize();
        progressBar.setProgress(progress);

        cardLearned.setText(Integer.toString(currentIndex));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.deck = DeckInfoController.deck;
        deckName.setText(deck.getDeckName());
        totalCard.setText("/"+ deck.getCards().getSize());
        updateCard();

        CardPane.setRotationAxis(Rotate.X_AXIS);
        CardPane.setAlignment(Pos.CENTER);
        Rotate backRot = new Rotate(180, Rotate.X_AXIS);
        answerLabel.getTransforms().add(backRot);

        answerLabel.setVisible(false);

        CardPane.layoutBoundsProperty().addListener((obs, oldBounds, newBounds) -> {
            double centerX = newBounds.getWidth() / 2;
            double centerY = newBounds.getHeight() / 2;
            answerLabel.setLayoutX(centerX - questionLabel.getWidth() / 2);
            answerLabel.setLayoutY(centerY - questionLabel.getHeight() / 2);
            questionLabel.setLayoutX(centerX - questionLabel.getWidth() / 2);
            questionLabel.setLayoutY(centerY - questionLabel.getHeight() / 2);
            answerLabel.setTranslateY(18);
            backRot.setPivotX(questionLabel.getWidth() / 2);
            backRot.setPivotY(questionLabel.getHeight() / 2);
        });

        flipForward = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(CardPane.rotateProperty(), 0d)),
                new KeyFrame(
                        Duration.seconds(FLIP_SECS / 2),
                        t -> {
                            answerLabel.setVisible(true);
                            questionLabel.setVisible(false);
                            answerLabel.toFront();
                            CardPane.setStyle("-fx-background-color: #AFEEEE");
                        },
                        new KeyValue(CardPane.rotateProperty(), 90d)),
                new KeyFrame(
                        Duration.seconds(FLIP_SECS),
                        new KeyValue(CardPane.rotateProperty(), 180d)));

        flipBackward = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(CardPane.rotateProperty(), 180d)),
                new KeyFrame(
                        Duration.seconds(FLIP_SECS / 2),
                        t -> {
                            answerLabel.setVisible(false);
                            questionLabel.setVisible(true);
                            questionLabel.toFront();
                            CardPane.setStyle("-fx-background-color: #FFFFFF");
                        },
                        new KeyValue(CardPane.rotateProperty(), 90d)),
                new KeyFrame(
                        Duration.seconds(FLIP_SECS),
                        new KeyValue(CardPane.rotateProperty(), 0d)));
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
        if (isFlipped) {
            flipBackward.play();
            System.out.println("flipped");
        } else {
            flipForward.play();
            System.out.println("unflipped");
        }
        isFlipped = !isFlipped;
    }

    @FXML
    void isEasy(MouseEvent event) throws IOException {
        deck.getCards().getCard(currentIndex).getSelector().update(Selector.AnswerType.CORRECT,deck.getEasyCard(), deck.getMediumCard(), deck.getHardCard());
        if (currentIndex < deck.getCards().getSize() - 1) {
            currentIndex++;
            updateCard();
        }
        else{
            goToResult();
            currentIndex = 0;
        }
    }

    @FXML
    void isHard(MouseEvent event) throws IOException {
        deck.getCards().getCard(currentIndex).getSelector().update(Selector.AnswerType.FAILURE,deck.getEasyCard(), deck.getMediumCard(), deck.getHardCard());
        if (currentIndex < deck.getCards().getSize() - 1) {
            currentIndex++;
            updateCard();
        }
        else{
            goToResult();
            currentIndex = 0;
        }
    }

    @FXML
    void isMedium(MouseEvent event) throws IOException {
        deck.getCards().getCard(currentIndex).getSelector().update(Selector.AnswerType.MEDIUM,deck.getEasyCard(), deck.getMediumCard(), deck.getHardCard());
        if (currentIndex < deck.getCards().getSize() - 1) {
            currentIndex++;
            updateCard();
        }
        else{
            goToResult();
            currentIndex = 0;
        }
    }

    public void backToDeckInfoWindow(MouseEvent mouseEvent) throws Exception {
        //deck.store();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Do you want to stop reviewing?");
        alert.setContentText("Click OK to confirm, or Cancel to continue.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/flashcardapp/deckinfo.fxml"));
            Parent detailSceneRoot = loader.load();
            Scene detailScene = new Scene(detailSceneRoot);
            Stage stage = (Stage)CardPane.getScene().getWindow();
            stage.setResizable(false);
            stage.setTitle(deck.getDeckName());
            stage.setScene(detailScene);
            stage.show();
        }
    }

    void goToResult() throws IOException {
        Stage stage = (Stage) answerLabel.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/project/flashcardapp/result_review_mode.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void detailsCard(MouseEvent mouseEvent) {

    }
}

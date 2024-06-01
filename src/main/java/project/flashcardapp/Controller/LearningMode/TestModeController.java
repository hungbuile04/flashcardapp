package project.flashcardapp.Controller.LearningMode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import project.flashcardapp.Controller.DeckInfoController;
import project.flashcardapp.Model.Deck;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

//Chức năng kiểm tra
public class  TestModeController implements Initializable {
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
        deck.randomCard(deck.getCards());
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
    void showNextCard(MouseEvent event) throws IOException {
        if (currentIndex <= deck.getCards().getSize() - 1) {
            currentIndex++;
            if(currentIndex <= deck.getCards().getSize() - 1){
                updateCard();
            }
        }
        if (currentIndex == deck.getCards().getSize() -1) {
            nextCard.setText("Show Result Deck");

        }
        if(currentIndex == deck.getCards().getSize()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/flashcardapp/result_test_mode.fxml"));
            Parent addCardSceneRoot = loader.load();
            Scene addCardScene = new Scene(addCardSceneRoot);
            Stage stage = (Stage) answerField.getScene().getWindow();
            stage.setResizable(false);
            //stage.setTitle("RESULT DECK");
            stage.setScene(addCardScene);
            stage.show();
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


    public void backToDeckInfoWindow(MouseEvent mouseEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Do you want to exit?");
        alert.setContentText("Click OK to confirm, or Cancel to continue.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/flashcardapp/deckinfo.fxml"));
            Parent detailSceneRoot = loader.load();
            Scene detailScene = new Scene(detailSceneRoot);
            Stage stage = (Stage)answerField.getScene().getWindow();
            stage.setResizable(false);
            stage.setTitle(deck.getDeckName());
            stage.setScene(detailScene);
            stage.show();
        }
    }
}

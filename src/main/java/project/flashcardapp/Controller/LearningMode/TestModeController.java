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
import project.flashcardapp.Controller.Display.DeckInfoController;
import project.flashcardapp.Model.Deck;
import project.flashcardapp.Model.ResultDeck;
import project.flashcardapp.Model.Selector;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

//Chức năng kiểm tra
public class  TestModeController implements Initializable {
    public ProgressBar progressBar;
    public Label deckName;
    private int currentIndex = 0;
    private boolean[] flag = new boolean[1000];
    private boolean[] submitflag = new boolean[1000];
    private Deck deck;
    public static List<ResultDeck> result = new ArrayList<>();

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
    private Button checkButton;

    @FXML
    private Button backtoDeck ;

    @FXML
    private Button showResultDeck;

    @FXML
    private Label cardLearned;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.deck = DeckInfoController.deck;
        deckName.setText(deck.getDeckName());
        deck.randomCard(deck.getCards());
        for(int i = 0 ; i < deck.getCards().getSize(); i++){
            result.add( new ResultDeck(deck.getCards().getCard(i).getQuestion(),deck.getCards().getCard(i).getAnswer(),"","incorrect"));
        }
        cardLearned.setStyle("-fx-background-color: white");
        updateCard();
    }

    private void updateCard() {
        if (deck.getCards().getSize() == 0) {
            System.out.println("Card list is empty!");
            return;
        }

        questionLabel.setText(deck.getCards().getCard(currentIndex).getQuestion());

        cardLearned.setText("Câu hỏi " + (currentIndex + 1) + "/" + deck.getCards().getSize());
        flag[currentIndex] = true;
        questionLabel.setVisible(true);
        // Cập nhật giá trị của ProgressBar
        double progress = (double) (currentIndex + 1) / deck.getCards().getSize();
        progressBar.setProgress(progress);
    }

    @FXML
    void showNextCard(MouseEvent event) throws IOException {
        if(currentIndex < deck.getCards().getSize() - 1 ) {
            currentIndex++;
            if(!submitflag[currentIndex+1] ) {
                cardLearned.setStyle("-fx-background-color: white");
            }else{
                cardLearned.setStyle("-fx-background-color: #AFEEEE");
            }
            if(!flag[currentIndex]) {
                answerField.clear();
            }else{
                answerField.setText(result.get(currentIndex).getYouranswer());
            }
            updateCard();
        }
    }

    @FXML
    void showPreviousCard(MouseEvent event) {
        if (currentIndex > 0) {
            currentIndex--;
            if(submitflag[currentIndex+1]) {
                cardLearned.setStyle("-fx-background-color: #AFEEEE");
            }else{
                cardLearned.setStyle("-fx-background-color: white");
            }
            updateCard();
            answerField.setText(result.get(currentIndex).getYouranswer());
        }
    }

    @FXML
    public void checkAnswer(ActionEvent event) throws IOException {
        if(!submitflag[currentIndex]) {
            cardLearned.setStyle("-fx-background-color: #AFEEEE");
            submitflag[currentIndex] = true;
        }
        String as = answerField.getText();
        String lowerCase = as.toLowerCase();
        String lowerCaseAnswer = deck.getCards().getCard(currentIndex).getAnswer().toLowerCase();

        if ( lowerCase.equals(lowerCaseAnswer)) {
            result.set(currentIndex, new ResultDeck(deck.getCards().getCard(currentIndex).getQuestion(),deck.getCards().getCard(currentIndex).getAnswer(), as,"correct"));
        }else{
            result.set(currentIndex, new ResultDeck(deck.getCards().getCard(currentIndex).getQuestion(),deck.getCards().getCard(currentIndex).getAnswer(), as,"incorrect"));
            deck.getCards().getCard(currentIndex).getSelector().update(Selector.AnswerType.FAILURE,deck.getEasyCard(), deck.getMediumCard(), deck.getHardCard());
        }
    }

    @FXML
    public void backToDeckInfoWindow(MouseEvent mouseEvent) throws IOException {
        System.out.println("back");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Do you want to exit?");
        alert.setContentText("Click OK to confirm, or Cancel to continue.");

        Optional<ButtonType> resultcheck = alert.showAndWait();
        if (resultcheck.isPresent() && resultcheck.get() == ButtonType.OK) {
            result.clear();
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

    @FXML
    void movetoResultPage(MouseEvent event) throws IOException {
        int cnt = 0;
        for (int i = 0 ; i < deck.getCards().getSize(); i++){
            if(!submitflag[i]) cnt++;
        }
        if(cnt > 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Show Result");
            alert.setHeaderText("You have " + cnt + " incompleted question. Do you want to show your result?");
            alert.setContentText("Click OK to confirm, or Cancel to continue.");
            Optional<ButtonType> check = alert.showAndWait();
            if (check.isPresent() && check.get() == ButtonType.OK) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/flashcardapp/result_test_mode.fxml"));
                Parent addCardSceneRoot = loader.load();
                Scene addCardScene = new Scene(addCardSceneRoot);
                Stage stage = (Stage)answerField.getScene().getWindow();
                stage.setScene(addCardScene);
                stage.show();
            }
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/flashcardapp/result_test_mode.fxml"));
            Parent addCardSceneRoot = loader.load();
            Scene addCardScene = new Scene(addCardSceneRoot);
            Stage stage = (Stage)answerField.getScene().getWindow();
            stage.setScene(addCardScene);
            stage.show();
        }
    }
}
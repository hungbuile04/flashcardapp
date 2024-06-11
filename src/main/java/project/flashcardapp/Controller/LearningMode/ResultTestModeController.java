package project.flashcardapp.Controller.LearningMode;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import project.flashcardapp.Controller.Display.DeckInfoController;
import project.flashcardapp.Model.Deck;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;
import project.flashcardapp.Model.ResultDeck;

import static java.lang.String.valueOf;
import static project.flashcardapp.Controller.LearningMode.TestModeController.result;

public class ResultTestModeController implements Initializable {
    @FXML
    private Button backtoDeckButton;

    @FXML
    private Button showed;

    @FXML
    private Label correctAnswer;

    @FXML
    private Label totalQuestion;

    @FXML
    private TableView <ResultDeck> resultTable;

    @FXML
    private TableColumn<ResultDeck, String> questionCol;

    @FXML
    private TableColumn<ResultDeck, String> answerCol;

    @FXML
    private TableColumn<ResultDeck, String> youranswerCol;

    @FXML
    private TableColumn<ResultDeck, String> yourresultCol;

    private Deck deck;
    public List <ResultDeck> resultDecks;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.deck = DeckInfoController.deck;
        totalQuestion.setText(valueOf(deck.getCards().getSize()));
        int count = 0;
        for(ResultDeck k : result) {
            if(k.getYourresult().equals("correct")){
                count ++ ;
            }
        }
        correctAnswer.setText(valueOf(count));
        questionCol.setCellValueFactory(new PropertyValueFactory<>("question"));
        answerCol.setCellValueFactory(new PropertyValueFactory<>("answer"));
        youranswerCol.setCellValueFactory(new PropertyValueFactory<>("youranswer"));
        yourresultCol.setCellValueFactory(new PropertyValueFactory<>("yourresult"));
        ObservableList<ResultDeck> std = FXCollections.observableList(result);
        resultTable.setItems(std);
    }

    @FXML
    void backToDeck(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/flashcardapp/deckinfo.fxml"));
        Parent addCardSceneRoot = loader.load();
        Scene addCardScene = new Scene(addCardSceneRoot);
        Stage stage = (Stage) resultTable.getScene().getWindow();
        stage.setScene(addCardScene);
        stage.setScene(addCardScene);
        stage.show();
        result.clear();
    }

}
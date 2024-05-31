package project.flashcardapp.Controller.LearningMode;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import project.flashcardapp.Controller.DeckInfoController;
import project.flashcardapp.Model.Deck;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;

public class ResultTestModeController {
    @FXML
    private Button backtoDeckButton;

    private Deck deck;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.deck = DeckInfoController.deck;
    }

    @FXML
    void backToDeck(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/flashcardapp/deckinfo.fxml"));
        Parent addCardSceneRoot = loader.load();
        Scene addCardScene = new Scene(addCardSceneRoot);
        Stage stage = new Stage();
        stage.setTitle("RESULT DECK");
        stage.setScene(addCardScene);
        stage.show();
    }

}

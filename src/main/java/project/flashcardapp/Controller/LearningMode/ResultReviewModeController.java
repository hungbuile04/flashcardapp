package project.flashcardapp.Controller.LearningMode;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import project.flashcardapp.Model.Deck;
import project.flashcardapp.Model.DeckData;

import java.io.IOException;

public class ResultReviewModeController {

    @FXML
    private Button backButton;

    @FXML
    private Label resultLabel;


    @FXML
    void backButtonClicked(MouseEvent event) throws IOException {
        DeckData.deck.counting();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/flashcardapp/deckinfo.fxml"));
        Parent detailSceneRoot = loader.load();
        Scene detailScene = new Scene(detailSceneRoot);
        Stage stage = (Stage)backButton.getScene().getWindow(); //Lấy sân khấu hiện tại bằng cách tham chiếu đến thể hiện đang có của cảnh đó
        stage.setScene(detailScene);
        stage.show();
    }

}
package project.flashcardapp.Controller.Customization;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import project.flashcardapp.Controller.DeckInfoController;
import project.flashcardapp.Model.Deck;

import java.net.URL;
import java.util.ResourceBundle;

//Tuỳ chỉnh thuộc tính của bộ thẻ
public class CustomizeDeckController implements Initializable {
    private Deck deck;

    @FXML
    private TextField deckLabelField;

    @FXML
    private TextField deckNameField;

    @FXML
    private TextField easyCardField;

    @FXML
    private TextField hardCardField;

    @FXML
    private TextField mediumCardField;

    @FXML
    void cancelButton(MouseEvent event) {

    }

    @FXML
    void saveButton(MouseEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
          this.deck= DeckInfoController.deck;
          deckNameField.setText(deck.getDeckName());
          deckLabelField.setText(deck.getLabelDescription());
          easyCardField.setText(Integer.toString(deck.getEasyCard()));
          mediumCardField.setText(Integer.toString(deck.getMediumCard()));
          hardCardField.setText(Integer.toString(deck.getHardCard()));
    }
}

package project.flashcardapp.Controller.Customization;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import project.flashcardapp.Model.Deck;
import project.flashcardapp.Model.DeckData;

public class AddDeckController {
    @FXML
    public TextField nameField;
    @FXML
    public TextArea labelField;

    public Deck processResults() { //Phương thưc này giúp người dùng khi nhập thông tin
                                   // nó sẽ tạo đối tượng Deck mới rồi lưu
         String nameDeck = nameField.getText().trim();
         String labelDeck = labelField.getText().trim();
         Deck newDeck = new Deck(nameDeck, labelDeck);
         DeckData.getInstance().getDecks().add(newDeck);
         return newDeck;
    }
}

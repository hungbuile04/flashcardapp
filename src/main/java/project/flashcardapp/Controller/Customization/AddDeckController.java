package project.flashcardapp.Controller.Customization;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import project.flashcardapp.Model.Deck;
import project.flashcardapp.Model.DeckData;

//Tạo bộ thẻ mới
public class AddDeckController {
    @FXML
    public TextField nameField;
    @FXML
    public TextArea labelField;

    public Deck processResults() { //Phương thưc này giúp người dùng khi nhập thông tin
                                   // nó sẽ tạo đối tượng Deck mới rồi lưu
         String nameDeck = nameField.getText().trim();
         String labelDeck = labelField.getText().trim();
        if (nameDeck == null) {
//            Exception e = new Exception("Category's name can't be blank") {
//                // Khối khởi tạo của anonymous class
//                {
//                    System.out.println("Exception: " + getMessage());
//                }
//            };
//            e.printStackTrace();
//            throw e;
        }
        if (nameDeck.length() > 20) {
//            Exception e = new Exception("Category's name has to be up to 15 characters") {
//                // Khối khởi tạo của anonymous class
//                {
//                    System.out.println("Exception: " + getMessage());
//                }
//            };
//            e.printStackTrace();
//            throw e;
        }
        if (nameDeck.length() < 2) {
//            Exception e = new Exception("Category's name has to have at least 2 characters") {
//                // Khối khởi tạo của anonymous class
//                {
//                    System.out.println("Exception: " + getMessage());
//                }
//            };
        }
         Deck newDeck = new Deck(nameDeck, labelDeck);
         DeckData.getInstance().getDecks().add(newDeck);
         return newDeck;
    }
}

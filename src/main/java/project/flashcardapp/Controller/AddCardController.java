package project.flashcardapp.Controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import project.flashcardapp.Model.Card;
import project.flashcardapp.Model.Deck;
import project.flashcardapp.Model.DeckData;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddCardController implements Initializable {
    @FXML
    private ComboBox<Deck> chooseDeck;

    @FXML
    private TableView<Card> cardTable;

    @FXML
    private TableColumn<Card, String> back;

    @FXML
    private TableColumn<Card, String> front;

    @FXML
    private TextArea answerField;

    @FXML
    private TextArea questionField;

    private Deck deck;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ready:"+DeckData.decks.size());
        chooseDeck.setItems(DeckData.decks);
//        chooseDeck.setCellFactory(lv -> new ListCell<Deck>() {
//            @Override
//            protected void updateItem(Deck deck, boolean empty) {
//                super.updateItem(deck, empty);
//                setText(empty ? null : deck.getDeckName());
//            }
//        }); Dùng để hCó phương thức toString trong Deck rồi nên thôi:D
        chooseDeck.setButtonCell(new ListCell<Deck>() {
            @Override
            protected void updateItem(Deck deck, boolean empty) {
                super.updateItem(deck, empty);
                setText(empty ? null : deck.getDeckName()); // Hiển thị tên của deck khi ComboBox đóng lại
            }
        });

        front.setCellValueFactory(new PropertyValueFactory<>("question"));
        back.setCellValueFactory(new PropertyValueFactory<>("answer"));

        chooseDeck.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                deck = newVal;
                cardTable.setItems(FXCollections.observableArrayList(newVal.getCards().getAll()));
//                cardTable.getSelectionModel().selectedItemProperty().addListener((obs1, oldSelection, newSelection) -> {
//                    if (newSelection != null) {
//                        questionField.setText(newSelection.getQuestion());
//                        answerField.setText(newSelection.getAnswer());
//                    } else {
//                        questionField.clear();
//                        answerField.clear();
//                    }
//                });
            }
        });
    }

    public void saveNewCard(MouseEvent mouseEvent) {
        String frontContent = questionField.getText().trim();
        String backContent = answerField.getText().trim();
        // Kiểm tra để đảm bảo nội dung không trống
        if (!frontContent.isEmpty() && !backContent.isEmpty()) {
            Card newCard = new Card(frontContent, backContent);
            deck.getCards().add(newCard);
            cardTable.getItems().add(newCard); // Thêm card mới vào TableView
            cardTable.getSelectionModel().select(newCard); // Tùy chọn: Chọn card mới thêm
            // Làm sạch TextArea sau khi lưu
            questionField.clear();
            answerField.clear();
        } else {
            // Hiển thị thông báo nếu không có nội dung đầy đủ
            showAlert("Thông tin không đầy đủ", "Vui lòng nhập đầy đủ mặt trước và mặt sau của card.");
        }
    }
    private void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void deleteCard(MouseEvent mouseEvent) {
        Card selectedCard = cardTable.getSelectionModel().getSelectedItem();
        // Kiểm tra xem có card nào được chọn hay không
        if (selectedCard != null) {
            // Tạo và cấu hình Alert
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận Xóa");
            alert.setHeaderText("Bạn có chắc chắn muốn xóa card này?");
            alert.setContentText("Hành động này không thể hoàn tác!");

            // Hiển thị Alert và chờ phản hồi người dùng
            Optional<ButtonType> response = alert.showAndWait();

            // Kiểm tra xem người dùng có nhấn OK không
            if (response.isPresent() && response.get() == ButtonType.OK) {
                deck.getCards().remove(selectedCard);
                cardTable.getItems().remove(selectedCard); // Xóa card khỏi danh sách
            }
        }
    }

    public void detailCard(MouseEvent mouseEvent) {
    }
}


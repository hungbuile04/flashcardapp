package project.flashcardapp.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.flashcardapp.Model.Card;
import project.flashcardapp.Model.Deck;
import project.flashcardapp.Model.DeckData;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddCardController implements Initializable {
    public AnchorPane addCardPane;
    @FXML
    private ComboBox<Deck> chooseDeck;

    @FXML
    private TableView<Card> cardTable = new TableView<>();

    @FXML
    private TableColumn<Card, String> back;

    @FXML
    private TableColumn<Card, String> front;

    @FXML
    private TextArea answerField;

    @FXML
    private TextArea questionField;

    private Deck deck;
    public static Card selectedCard;
    public ObservableList<Card> cards;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ready:"+DeckData.decks.size());
        chooseDeck.setPromptText("Choose a deck");
        chooseDeck.setItems(DeckData.decks);
//        chooseDeck.setCellFactory(lv -> new ListCell<Deck>() {
//            @Override
//            protected void updateItem(Deck deck, boolean empty) {
//                super.updateItem(deck, empty);
//                setText(empty ? null : deck.getDeckName());
//            }
//        }); Dùng để tuỳ chỉnh cách hiển thị các thanh chọn. Có phương thức toString để thể hiện name trong Deck rồi nên thôi.
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
                cards = FXCollections.observableArrayList(newVal.getCards().getAll());
                cardTable.setItems(cards);
            }
        });
    }

    public void createNewDeck(MouseEvent mouseEvent) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(addCardPane.getScene().getWindow());
        dialog.setTitle("Add new TodoItem");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/project/flashcardapp/add_deck.fxml")); //fxmlLoader.setLocation(): Phương thức này được sử dụng để đặt vị trí tệp FXML cho FXMLLoader. Khi bạn gọi phương thức load(), nó sẽ sử dụng vị trí này để tải tệp FXML.
        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch(IOException e){
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }
       dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
       dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
       Optional<ButtonType> result = dialog.showAndWait();
       if(result.isPresent() && result.get() == ButtonType.OK){

       }else {
            System.out.println("Cancel pressed");
       }
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
            showAlert("Infomation is not enough.", "Please try again.");
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
            alert.setTitle("Confirm delete");
            alert.setHeaderText("Are you sure to delete this card?");
            alert.setContentText("This action couldn't be undone!");
            // Hiển thị Alert và chờ phản hồi người dùng
            Optional<ButtonType> response = alert.showAndWait();
            // Kiểm tra xem người dùng có nhấn OK không
            if (response.isPresent() && response.get() == ButtonType.OK) {
                deck.getCards().remove(selectedCard);
                cardTable.getItems().remove(selectedCard); // Xóa card khỏi danh sách
            }
        }
    }

    public void detailCard(MouseEvent actionEvent) throws IOException {
        selectedCard = cardTable.getSelectionModel().getSelectedItem();
        if (selectedCard != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/flashcardapp/details_card.fxml"));
            Parent detailsCardSceneRoot = loader.load();
            Scene detailsCardScene = new Scene(detailsCardSceneRoot);
            Stage stage = new Stage();
            stage.setTitle("Details");
            stage.setScene(detailsCardScene);
            DetailsCardController detailsCardController = loader.getController();
            detailsCardController.setAddCardController(this);// gán thể hiện hiện tại của addcardcontroller cho detailscardcontroller
            stage.show();
        }
    }
    public void refreshCard(){
        cardTable.refresh(); //cập nhật lại tablecard khi thẻ có sự thay đổi
    }
}


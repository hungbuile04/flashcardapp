package project.flashcardapp.Controller.Customization;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

//Cửa sổ thêm nội dung gồm các chức năng thêm thẻ, tạo bộ thẻ mới, mở cửa sổ chi tiết thẻ
public class AddCardController implements Initializable {
    public AnchorPane addCardPane;
    @FXML
    private ComboBox<Deck> chooseDeck;

    @FXML
    private TableView<Card> cardTable = new TableView<>();
    public TableView<Card> getCardTable() {
        return cardTable;
    }

    @FXML
    private TableColumn<Card, String> back;

    @FXML
    private TableColumn<Card, String> front;

    @FXML
    private TableColumn<Card, Integer> indexColumn; // Khai báo cột số thứ tự

    @FXML
    private TextArea frontField;

    @FXML
    private TextArea backField;

    private Deck deck;
    public Deck getDeck() {
        return deck;
    }

    public static Card selectedCard;
    public ObservableList<Card> cards;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ready:"+DeckData.decks.size());
        chooseDeck.setPromptText("Choose a deck");
        chooseDeck.setItems(DeckData.decks);
        chooseDeck.setButtonCell(new ListCell<Deck>() {
            @Override
            protected void updateItem(Deck deck, boolean empty) {
                super.updateItem(deck, empty);
                setText(empty ? null : deck.getDeckName()); // Hiển thị tên của deck khi ComboBox đóng lại
            }
        });
        front.setCellValueFactory(new PropertyValueFactory<>("question"));
        back.setCellValueFactory(new PropertyValueFactory<>("answer"));
        // Thiết lập giá trị cho cột số thứ tự
        indexColumn.setCellValueFactory(column ->
                new ReadOnlyObjectWrapper<>(cardTable.getItems().indexOf(column.getValue()) + 1)
        );
        indexColumn.setSortable(false);

        chooseDeck.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                deck = newVal;
                cards = FXCollections.observableArrayList(newVal.getCards().getAll());
                cardTable.setItems(cards);
            }
        });
    }

    //Mở cửa sổ dialog thêm bộ thẻ mới
    public void createNewDeck(MouseEvent mouseEvent) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(addCardPane.getScene().getWindow());
        dialog.setTitle("Add new deck");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/project/flashcardapp/add_deck.fxml")); //fxmlLoader.setLocation(): Phương thức này được sử dụng để đặt vị trí tệp FXML cho FXMLLoader. Khi bạn gọi phương thức load(), nó sẽ sử dụng vị trí này để tải tệp FXML.
        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch(IOException e){
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }
        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(okButtonType);
        // Lấy nút OK từ dialog
        Button okButton = (Button) dialog.getDialogPane().lookupButton(okButtonType);
        AddDeckController controller = fxmlLoader.getController();
        // Thêm trình xử lý sự kiện cho nút OK
        okButton.addEventFilter(ActionEvent.ACTION, event -> {
            String nameDeck = controller.nameField.getText().trim();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            if (nameDeck.isEmpty()) {
                alert.setContentText("Deck's name can't be blank!");
                alert.showAndWait();
                event.consume(); // Ngăn dialog đóng
            } else if (nameDeck.length() > 20) {
                alert.setContentText("Deck's name has to be up to 20 characters");
                alert.showAndWait();
                event.consume(); // Ngăn dialog đóng
            } else if (nameDeck.length() < 2) {
                alert.setContentText("Deck's name has to have at least 2 characters");
                alert.showAndWait();
                event.consume(); // Ngăn dialog đóng
            }
        });
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == okButtonType) {
            Deck newDeck = controller.processResults();
            chooseDeck.setItems(FXCollections.observableArrayList(DeckData.getInstance().getDecks()));
            chooseDeck.getSelectionModel().select(newDeck);
        } else {
            System.out.println("Cancel pressed");
        }
    }

    public void saveNewCard(MouseEvent mouseEvent) {
        String frontContent = frontField.getText().trim();
        String backContent = backField.getText().trim();
        // Kiểm tra để đảm bảo nội dung không trống
        if (!frontContent.isEmpty() && !backContent.isEmpty()) {
            Card newCard = new Card(frontContent, backContent);
            deck.getCards().add(newCard);
            cardTable.getItems().add(newCard); // Thêm card mới vào TableView
            cardTable.getSelectionModel().select(newCard); // Tùy chọn: Chọn card mới thêm
            // Làm sạch TextArea sau khi lưu
            frontField.clear();
            backField.clear();
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

    public void detailCard(MouseEvent actionEvent) throws IOException {
        selectedCard = cardTable.getSelectionModel().getSelectedItem();
        if (selectedCard != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/flashcardapp/details_card.fxml"));
            Parent detailsCardSceneRoot = loader.load();
            Scene detailsCardScene = new Scene(detailsCardSceneRoot);
            Stage stage = new Stage();
            stage.setResizable(false);
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

    public void backToMainWindow(MouseEvent mouseEvent) throws IOException {
        DeckData.getInstance().storeDeck();
        DeckData.getInstance().loadDeck();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/project/flashcardapp/main_window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Hello!");
        stage.show();
    }
}


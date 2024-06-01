package project.flashcardapp.Controller.Customization;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import project.flashcardapp.Model.Card;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

//Chi tiết nội dung card+ chức năng chỉnh sửa thẻ
public class DetailsCardController implements Initializable {
    public TextField frontTextField;
    public TextField backTextField;
    public TextField dueDateTextField;
    Card current;
    private AddCardController addCardController;

    public void setAddCardController(AddCardController addCardController) {
        this.addCardController = addCardController;
    }


    public void setCard(Card card) {
        this.current = card;
    }

    public void saveChanges(MouseEvent mouseEvent) {
        current.setQuestion(frontTextField.getText());
        current.setAnswer(backTextField.getText());
        addCardController.refreshCard();
        ((Stage) backTextField.getScene().getWindow()).close();
    }

    public void cancelChanges(MouseEvent mouseEvent) {
        if (changesMade()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Cancel");
            alert.setHeaderText("There are unsaved changes. Do you want to save them?");
            alert.setContentText("Click OK to save and close, or Cancel to discard changes.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                saveChanges(mouseEvent);
            }
        }
        ((Stage) frontTextField.getScene().getWindow()).close();
    }
    private boolean changesMade() {
        return !frontTextField.getText().equals(current.getQuestion()) ||
                !backTextField.getText().equals(current.getAnswer());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        current=AddCardController.selectedCard;
        frontTextField.setText(current.getQuestion());
        backTextField.setText(current.getAnswer());
        dueDateTextField.setText(toString(current.getSelector().getDeadlineAt()));
    }
    private String toString(Date deadlineAt) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(deadlineAt);
    }
}

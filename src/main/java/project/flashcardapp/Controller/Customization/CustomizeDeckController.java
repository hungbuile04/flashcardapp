package project.flashcardapp.Controller.Customization;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import project.flashcardapp.Controller.Display.DeckInfoController;
import project.flashcardapp.Model.Deck;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
    void cancelButton(MouseEvent event) throws Exception {
        if (changesMade()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Cancel");
            alert.setHeaderText("There are unsaved changes. Do you want to save them?");
            alert.setContentText("Click OK to save and close, or Cancel to discard changes.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                saveChanges();
            }
        }
    }
    private boolean changesMade() {
        return !deckNameField.getText().equals(deck.getDeckName()) ||
                !easyCardField.getText().equals(deck.getEasyCard()) ||
                !mediumCardField.getText().equals(deck.getMediumCard()) ||
                !hardCardField.getText().equals(deck.getHardCard());
    }
    public void saveChanges() throws Exception {
        String nameDeck = deckNameField.getText().trim();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        if (nameDeck.isEmpty()) {
            alert.setContentText("Category's name can't be blank!");
            alert.showAndWait();
            return;
        } else if (nameDeck.length() > 20) {
            alert.setContentText("Category's name has to be up to 20 characters");
            alert.showAndWait();
            return;
        } else if (nameDeck.length() < 2) {
            alert.setContentText("Category's name has to have at least 2 characters");
            alert.showAndWait();
            return;
        }
        deck.setDeckName(deckNameField.getText());
        deck.setLabelDescription(deckLabelField.getText());
        deck.setEasyCard(Integer.parseInt(easyCardField.getText()));
        deck.setMediumCard(Integer.parseInt(mediumCardField.getText()));
        deck.setHardCard(Integer.parseInt(hardCardField.getText()));
        try {
            // Tải FXML cho scene chi tiết
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/flashcardapp/deckinfo.fxml"));
            Parent detailSceneRoot = loader.load();
            Scene detailScene = new Scene(detailSceneRoot);
            Stage stage = (Stage)deckLabelField.getScene().getWindow();
            stage.setResizable(false);
            stage.setTitle(deck.getDeckName());
            stage.setScene(detailScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void saveButton(MouseEvent event) throws Exception {
        saveChanges();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.deck= DeckInfoController.deck;
        deckNameField.setText(deck.getDeckName());
        deckLabelField.setText(deck.getLabelDescription());
        easyCardField.setText(Integer.toString(deck.getEasyCard()));
        mediumCardField.setText(Integer.toString(deck.getMediumCard()));
        hardCardField.setText(Integer.toString(deck.getHardCard()));
        easyCardField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            } else {
                return null;
            }
        }));
        mediumCardField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            } else {
                return null;
            }
        }));
        hardCardField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            } else {
                return null;
            }
        }));
    }

    public void backButton(MouseEvent mouseEvent) {
        try {
            // Tải FXML cho scene chi tiết
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/flashcardapp/deckinfo.fxml"));
            Parent detailSceneRoot = loader.load();
            Scene detailScene = new Scene(detailSceneRoot);
            Stage stage = (Stage)deckLabelField.getScene().getWindow();
            stage.setResizable(false);
            stage.setTitle(deck.getDeckName());
            stage.setScene(detailScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
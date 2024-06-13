package project.flashcardapp.Controller.Display;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserInformation {

    @FXML
    private Label Age;

    @FXML
    private Label Email;

    @FXML
    private Label Phone;

    @FXML
    private Label Username;

    @FXML
    private Label Yourschool;

    @FXML
    private TextField ageField;

    @FXML
    private Button backtoSettings;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField schoolField;

    @FXML
    private AnchorPane userPane;

    @FXML
    private Label userTitle;

    @FXML
    public void backtosettingsButton (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/flashcardapp/settings.fxml"));
        Parent settingsSceneRoot = loader.load();

        // Get the controller for the next screen
        SettingsController settingsController = loader.getController();
        // Pass data to the next screen
        settingsController.setUserData(
                ageField.getText(),
                emailField.getText(),
                phoneField.getText(),
                nameField.getText(),
                schoolField.getText()
        );

        Stage stage = new Stage();
        stage.setTitle("Settings");
        stage.setScene(new Scene(settingsSceneRoot));
        stage.show();

        // Close current stage
        Stage currentStage = (Stage) userPane.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    public void confirmButton (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/flashcardapp/settings.fxml"));
        Parent settingsSceneRoot = loader.load();

        // Get the controller for the next screen
        SettingsController settingsController = loader.getController();
        // Pass data to the next screen
        settingsController.setUserData(
                ageField.getText(),
                emailField.getText(),
                phoneField.getText(),
                nameField.getText(),
                schoolField.getText()
        );

        Stage stage = new Stage();
        stage.setTitle("Settings");
        stage.setScene(new Scene(settingsSceneRoot));
        stage.show();

        // Close current stage
        Stage currentStage = (Stage) userPane.getScene().getWindow();
        currentStage.close();
    }
}

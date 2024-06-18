package project.flashcardapp.Controller.Display;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SettingsController {
    @FXML
    private Label age;

    @FXML
    private Label ageLabel;

    @FXML
    private Button backButton;

    @FXML
    private Label email;

    @FXML
    private Label emailLabel;

    @FXML
    private Circle frameImage;

    @FXML
    private Pane inforPane;

    @FXML
    private Label nameTitle;

    @FXML
    private Label phoneLabel;

    @FXML
    private Circle profileImage;

//    public MainWindowController mainWindowController;
//    public void setMainWindowController(){};

    @FXML
    private AnchorPane scenePane;

    @FXML
    private Label schoolLabel;

    @FXML
    private Label settings;

    @FXML
    private Label settingsTitle;

    @FXML
    private Button settingsUser;

    @FXML
    private Button uploadImage;

    @FXML
    private Label userinformation;

    @FXML
    private Label username;

    @FXML
    private Label usernameLabel;

    @FXML
    public void uploadImage(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();

        Stage stage = (Stage) scenePane.getScene().getWindow();

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            Image image = new Image(file.toURI().toString(), 300, 300, false, true);
            profileImage.setFill(new ImagePattern(image));
        }
    }

    @FXML
    public void switchtoSettingsUser(ActionEvent event) throws IOException {

        Stage currentStage = (Stage) scenePane.getScene().getWindow();
        currentStage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/flashcardapp/user_information.fxml"));
        Parent settingsSceneRoot = loader.load();
        Scene settingsScene = new Scene(settingsSceneRoot);
        Stage stage = new Stage();
        stage.setTitle("User Information");
        stage.setScene(settingsScene);
        stage.show();
    }

    // Method to receive and display user data
    public void setUserData(String age, String email, String phone, String username, String school) {
        nameTitle.setText(username);
        ageLabel.setText(age);
        emailLabel.setText(email);
        phoneLabel.setText(phone);
        usernameLabel.setText(username);
        schoolLabel.setText(school);
    }

    public void backToMainWindow(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cài đặt");
        alert.setHeaderText("Bạn đã thay đổi phần cài đặt");
        alert.setContentText("Bạn có muốn lưu lại thay đổi không?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) scenePane.getScene().getWindow();
            stage.close();
        }
    }
}

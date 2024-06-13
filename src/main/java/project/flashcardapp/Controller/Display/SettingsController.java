package project.flashcardapp.Controller.Display;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SettingsController {
    @FXML
    private AnchorPane scenePane;

    @FXML
    private Circle profileImage;

//    public MainWindowController mainWindowController;
//    public void setMainWindowController(){};

    @FXML
    public void backtoMainButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cài đặt");
        alert.setHeaderText("Bạn đã thay đổi phần cài đặt");
        alert.setContentText("Bạn có muốn lưu lại thay đổi không?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) scenePane.getScene().getWindow();
            stage.close();
        }
    }

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
}

package project.flashcardapp.Controller.Display;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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

//    Back to MainWindow
    @FXML
    public void backtoMainButton(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

//        Thêm cửa sổ cảnh báo, nếu có sự thay đổi ở cài đặt thì hỏi có lưu hay không
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cài đặt");
        alert.setHeaderText("Bạn đã thay đổi phần cài đặt");
        alert.setContentText("Bạn có muốn lưu lại thay đổi không?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage)scenePane.getScene().getWindow();
            stage.close();
        }
    }

//    Upload Image

    @FXML
    private Circle profileImage;

    @FXML
    public void uploadImage(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();

        Stage stage = (Stage)scenePane.getScene().getWindow();

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            Image image = new Image(file.toURI().toString(), 300, 300 ,false,true);
            profileImage.setFill(new ImagePattern(image));
        }
    }
}

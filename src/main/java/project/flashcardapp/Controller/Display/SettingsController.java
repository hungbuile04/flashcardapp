package project.flashcardapp.Controller.Display;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
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

    private static String uploadedImageUrl;

    @FXML
    public void uploadImage(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();

        Stage stage = (Stage) scenePane.getScene().getWindow();

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            Image image = new Image(file.toURI().toString(), 300, 300, false, true);
            profileImage.setFill(new ImagePattern(image));
            uploadedImageUrl = file.toURI().toString();
        }
    }

    @FXML
    public void backtoMainButton(MouseEvent event) throws IOException {
        Stage curentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        curentStage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/flashcardapp/main_window.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("EngHUST");

        Image icon = new Image("logoApp_transparent_final.png");
        stage.getIcons().add(icon);

        MainWindowController mainWindowController = loader.getController();
//        mainWindowController.setProfileImage(SettingsController.getUploadedImageUrl());

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchtoSettingsUser(ActionEvent event) throws IOException {

        Stage currentStage = (Stage) scenePane.getScene().getWindow();
        currentStage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/flashcardapp/user_information.fxml"));
        Parent settingsSceneRoot = loader.load();
        Scene settingsScene = new Scene(settingsSceneRoot);
        Stage stage = new Stage();
        Image icon = new Image("logoApp_transparent_final.png");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle("EngHUST");
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

//    Thêm phương thức trả về
    public static String getUploadedImageUrl() {
        return uploadedImageUrl;
    }
}

package project.flashcardapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import project.flashcardapp.Model.DeckData;
import project.flashcardapp.Controller.Display.SettingsController;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main_window.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        Image icon = new Image("logoApp_transparent_final.png");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setTitle("EngHUST");

        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }


    @Override
    public void init() throws Exception {
        try{
            DeckData.getInstance().loadDeck();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void stop() throws IOException {
        try{
            DeckData.getInstance().storeDeck();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
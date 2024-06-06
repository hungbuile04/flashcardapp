package project.flashcardapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.flashcardapp.Model.DeckData;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main_window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("EngHust Flash Card App");
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
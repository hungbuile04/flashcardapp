package project.flashcardapp.Controller.Display;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class NoteController implements Initializable {
    private String filePath = "note.txt";
    public TextArea noteField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadNote();
    }

    private void loadNote() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        StringBuilder note = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                note.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        noteField.setText(note.toString());
    }

    @FXML
    private void saveNote() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(noteField.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) noteField.getScene().getWindow();
        stage.close();
    }
}

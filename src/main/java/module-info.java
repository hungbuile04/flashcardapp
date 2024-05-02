module project.flashcardapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;

    opens project.flashcardapp.Model to javafx.fxml, javafx.base, com.google.gson;
    opens project.flashcardapp to javafx.fxml;
    exports project.flashcardapp;
    exports project.flashcardapp.Controller;
    opens project.flashcardapp.Controller to javafx.fxml;
}
module project.flashcardapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;
    requires java.desktop;

    exports project.flashcardapp.Controller.Display;
    opens project.flashcardapp.Model to javafx.fxml, javafx.base, com.google.gson;
    opens project.flashcardapp to javafx.fxml;
    exports project.flashcardapp;
    exports project.flashcardapp.Controller.Customization;
    opens project.flashcardapp.Controller.Customization to javafx.fxml;
    exports project.flashcardapp.Controller.LearningMode;
    opens project.flashcardapp.Controller.LearningMode to javafx.fxml;
    opens project.flashcardapp.Controller.Display to javafx.fxml;
}
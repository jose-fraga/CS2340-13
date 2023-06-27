module com.example.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.lang3;


    opens com.example.project to javafx.fxml;
    exports com.example.project;
    exports com.example.project.codenames;
    opens com.example.project.codenames to javafx.fxml;
    exports com.example.project.flowfree;
    opens com.example.project.flowfree to javafx.fxml;
    exports com.example.project.flowfree.controllers;
    opens com.example.project.flowfree.controllers to javafx.fxml;
    exports com.example.project.flowfree.enums;
    opens com.example.project.flowfree.enums to javafx.fxml;
    exports com.example.project.wordle;
    opens com.example.project.wordle to javafx.fxml;
    exports com.example.project.wordle.controllers;
    opens com.example.project.wordle.controllers to javafx.fxml;
}
package com.example.project.codenames;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class CodenamesConfigurationScreenController implements Initializable {
    private CodenamesConfigSingleton config;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("Codenames Configuration Screen");
        config = CodenamesConfigSingleton.getInstance();
//        config.getTeam1().setName("baloney");
//        config.getTeam2().setName("awesome");
    }

}

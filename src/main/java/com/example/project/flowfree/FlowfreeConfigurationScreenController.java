package com.example.project.flowfree;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class FlowfreeConfigurationScreenController implements Initializable {
    private FlowfreeConfigSingleton config;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("Flow Free Configuration Screen");
        config = FlowfreeConfigSingleton.getInstance();
    }

}

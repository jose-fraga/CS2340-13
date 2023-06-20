package com.example.project.flowfree.enums;

public enum Warning {
    FAILURE_1("Drag Exited Grid!"),
    FAILURE_2("Invalid Dot Connection!"),
    FAILURE_3("Invalid Drag!"),
    FAILURE_4("Drag Contains Obstacles!");

    private final String message;

    Warning(String message) {
        this.message = "FAILURE:\n" + message;
    }

    public String getMessage() { return this.message; }
}

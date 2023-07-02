package com.example.project.wordle;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EnumPathTestHelper<T> {
    private T enumToTest;

    public void assertPathResolves(String path, String pathMethodName) {
        assertNotNull(EnumPathTestHelper.class.getResource(path), this.notFoundMsg(enumToTest, pathMethodName, path));
    }

    public String notFoundMsg(T enumValue, String pathMethodName, String path) {
        return "FXML File not found for enum" + enumValue + "." + pathMethodName + "() at " + path + "\n" +
                "Check that the file exists in src/main/resources/com/example/project/" + path + "\n" +
                "Check that the file is included in the build.gradle file";
    }

}

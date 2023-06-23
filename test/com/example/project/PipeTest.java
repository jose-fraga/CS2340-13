package com.example.project;

import com.example.project.flowfree.Pipe;
import com.example.project.flowfree.enums.PipeState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.example.project.flowfree.Obstacle;
import org.junit.jupiter.api.Test;
class PipeTest {
    @Test
    void pipeIsNull() {
        // Create a pipe and add it to the game flow
        Pipe pipe = new Pipe();
        assertNotNull(pipe, "Pipe is null");
    }

    @Test
    void PipeState() {
        Pipe pipe = new Pipe();
        assertEquals(PipeState.EMPTY,pipe.getPipeState());
    }
}
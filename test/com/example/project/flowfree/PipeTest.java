package com.example.project.flowfree;

import com.example.project.flowfree.enums.PipeState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

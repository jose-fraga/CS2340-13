package com.example.project.flowfree;

import javafx.scene.paint.Color;

public class Pipe extends ColoredGridItem {
    private PipeState pipeState;

    public Pipe() {
        super(null);
        setPipeState(PipeState.EMPTY);
    }


    public Pipe(int x, int y) {
        super(null, x, y);
        setPipeState(PipeState.EMPTY);
    }

    public Pipe(Color color, int x, int y) {
        super(color, x, y);
        setPipeState(PipeState.EMPTY);
    }

    public void setPipeState(PipeState state) {
        this.pipeState = state;
        this.setIsEmpty(this.pipeState == PipeState.EMPTY);
    }

    public void tempFill(Color color) {
        this.setColor(color);
        setPipeState(PipeState.FILLED_TEMP);
    }

    @Override
    public boolean isEmpty() {
        return this.pipeState == PipeState.EMPTY;
    }

    public void reset() {
        this.setColor(null);
        this.setPipeState(PipeState.EMPTY);
    }

    public void finalize() {
        if (this.pipeState == PipeState.FILLED_TEMP) {
            this.setPipeState(PipeState.FILLED_FINAL);
        }
    }

    @Override
    public String toString() {
        return String.format("Pipe(%s, %d, %d, %s)", this.pipeState, this.getX(), this.getY(), this.getHexColor());
    }
}

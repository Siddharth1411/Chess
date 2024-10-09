package com.Game.chess;

public class CoordinateRequest {
    private Coordinate start;
    private Coordinate end;

    public CoordinateRequest() {}

    public CoordinateRequest(Coordinate start, Coordinate end) {
        this.start = start;
        this.end = end;
    }

    public Coordinate getStart() {
        return start;
    }

    public void setStart(Coordinate start) {
        this.start = start;
    }

    public Coordinate getEnd() {
        return end;
    }

    public void setEnd(Coordinate end) {
        this.end = end;
    }
}

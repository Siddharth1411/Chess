package com.Game.chess;

public interface Piece {
    public boolean isValidMove(int r1, int c1, int r2, int c2);
    public String getName();
    public int getColor();
}

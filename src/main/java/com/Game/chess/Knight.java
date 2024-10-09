package com.Game.chess;

public class Knight implements Piece {
    int color;

    public Knight(int c){
        this.color = c;
    }
    public boolean isValidMove(int r1, int c1, int r2, int c2){
        if(Math.abs(r1 - r2) == 2 && Math.abs(c1 - c2) == 1) return true;
        if(Math.abs(r1 - r2) == 1 && Math.abs(c1 - c2) == 2) return true;
        return false;
    }

    public String getName(){
        return "knight";
    }

    public int getColor(){
        return this.color;
    }
}

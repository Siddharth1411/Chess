package com.Game.chess;

public class Bishop implements Piece {
    int color;

    public Bishop(int c){
        this.color = c;
    }
    public boolean isValidMove(int r1, int c1, int r2, int c2){
        //only diagonal movement
        if(Math.abs(r1 - r2) == Math.abs(c1 - c2)) return true;
        return false;
    }

    public String getName(){
        return "bishop";
    }

    public int getColor(){
        return this.color;
    }
}


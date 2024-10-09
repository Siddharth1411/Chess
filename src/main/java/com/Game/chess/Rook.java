package com.Game.chess;

public class Rook implements Piece {
    int color;

    public Rook(int c){
        this.color = c;
    }
    public boolean isValidMove(int r1, int c1, int r2, int c2){
        //horizontal + verical
        if(r1 == r2 || c1 == c2) return true;
        return false;
    }

    public String getName(){
        return "rook";
    }

    public int getColor(){
        return this.color;
    }
}

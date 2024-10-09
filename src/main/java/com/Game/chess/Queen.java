package com.Game.chess;

public class Queen implements Piece {
    int color;

    public Queen(int c){
        this.color = c;
    }
    public boolean isValidMove(int r1, int c1, int r2, int c2){
        //rook + bishop
        if(r1 == r2 || c1 == c2) return true; //rook
        if(Math.abs(r1 - r2) == Math.abs(c1 - c2)) return true; //bishop
        return false;
    }

    public String getName(){
        return "queen";
    }

    public int getColor(){
        return this.color;
    }
}

package com.Game.chess;

public class Pawn implements Piece {
    int color;

    public Pawn(int c){
        this.color = c;
    }
    public boolean isValidMove(int r1, int c1, int r2, int c2){
        //can change atmost 1 col and atmost 2 rows
        //no backward movement (backward defined differently for white & black)
        //possible movements: (2 rows forward only when starting), (1 row forward), (1 row + 1 col when taking a piece)
        if(Math.abs(c1 - c2) > 1 || Math.abs(r1 - r2) > 2 || r1 == r2 || (Math.abs(r1 - r2) == 2 && Math.abs(c1 - c2) == 1)) return false;
        if(this.color == 0 && (r2 < r1 || (r2 - r1 == 2 && r1 != 1))) return false;
        else if(this.color == 1 && (r2 > r1 || (r1 - r2 == 2 && r1 != 6))) return false;
        return true;
    }

    public String getName(){
        return "pawn";
    }

    public int getColor(){
        return this.color;
    }
}

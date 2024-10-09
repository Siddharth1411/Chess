package com.Game.chess;

import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class playGame {
    Board board;
    public playGame(){
        buildBoard();
    }
    public void buildBoard(){
        board = new Board();
        board.setRooks();
        board.setKnights();
        board.setBishops();
        board.setQueens();
        board.setKings();
        board.setPawns();
    }
    public String getPiece(int r, int c){
        return board.getPieceName(r, c);
    }

    public List<List<Integer>> activateValidMoves(int row, int col){
        return board.activeCells(row, col);
    }
    public void playMove(int r1, int c1, int r2, int c2){
        board.MovePiece(r1, c1, r2, c2);
    }
    public void resetGame(){
        buildBoard();
    }
}

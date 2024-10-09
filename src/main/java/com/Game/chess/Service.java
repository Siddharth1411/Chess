package com.Game.chess;
import java.util.*;

@org.springframework.stereotype.Service
public class Service {
    playGame game;
    public Service(playGame game){
        this.game = game;
    }
    public String getInfoForCell(int row, int col){
        return game.getPiece(row, col);
    }
    public List<List<Integer>> getValidMoves(int row, int col){
        return game.activateValidMoves(row, col);
    }
    public void makeMove(Coordinate start, Coordinate end){
        game.playMove(start.getRow(), start.getCol(), end.getRow(), end.getCol());
    }
    public void Reset(){
        game.resetGame();
    }
}

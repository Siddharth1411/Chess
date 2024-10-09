package com.Game.chess;
import java.util.*;
public class Board {
    private Piece[][] board = new Piece[8][8]; 

    public void createPiece(Piece p, int row, int col){
        if(board[row][col] != null){
            System.out.println("position is occupied");
            return;
        }
        board[row][col] = p;
    }

    public boolean CanMove(int r1, int c1, int r2, int c2){
        // no piece to move
        if(board[r1][c1] == null){
            return false;
        }
        if(board[r1][c1].isValidMove(r1, c1, r2, c2)){
            //destination cannot contain same color
            if(board[r2][c2] != null){
                if(board[r2][c2].getColor() == board[r1][c1].getColor()){
                    return false;
                }
            }
            //knight can jump over other pieces
            if(board[r1][c1].getName() != "knight"){
                //other pieces can only move if the path is clear
                int r_step = r1 == r2 ? 0 : (r2 - r1)/Math.abs(r1 - r2);
                int c_step = c1 == c2 ? 0 : (c2 - c1)/Math.abs(c1 - c2);
                for(int r = r1 + r_step, c = c1 + c_step; (r != r2 || c != c2); ){
                    if(board[r][c] != null){
                        //path blocked
                        return false;
                    }
                    if(r != r2) r += r_step;
                    if(c != c2) c += c_step;
                }
            }
        }
        else return false;
        //pawn moves diagonally by taking a piece
        if(board[r1][c1].getName() == "pawn" && Math.abs(c1 - c2) == 1) return board[r2][c2] != null;
        //pawn cannot take a piece moving forward
        if(board[r1][c1].getName() == "pawn" && board[r2][c2] != null) return false;
        return true;
    }

    public void MovePiece(int r1, int c1, int r2, int c2){
        if(CanMove(r1, c1, r2, c2) == true){
            board[r2][c2] = board[r1][c1];
            board[r1][c1] = null;
        }
    }


    public void setRooks(){
        Piece whiteRook1 = new Rook(0);
        Piece whiteRook2 = new Rook(0);
        Piece blackRook1 = new Rook(1);
        Piece blackRook2 = new Rook(1);
        createPiece(whiteRook1, 0, 0);
        createPiece(whiteRook2, 0, 7);
        createPiece(blackRook1, 7, 0);
        createPiece(blackRook2, 7, 7);
    }

    public void setKnights(){
        Piece whiteKnight1 = new Knight(0);
        Piece whiteKnight2 = new Knight(0);
        Piece blackKnight1 = new Knight(1);
        Piece blackKnight2 = new Knight(1);
        createPiece(whiteKnight1, 0, 1);
        createPiece(whiteKnight2, 0, 6);
        createPiece(blackKnight1, 7, 1);
        createPiece(blackKnight2, 7, 6);
    }

    public void setBishops(){
        Piece whiteBishop1 = new Bishop(0);
        Piece whiteBishop2 = new Bishop(0);
        Piece blackBishop1 = new Bishop(1);
        Piece blackBishop2 = new Bishop(1);
        createPiece(whiteBishop1, 0, 2);
        createPiece(whiteBishop2, 0, 5);
        createPiece(blackBishop1, 7, 2);
        createPiece(blackBishop2, 7, 5);
    }

    public void setQueens(){
        Piece whiteQueen = new Queen(0);
        Piece blackQueen = new Queen(1);
        createPiece(blackQueen, 7, 3);
        createPiece(whiteQueen, 0, 3);
    }

    public void setKings(){
        Piece whiteKing = new King(0);
        Piece blackKing = new King(1);
        createPiece(blackKing, 7, 4);
        createPiece(whiteKing, 0, 4);
    }
    
    public void setPawns(){
        for(int i = 0; i < 8; i++){
            board[1][i] = new Pawn(0);
            board[6][i] = new Pawn(1);
        }
    }
    public String getPieceName(int r, int c){
        if(board[r][c] == null) return "empty cell";
        String color;
        if(board[r][c].getColor() == 0) color = "white";
        else color = "black";
        String piece = board[r][c].getName();
        return color + " " + piece;
    }

    //valid destination cells for a given src cell
    public List<List<Integer>> activeCells(int row, int col){
        if(board[row][col] == null) return new ArrayList<>();
        int color = board[row][col].getColor();
        List<List<Integer>> validCells = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                //same color - invalid
                if(board[i][j] != null && board[i][j].getColor() == color) continue;
                //src cell - invalid
                if(row == i && col == j) continue;

                if(CanMove(row, col, i, j) == true){
                    List<Integer>l = new ArrayList<>();
                    l.add(i);
                    l.add(j);
                    validCells.add(l);
                }
            }
        }
        return validCells;
    }
}
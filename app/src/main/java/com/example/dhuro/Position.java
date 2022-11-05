package com.example.dhuro;

import com.example.dhuro.Pieces.Piece;


public class Position {
    private Piece piece;


    Position(Piece piece ) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;

    }

    void setPiece(Piece piece) {
        this.piece = piece;
    }

}

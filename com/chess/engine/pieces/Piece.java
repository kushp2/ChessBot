package com.chess.engine.pieces;

import java.util.Collection;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

public abstract class Piece
{
    //variables 
    protected final int piecePosition;                          //holds the piece position
    protected final Alliance pieceAlliance;                     //holds enum values           

    Piece(final int piecePosition, final Alliance pieceAlliance){
        this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePosition;
    }//end of constructor

    //methods
    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }
    
    public abstract Collection<Move> calculateLegalMoves(final Board board);

}//end of piece class
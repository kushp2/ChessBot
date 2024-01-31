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
    protected final boolean isFirstMove;                        //true if it is the pieces first move

    Piece(final int piecePosition, final Alliance pieceAlliance){
        this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePosition;
        //TODO more work here!!
        this.isFirstMove = false;
    }//end of constructor

    //methods
    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }
    
    public boolean isFirstMove(){
        return isFirstMove;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);

}//end of piece class
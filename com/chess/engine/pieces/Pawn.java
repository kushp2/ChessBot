package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;

public class Pawn extends Piece {

    private static int[] CANDIDATE_MOVE_COORDINATES = {8, 16, 7, 9};


    Pawn(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) 
    {

        final List<Move> legalMoves = new ArrayList<>(); 

        //for each move the pawn can make
        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES)
        {
            final int candidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * currentCandidateOffset);

            //if the move is on the board
            if (!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                continue;
            }
            //if the tile is not occupied
            if (currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied())
            {
                //TODO more work to do here (promotions!!!
                legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
            } else if (currentCandidateOffset == 16 && this.isFirstMove() && 
                (BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceAlliance().isBlack()) || 
                (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceAlliance().isWhite()))
            {
                final int behindcandidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * 8);
                if (!board.getTile(behindcandidateDestinationCoordinate).isTileOccupied() && 
                !board.getTile(candidateDestinationCoordinate).isTileOccupied())
                {
                    legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                }
            } else if (currentCandidateOffset == 7 && 
                !((BoardUtils.EIGHTH_COLUM[piecePosition] && this.pieceAlliance.isWhite() ||
                (BoardUtils.FIRST_COLUMN[piecePosition] && this.pieceAlliance.isBlack())) ))
            {
                if (board.getTile(candidateDestinationCoordinate).isTileOccupied())
                {
                    final Piece pieceOncandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if (this.pieceAlliance != pieceOncandidate.getPieceAlliance())
                    {
                        //TODO more to do here!!
                        legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                    }
                }
            } else if (currentCandidateOffset == 9 &&
                !((BoardUtils.FIRST_COLUMN[piecePosition] && this.pieceAlliance.isWhite() ||
                (BoardUtils.EIGHTH_COLUM[piecePosition] && this.pieceAlliance.isBlack())) ))
            {
                if (board.getTile(candidateDestinationCoordinate).isTileOccupied())
                {
                    final Piece pieceOncandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if (this.pieceAlliance != pieceOncandidate.getPieceAlliance())
                    {
                        //TODO more to do here!!
                        legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                    }
                }
            }
        
        }//end of for loop

        return null;
    }//end of CalculateLegalMoves
}
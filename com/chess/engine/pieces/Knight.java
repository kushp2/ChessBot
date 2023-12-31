package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.AttackMove;
import com.chess.engine.board.Move.MajorMove;
import com.chess.engine.board.Tile;

public class Knight extends Piece
{
    //all the legal moves a knight can make (adds these values to the knight's current coordinate to get the possible destination coordinates)
    private final static int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};

    Knight(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) 
    {
        
        //variables
        int candidateDestinationCoordinate;                                                                //holds the destination coordinates
        final List<Move> legalMoves = new ArrayList<>();                                                   //holds the legal moves

        //for every move the knight can make
        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES)
        {
            //checks to see if the move is valid
            candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;

            if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate))
            {
                // if  the move is on an edge case
                if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) || 
                isSecondColumnExclusion(candidateDestinationCoordinate, currentCandidateOffset) || 
                isSeventhColumnExclusion(candidateDestinationCoordinate, currentCandidateOffset) || 
                isEighthColumnExclusion(candidateDestinationCoordinate, currentCandidateOffset)){
                    continue;
                }
                
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);

                //if it is not occupied, add to leagal moves, else check for alliance (if the piece is black or white)
                if (!candidateDestinationTile.isTileOccupied()){
                    legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                } else{
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                    //adds a legal move is opponent is on the destination tile
                    if (this.pieceAlliance != pieceAlliance){
                        legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                    }
                }//end if else
            }//end if the move is valid
        }//end of for loop
        
        return Collections.unmodifiableList(legalMoves);
    }//end of calculateLeagalMoves  
    
    
    //edge cases for the legal moves
    //for the first column
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN [currentPosition] && ((candidateOffset == -17) || (candidateOffset == -10) || (candidateOffset == 6) || (candidateOffset == 15));
    }
    //for the second column
    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.SECOND_COLUMN[currentPosition] && ((candidateOffset == -10) || candidateOffset == 6);
    }
    //for the seventh column
    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && ((candidateOffset == -6) || candidateOffset == 10);
    }
    //for the eight column
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.EIGHTH_COLUM[currentPosition] && ((candidateOffset == -15) || (candidateOffset == -6) || (candidateOffset == 10) || candidateOffset == 17);
    }

}//end of knight

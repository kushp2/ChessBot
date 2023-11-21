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

public class Rook extends Piece
{
    //all the legal moves a rook can make 
    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-8, -1, 1, 8};

    //constructor
    Rook(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

//calculates the legal moves the bishop can make
    @Override
    public Collection<Move> calculateLegalMoves(final Board board) 
    {
        final List<Move> legalMoves = new ArrayList<>();

        //for every direction the bishop can move
        for (final int candidateCoordinationOffset : CANDIDATE_MOVE_VECTOR_COORDINATES)
        {
            int candidateDestinationCoordinate = this.piecePosition;

            //while the next move is valid, apply the offset (legal move)
            while(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate))
            {

                if(isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinationOffset) || 
                isEighthColumnExclusion(candidateDestinationCoordinate, candidateCoordinationOffset))
                {
                    break;
                }

                candidateDestinationCoordinate += candidateCoordinationOffset; 

                if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate))
                {
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
                        break;  
                    }//end if else
                }//end if
            }//end of while loop
        }//end of for loop

        return Collections.unmodifiableList(legalMoves);
    }//end of calculateLegalMoves


    //edge cases for the legal moves
    //for the first column
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN [currentPosition] && ((candidateOffset == -1));
    }
    //for the eight column
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.EIGHTH_COLUM[currentPosition] && ((candidateOffset == 1));
    }

}//end of rook class

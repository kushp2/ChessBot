package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public abstract class Tile 
{
    protected final int tileCoordinate;                                                             // will hold the tile coordinates
    private static final Map<Integer, EmptyTile> EMPTY_TILE_CACHE = createAllPossibleEmptyTiles();

    //creates a map
    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() 
    {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>(); 

        for (int i = 0; i < 64; i++){
            emptyTileMap.put(i, new EmptyTile(i));
        }

        return Collections.unmodifiableMap(emptyTileMap);
    }//end of map constructor

    //creates the tiles on the map
    public static Tile createTile(final int tileCoordinate, final Piece piece){
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILE_CACHE.get(tileCoordinate);
    }

    //sets the coordinates of a tile
    private Tile(int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }


    // methods
    public abstract boolean isTileOccupied();                                                       // returns true if tile is occupied
    public abstract Piece getPiece();                                                               // returns the piece type


    // classes for empty and occupied tiles
    public static final class EmptyTile extends Tile {
        
        private EmptyTile(final int coordinate) {
            super(coordinate);
        }

        @Override
        public boolean isTileOccupied() {
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }
    }// end of final class emptyTile

    public static final class OccupiedTile extends Tile {
        private final Piece pieceOnTile;                                                             // holds the piece on the tile

        private OccupiedTile(int tileCoordinate, Piece pieceOnTile) 
        {
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public boolean isTileOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return this.pieceOnTile;
        }
    }// end of final class OccupiedTile
}// end of board class
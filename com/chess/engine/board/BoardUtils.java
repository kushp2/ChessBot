package com.chess.engine.board;

public class BoardUtils {

    //variables
    public static final boolean[] FIRST_COLUMN = initColumn(0);                     //a 'column' of booleans set to true
    public static final boolean[] SECOND_COLUMN = initColumn(1);
    public static final boolean[] SEVENTH_COLUMN = initColumn(6);
    public static final boolean[] EIGHTH_COLUM = initColumn(7);
    public static final int NUM_TILES = 64;                                             //Total number of tiles on the board
    public static final int NUM_TILES_PER_ROW = 8;

    //TODO more work needed here!!!
    public static final boolean[] SECOND_ROW = null;                                    //holds the rows
    public static final boolean[] SEVENTH_ROW = null;

    private BoardUtils() {
        throw new RuntimeException("You cannot instantiate me!");
    }
    
    //returns true if the tile is on the board
    public static boolean isValidTileCoordinate(final int coordinate){
        return coordinate >= 0 && coordinate < NUM_TILES;
    }

    //sets each boolean in the column to true
    private static boolean[] initColumn(int col) 
    {
        final boolean[] column = new boolean[64];
        do{
            column[col] = true;
            col+=NUM_TILES_PER_ROW;
        }while(col < NUM_TILES);

        return column;
    }
}

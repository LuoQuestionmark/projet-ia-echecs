// This will be the abstract class of a chess piece

package Board;

import java.util.TreeSet;

import mUtil.Coord;

public abstract class Piece {
    protected boolean isBlackVal;
    // protected Coord coord; // the position
    // protected Board board;
    abstract public TreeSet<Coord> getLegalMoves(Board currentBoard, Coord currentCoord);

    abstract public String getShortName();
    
    // public Piece() throws IllegalAccessError {
        //     throw new IllegalAccessError("don't call this function directly");
        // }
        
    public Piece(boolean isBlack) {
        // this.board = b;
        this.isBlackVal = isBlack;
        // this.coord = initCoord;
    }
    
    public boolean isBlack() {
        return isBlackVal;
    }

}

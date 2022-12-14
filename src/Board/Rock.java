package Board;

import java.util.TreeMap;
import java.util.TreeSet;
import java.lang.Math;

import mUtil.Coord;

public class Rock extends Piece{

    public Rock(boolean isBlack) {
        super(isBlack);
    }

    @Override
    public TreeSet<Coord> getLegalMoves(Board currentBoard, Coord currentCoord) {
        int xLowerLimit, xUpperLimit, yLowerLimit, yUpperLimit;
        xLowerLimit = Math.min(0, currentCoord.x);
        xUpperLimit = Math.max(7, currentCoord.x);

        yLowerLimit = Math.min(0, currentCoord.y);
        yUpperLimit = Math.max(7, currentCoord.y);
        
        TreeMap<Coord, Piece> friends, enemies;
        if (this.isBlack()) {
            friends = currentBoard.getBlackPieces(currentCoord);
            enemies = currentBoard.getWhitePieces();
        }
        else {
            friends = currentBoard.getWhitePieces(currentCoord);
            enemies = currentBoard.getBlackPieces();
        }

        for (Coord c: friends.keySet()) {
            if (c.y == currentCoord.y) {
                // same row
                if (c.x < currentCoord.x) {
                    xLowerLimit = Math.max(xLowerLimit, c.x + 1); // cannot eat teammate
                }
                else {
                    xUpperLimit = Math.min(xUpperLimit, c.x - 1); // cannot eat teammate
                }
            }
            else if (c.x == currentCoord.x) {
                // same col
                if (c.y < currentCoord.y) {
                    yLowerLimit = Math.max(yLowerLimit, c.y + 1); // cannot eat teammate
                }
                else {
                    yUpperLimit = Math.min(yUpperLimit, c.y - 1); // cannot eat teammate
                }
            }
        }

        for (Coord c: enemies.keySet()) {
            if (c.y == currentCoord.y) {
                // same row
                if (c.x < currentCoord.x) {
                    xLowerLimit = Math.max(xLowerLimit, c.x); // can eat enemy
                }
                else {
                    xUpperLimit = Math.min(xUpperLimit, c.x); // can eat enemy
                }
            }
            else if (c.x == currentCoord.x) {
                // same col
                if (c.y < currentCoord.y) {
                    yLowerLimit = Math.max(yLowerLimit, c.y); // can eat enemy
                }
                else {
                    yUpperLimit = Math.min(yUpperLimit, c.y); // can eat enemy
                }
            }
        }

        TreeSet<Coord> ret = new TreeSet<>();

        for (int col = xLowerLimit; col <= xUpperLimit; col++) {
            ret.add(new Coord(col, currentCoord.y));
        }
        for (int row = yLowerLimit; row <= yUpperLimit; row++) {
            ret.add(new Coord(currentCoord.x, row));
        }

        ret.remove(currentCoord);
        
        return ret;
    }

    @Override
    public String getShortName() {
        return "R";
    }
    
}

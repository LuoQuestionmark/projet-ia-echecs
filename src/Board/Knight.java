package Board;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

import mUtil.Coord;

public class Knight extends Piece {

    public Knight(boolean isBlack) {
        super(isBlack);
    }

    @Override
    public TreeSet<Coord> getLegalMoves(Board currentBoard, Coord currentCoord) {
        ArrayList<Integer> p1 = new ArrayList<>();
        p1.add(1); p1.add(-1);
        ArrayList<Integer> p2 = new ArrayList<>(p1);
        ArrayList<Integer> p3 = new ArrayList<>(p1);

        TreeSet<Coord> ret = new TreeSet<>();

        for (int i: p1) {
            for (int j: p2) {
                for (int k: p3) {
                    int x = i * j * (k>0?2:1);
                    int y = j * (k<0?2:1);
                    // System.out.printf("%d, %d\n", x, y);
                    if ((x + currentCoord.x >= 0 && x + currentCoord.x < 8)
                      &&(y + currentCoord.y >= 0 && y + currentCoord.y < 8))
                    {
                        ret.add(new Coord(x + currentCoord.x,  + y + currentCoord.y));
                    }
                }
            }
        }

        TreeMap<Coord, Piece> friends;
        if (this.isBlack()) {
            friends = currentBoard.getBlackPieces(currentCoord);
        }
        else {
            friends = currentBoard.getWhitePieces(currentCoord);
        }

        TreeSet<Coord> illegaCoords = new TreeSet<Coord>();

        // cannot eat teammates
        for (Coord c: friends.keySet()) {
            if (ret.contains(c)) {
                illegaCoords.add(c);
            }
        }

        ret.removeAll(illegaCoords);

        return ret;
    }

    @Override
    public String getShortName() {
        return "N";
    }
    
}

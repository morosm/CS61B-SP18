package byog.Core;

import byog.TileEngine.TETile;

import java.util.Random;

public class Hallway extends Generator{
    public final int minX;
    public final int minY;
    public final int maxX;
    public final int maxY;

    private Hallway(int minX, int minY, int maxX, int maxY){
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public static Hallway generateHallway(Random ran, Room r1, Room r2){
        Layout layout = checkLayout(r1, r2);
        return layout == Layout.HORIZONTAL?
                generateHorizontal(ran, r1, r2):
                generateVertical(ran, r1, r2);
    }

    private static Hallway generateHorizontal(Random ran, Room r1, Room r2){
        boolean isIncreasing = r1.maxX < r2.minX;

        int minX = isIncreasing ? r1.maxX + 1 : r2.maxX + 1;
        int maxX = isIncreasing ? r2.minX - 1 : r1.minX - 1;

        int minYBound = Math.max(r1.minY, r2.minY);
        int maxYBound = Math.min(r1.maxY, r2.maxY);
        int y = ran.nextInt(minYBound, maxYBound + 1);

        return new Hallway(minX, y, maxX, y);
    }
    private static Hallway generateVertical(Random ran, Room r1, Room r2){
        boolean isIncreasing = r1.maxY < r2.minY;

        int minY = isIncreasing ? r1.maxY + 1 : r2.maxY + 1;
        int maxY = isIncreasing ? r2.minY - 1 : r1.minY - 1;

        int minXBound = Math.max(r1.minX, r2.minX);
        int maxXBound = Math.min(r1.maxX, r2.maxX);
        int x = ran.nextInt(minXBound, maxXBound + 1);

        return new Hallway(x, minY, x, maxY);
    }
    private static Layout checkLayout(Room r1, Room r2){
        int disX = Math.min((Math.abs(r1.minX - r2.maxX)), Math.abs(r1.maxX - r2.minX));
        int disY = Math.min((Math.abs(r1.minY - r2.maxY)), Math.abs(r1.maxY - r2.minY));
        //相等就算横向连接
        return disX <= disY ? Layout.HORIZONTAL : Layout.VERTICAL;
    }
    enum Layout{
        HORIZONTAL, VERTICAL
    }
    @Override
    public void build(TETile[][] world) {

    }
}

package byog.Core;

import byog.TileEngine.TETile;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Hallway extends Generator{
    public final Position[] points;

    private Hallway(Position[] points){
        this.points = points.clone();
    }

    public static Hallway generateHallway(Random ran, Room r1, Room r2){
        //四种情况
        //左上，右下
        //左下，右上
        //左，右
        //上，下

        if(r1 == null || r2 == null) throw new RuntimeException("cannot be null");
        boolean xNotIntersected = (r1.minX > r2.maxX) || (r2.minX > r1.maxX);
        boolean yNotIntersected = (r1.minY > r2.maxY) || (r2.minY > r1.maxY);
        
        //上，下
        if(!xNotIntersected){
            return generateVertical(ran,r1,r2);
        } else if (!yNotIntersected) {
            return generateHorizontal(ran,r1,r2);
        } else {
            return generateCorner(ran, r1, r2);
        }
    }

    private static Hallway generateHorizontal(Random ran, Room r1, Room r2){
        boolean isIncreasing = r1.maxX < r2.minX;

        int minX = isIncreasing ? r1.maxX + 1 : r2.maxX + 1;
        int maxX = isIncreasing ? r2.minX - 1 : r1.minX - 1;

        int minYBound = Math.max(r1.minY, r2.minY);
        int maxYBound = Math.min(r1.maxY, r2.maxY);
        int y = ran.nextInt(minYBound, maxYBound + 1);

        var rst = new Position[]{
                new Position(minX,y),
                new Position(maxX,y)
        };
        return new Hallway(rst);
    }
    private static Hallway generateVertical(Random ran, Room r1, Room r2){
        boolean isIncreasing = r1.maxY < r2.minY;

        int minY = isIncreasing ? r1.maxY + 1 : r2.maxY + 1;
        int maxY = isIncreasing ? r2.minY - 1 : r1.minY - 1;

        int minXBound = Math.max(r1.minX, r2.minX);
        int maxXBound = Math.min(r1.maxX, r2.maxX);
        int x = ran.nextInt(minXBound, maxXBound + 1);

        var rst = new Position[]{
                new Position(x,minY),
                new Position(x,maxY)
        };
        return new Hallway(rst);
    }

    private static Hallway generateCorner(Random ran, Room r1, Room r2){
        boolean xIncreasing = r1.minX < r2.maxX;
        boolean yIncreasing = r1.minY < r2.maxY;

        if(xIncreasing && yIncreasing){
            return generateCornerUp(ran, r1, r2);
        } else if (!xIncreasing && !yIncreasing) {
            return generateCornerUp(ran, r2, r1);
        } else if (xIncreasing) {
            return generateCornerDown(ran, r1, r2);
        } else {
            return generateCornerDown(ran ,r2, r1);
        }
    }

    /**
     * - r2
     * r1 -
     * @param ran
     * @param r1
     * @param r2
     * @return
     */
    private static Hallway generateCornerUp(Random ran, Room r1, Room r2){
        //r1上
        boolean r1Up = ran.nextBoolean();

        if(r1Up){
            int p1X = ran.nextInt(r1.minX + 1, r1.maxX + 1);
            int p1Y = r1.maxY + 1;
            int p2X = r2.minX - 1;
            int p2Y = ran.nextInt(r2.minY + 1, r2.maxY + 1);
            var rst = new Position[]{
                    new Position(p1X, p1Y),
                    new Position(p1X, p2Y),
                    new Position(p2X, p2Y)
            };
            return new Hallway(rst);
        }

        int p1X = r1.maxX + 1;
        int p1Y = ran.nextInt(r1.minY + 1, r1.maxY + 1);
        int p2X = ran.nextInt(r2.minX + 1, r2.maxX + 1);
        int p2Y = r1.minY - 1;
        var rst = new Position[]{
                new Position(p1X, p1Y),
                new Position(p2X, p1Y),
                new Position(p2X, p2Y)
        };
        return new Hallway(rst);
    }

    /**
     * r1 -
     * - r2
     * @param ran
     * @param r1
     * @param r2
     * @return
     */
    private static Hallway generateCornerDown(Random ran, Room r1, Room r2){
        //r1上
        boolean r1Down = ran.nextBoolean();

        if(r1Down){
            int p1X = ran.nextInt(r1.minX + 1, r1.maxX + 1);
            int p1Y = r1.minY - 1;
            int p2X = r2.minX - 1;
            int p2Y = ran.nextInt(r2.minY + 1, r2.maxY + 1);
            var rst = new Position[]{
                    new Position(p1X, p1Y),
                    new Position(p1X, p2Y),
                    new Position(p2X, p2Y)
            };
            return new Hallway(rst);
        }

        int p1X = r1.maxX + 1;
        int p1Y = ran.nextInt(r1.minY + 1, r1.maxY + 1);
        int p2X = ran.nextInt(r2.minX + 1, r2.maxX + 1);
        int p2Y = r1.maxY + 1;
        var rst = new Position[]{
                new Position(p1X, p1Y),
                new Position(p2X, p1Y),
                new Position(p2X, p2Y)
        };
        return new Hallway(rst);
    }

    @Override
    public void build(TETile[][] world) {
        if(points.length == 3) {
            buildCorner(world);
        } else if(points.length == 2){
            if (points[0].x == points[1].x) {
                buildVertical(world, points);
            }else {
                buildHorizontal(world, points);
            }
        } else {
            throw  new RuntimeException("wrong type");
        }
    }

    private void buildHorizontal(TETile[][] world, Position[] points){
        if(points.length != 2) throw new RuntimeException("wrong type");
        int y = points[0].y;
        int x = Math.min(points[0].x, points[1].x);
        int d = Math.abs(points[0].x - points[1].x);
        for(int i = 0; i <= d; i++){
            world[x + i][y] = floor;

            if(world[x + i][y-1] != floor) world[x + i][y-1] = wall;
            if(world[x + i][y+1] != floor) world[x + i][y+1] = wall;
        }
    }
    private void buildVertical(TETile[][] world, Position[] points) {
        if(points.length != 2) throw new RuntimeException("wrong type");
        int x = points[0].x;
        int y = Math.min(points[0].y, points[1].y);
        int d = Math.abs(points[0].y - points[1].y);
        for(int i = 0; i <= d; i++){
            world[x][y + i] = floor;

            if(world[x - 1][y + i] != floor) world[x - 1][y + i] = wall;
            if(world[x + 1][y + i] != floor) world[x + 1][y + i] = wall;
        }
    }

    private void buildCorner(TETile[][] world){
        if(points.length != 3) throw new RuntimeException("wrong type");
        if(points[0].x == points[1].x){
            buildVertical(world, Arrays.copyOfRange(points, 0, 2));
        }else {
            buildHorizontal(world, Arrays.copyOfRange(points, 0, 2));
        }
        System.out.println(TETile.toString(world));
        if(points[1].x == points[2].x){
            buildVertical(world, Arrays.copyOfRange(points, 1, 3));
        }else {
            buildHorizontal(world, Arrays.copyOfRange(points, 1, 3));
        }
        System.out.println(TETile.toString(world));

        //补丁，转角处的一块
        int px = points[1].x > points[0].x? points[1].x + 1 : points[0].x - 1;
        int py = points[1].y > points[0].y? points[1].y + 1 : points[1].y - 1;
        if(world[px][py] != floor) world[px][py] = wall;

    }
}

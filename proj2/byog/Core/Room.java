package byog.Core;
import byog.TileEngine.TETile;

import java.util.List;
import java.util.Random;

public class Room extends Generator{
    private final Position position;

    // |width - position - width| 用法0 0，一开始的时候就写错了
    private final int width;
    private final int height;

    public static final int MAXWIDTH = 4;
    public static final int MAXHEIGHT = 4;

    public Room(Position position, int width, int height){
        this.position = position;
        this.width = width;
        this.height = height;
    }
    public Room(int x, int y, int width, int height){
        this(new Position(x,y), width, height);
    }

    public static Room generateRoom(Random random, int boundX, int boundY){
        while(true){
            int x = random.nextInt(0,boundX - MAXWIDTH);
            int y = random.nextInt(0, boundY - MAXHEIGHT);
            int width = random.nextInt(1,MAXWIDTH + 1);
            int height = random.nextInt(1, MAXHEIGHT + 1);
            Room room = new Room(x,y,width,height);
            if(isInBound(room,boundX, boundY)) return room;
        }
    }

    private static boolean isInBound(Room r, int boundX, int boundY){
        if(r.position.x - (r.width + 1) < 0) return false;
        if(r.position.x + r.width + 1 > boundX) return false;
        if(r.position.y - (r.height + 1) < 0) return false;
        if(r.position.y + r.height + 1 > boundY) return false;
        return true;
    }

    public boolean canBePlaced(List<Room> rooms){
        for(Room r : rooms){
            if(isOverlapped(r)){
                return false;
            }
        }
        return true;
    }
    public boolean isOverlapped(Room that) {
        boolean xSafe = notIntersected(this.position.x, that.position.x, this.width, that.width);
        boolean ySafe = notIntersected(this.position.y, that.position.y, this.height, that.height);
        return  !(xSafe && ySafe);
    }

    private boolean notIntersected(int r1p, int r2p, int r1d, int r2d){
        //this(0) -(1) -(2) -(3) that(4)
        int voidGrids = Math.abs(r1p - r2p) - 1;
        //可以共享一堵墙，所以+1
        return voidGrids >= (r1d + r2d + 1);
    }

    @Override
    public void build(TETile[][] world) {
        buildFloor(world);
        buildWall(world);
    }
    private void buildFloor(TETile[][] world){
        int minX = this.position.x - width;
        int minY = this.position.y - height;
        int maxX = this.position.x + width;
        int maxY = this.position.y + height;
        for(int px = minX; px <= maxX; px++){
            for(int py = minY; py <= maxY; py++){
                world[px][py] = floor;
            }
        }
    }
    private void buildWall(TETile[][] world){
        int minX = this.position.x - width - 1;
        int minY = this.position.y - height - 1;
        int maxX = this.position.x + width + 1;
        int maxY = this.position.y + height + 1;
        if(minX < 0){
            var b  = 1;
        }
        if(minY < 0){
            var b  = 1;
        }
        //horizontal
        for(int px = minX; px <= maxX; px++){
            world[px][minY] = wall;
            world[px][maxY] = wall;
        }
        //vertical
        for(int py = minY + 1; py < maxY; py++){
            world[minX][py] = wall;
            world[maxX][py] = wall;
        }
    }
}

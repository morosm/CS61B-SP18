package byog.Core;
import byog.TileEngine.TETile;

import java.util.List;
import java.util.Random;

public class Room extends Generator{
    public final int minX;
    public final int minY;
    public final int maxX;
    public final int maxY;

    private static final int MAXWIDTH = 10;
    private static final int MAXHEIGHT = 10;
    public Room(int minX, int minY, int maxX, int maxY){
        if(minX >= maxX) throw new IllegalArgumentException("wrong x");
        if(minY >= maxY) throw new IllegalArgumentException("wrong y");

        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public Position getCenter(){
        int x = minX + (maxX - minX)/2;
        int y = minY + (maxY - minY)/2;
        return new Position(x,y);
    }

    /**
     * 方法待优化，上方和右侧有空白区域，不能完全填充
     * @param random
     * @param boundX
     * @param boundY
     * @return
     */
    public static Room generateRoom(Random random, int boundX, int boundY){
        //1是为了考虑墙的厚度
        int minX = random.nextInt(1,boundX - 1 - MAXWIDTH);
        int minY = random.nextInt(1, boundY - 1 - MAXHEIGHT);
        //0, 如果生成(1,1,1,1)那么这个矩形就是room就是一个点
        //1，保证房间宽度最少为2
        int maxX = minX + random.nextInt(1, MAXWIDTH);
        int maxY = minY + random.nextInt(1, MAXWIDTH);
        return new Room(minX,minY,maxX,maxY);
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
        int[] rec1 = new int[]{this.minX, this.minY, this.maxX, this.maxY};
        int[] rec2 = new int[]{that.minX, that.minY, that.maxX, that.maxY};
        return isRectangleOverlap(rec1, rec2);
    }

    private boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        boolean notOverLap = checkLinear(rec1[0], rec1[2], rec2[0], rec2[2]) || checkLinear(rec1[1], rec1[3], rec2[1], rec2[3]);
        return !notOverLap;
    }
    private boolean checkLinear(int x1, int x2, int x3, int x4){
        boolean checkRec = (x1 < x2) && (x3 < x4);
        //x3>x2,最接近时两个点相邻
        //+1，预留1墙厚
        boolean notOverLap = (x3 > x2 + 1) || (x1 > x4 + 1);
        return checkRec && notOverLap;
    }
    @Override
    public void build(TETile[][] world) {
        buildFloor(world);
        buildWall(world);
    }
    private void buildFloor(TETile[][] world){
        for(int px = minX; px <= maxX; px++){
            for(int py = minY; py <= maxY; py++){
                world[px][py] = floor;
            }
        }
    }
    private void buildWall(TETile[][] world){
        //horizontal
        for(int px = minX - 1; px <= maxX + 1; px++){
            world[px][minY - 1] = wall;
            world[px][maxY + 1] = wall;
        }
        //vertical
        for(int py = minY; py <= maxY; py++){
            world[minX - 1][py] = wall;
            world[maxX + 1][py] = wall;
        }
    }

    public int calDis(Room that){
        return getCenter().calDis(that.getCenter());
    }
}

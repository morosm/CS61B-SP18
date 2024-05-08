package byog.Test;

import byog.Core.Hallway;
import byog.Core.Room;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class TestHallway {
    static Random random = new Random(111);
    public static void main(String[] args){
        int width = 40;
        int height = 40;
        TETile[][] finalWorldFrame = initializeFrame(width,height);

        testTypical(finalWorldFrame);

        TERenderer ter = new TERenderer();
        ter.initialize(width, height);
        ter.renderFrame(finalWorldFrame);

    }

    public static void testTypical(TETile[][] world){
        Room r1 = new Room(5,5,8,8);
        //Room r2 = new Room(15,5,22,8);
        Room r3 = new Room(15,25,22,33);
        //Room r4 = new Room(5, 23, 8,33);

        r1.build(world);
        //r2.build(world);
        r3.build(world);
        //r4.build(world);

        //Hallway hw = Hallway.generateHallway(random, r2, r4);
        //hw.build(world);
        Hallway hw2 = Hallway.generateHallway(random, r1, r3);
        hw2.build(world);
    }

    private static TETile[][] initializeFrame(int w, int h){
        TETile[][] frame = new TETile[w][h];
        for(int x = 0; x < w; x ++){
            for(int y = 0; y < h; y ++){
                frame[x][y] = Tileset.NOTHING;
            }
        }
        return frame;
    }
}

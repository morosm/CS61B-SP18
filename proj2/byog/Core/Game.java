package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 40;
    public static final int HEIGHT = 40;
    public static final Random RANDOM = new Random(11121);
    private static  final int MAXFAILURE = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        int count = 5;
        TETile[][] finalWorldFrame = initializeFrame(WIDTH,HEIGHT);

        List<Room> rooms = generateRooms(count);
        for(Room r : rooms){
            r.build(finalWorldFrame);
        }

        var xList = rooms.stream().sorted(Comparator.comparingInt(o -> o.getCenter().x)).toList();
        var yList = rooms.stream().sorted(Comparator.comparingInt(o -> o.getCenter().y)).toList();
        for (Room room : rooms) {
            boolean chooseX = RANDOM.nextBoolean();
            Room r2 = null;

            if (chooseX) {
                int index = xList.indexOf(room);
                index = index + 1 < xList.size() ? index + 1 : index - 1;
                r2 = xList.get(index);
            } else {
                int index = yList.indexOf(room);
                index = index + 1 < yList.size() ? index + 1 : index - 1;
                r2 = yList.get(index);
            }

            var hallway = Hallway.generateHallway(RANDOM, room, r2);
            hallway.build(finalWorldFrame);
        }

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        ter.renderFrame(finalWorldFrame);
        return finalWorldFrame;
    }
    private TETile[][] initializeFrame(int w, int h){
        TETile[][] frame = new TETile[w][h];
        for(int x = 0; x < w; x ++){
            for(int y = 0; y < h; y ++){
                frame[x][y] = Tileset.NOTHING;
            }
        }
        return frame;
    }

    private List<Room> generateRooms(int count){
        List<Room> rooms = new ArrayList<Room>();
        for (int i = 0; i < count; i ++){
            boolean isGenerated = false;
            int failure = 0;
            while(!isGenerated){
                Room r = Room.generateRoom(RANDOM, WIDTH, HEIGHT);
                if(r.canBePlaced(rooms)){
                    rooms.add(r);
                    isGenerated = true;
                }
                if(++failure > MAXFAILURE){
                    break;
                }
            }
        }
        return rooms;
    }
}

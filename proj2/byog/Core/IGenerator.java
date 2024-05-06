package byog.Core;


import byog.TileEngine.*;

/**
 * for room and hallway
 */
public interface IGenerator {
    public void generate(TETile[][] world, Position p, int sx, int sy);
}

package byog.Core;

import java.util.Dictionary;
import java.util.HashMap;

public class Room {
    private final Position position;
    private final int width;
    private final int height;
    private final HashMap<Direction, Room> connections;

    public Room(Position position, int width, int height){
        this.position = position;
        this.width = width;
        this.height = height;

        connections = new HashMap<Direction, Room>();
    }

    public boolean isConnected(){
        return !connections.isEmpty();
    }
    public boolean setUp(Room that){
        if(connections.containsKey(Direction.Up)) return false;
        if(that.connections.containsKey(Direction.Down)) return false;

        this.connections.put(Direction.Up, that);
        that.connections.put(Direction.Down, this);
        return true;
    }
    public boolean setDown(Room that){
        if(connections.containsKey(Direction.Down)) return false;
        if(that.connections.containsKey(Direction.Up)) return false;

        this.connections.put(Direction.Down, that);
        that.connections.put(Direction.Up, this);
        return true;
    }
    public boolean setLeft(Room that){
        if(connections.containsKey(Direction.Left)) return false;
        if(that.connections.containsKey(Direction.Right)) return false;

        this.connections.put(Direction.Left, that);
        that.connections.put(Direction.Right, this);
        return true;
    }
    public boolean setRight(Room that){
        if(connections.containsKey(Direction.Right)) return false;
        if(that.connections.containsKey(Direction.Left)) return false;

        this.connections.put(Direction.Right, that);
        that.connections.put(Direction.Left, this);
        return true;
    }

}

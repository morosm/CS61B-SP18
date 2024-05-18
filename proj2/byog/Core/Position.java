package byog.Core;

public class Position {
    public int x;
    public int y;
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int calDis(Position that){
        int xd = Math.abs(this.x - that.x);
        int yd = Math.abs(this.y - that.y);
        return (int)Math.sqrt(xd * xd + yd * yd);
    }
}

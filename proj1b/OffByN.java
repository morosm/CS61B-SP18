public class OffByN implements CharacterComparator{
    private int n;
    private OffByN(){}
    public OffByN(int n){
        this.n = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        if(Math.abs(diff) == n){
            return true;
        }
        return false;
    }
}

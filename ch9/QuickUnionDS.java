public class QuickUnionDS implements DisjointSets{
    private int[] array;
    public QuickUnionDS(int N){
        array = new int[N];
        for(int i = 0; i < N; i++){
            array[i] = -1;
        }
    }

    @Override
    public void connect(int p, int q) {
        int i = find(p);
        int j = find(q);
        array[i] = j;
    }

    @Override
    public boolean isConnected(int p, int q) {
        int i = find(p);
        int j = find(q);
        return i == j;
    }
    
    private int find(int p){
        int r = p;
        while (array[r] >= 0) {
            //不会取到-1的值
            r = array[r];
        }
        return r;
    }
}

public class UnionFind  implements IUnionFind{
    private int[] parent, size;

    /**
     * Creates a UnionFind data structure holding n vertices. Initially, all vertices are in disjoint sets.
     * @param v1
     * @return
     */
    public UnionFind(int n){
        parent = new int[n];
        size = new int[n];
        
        for(int i = 0; i < n; i ++){
            parent[i] = i;
        }
    }

    @Override
    public void validate(int v1) {
        if(v1 < 0) throw new IllegalArgumentException("must be positive");
        if(v1 >= parent.length) throw new IllegalArgumentException("out of index");
    }

    @Override
    public int sizeOf(int v1) {
        return size[v1];
    }

    @Override
    public int parent(int v1) {
        return parent[v1];
    }

    @Override
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);

        return find(v1) == find(v2);
    }

    @Override
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);

        int r1 = find(v1);
        int r2 = find(v2);

        if(sizeOf(r1) <= sizeOf(r2)){
            parent[r1] = r2;
            size[r2] += size[r1];
            size[r1] = 0;
        }else{
            parent[r2] = r1;
            size[r1] += size[r2];
            size[r2] = 0;
        }
    }

    @Override
    public int find(int v1) {
        if(parent[v1] != v1) return find(v1);
        return v1;
    }
}
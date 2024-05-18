public interface IUnionFind {
    /**
     * Throws an exception if v1 is not a valid index.
     * @param v1
     */
    public void validate(int v1);
    
    /**
     * Returns the size of the set v1 belongs to.
     * @param v1
     * @return
     */
    public int sizeOf(int v1);

    /**
     * Returns the parent of v1. If v1 is the root of a tree, returns the negative size of the tree for which v1 is the root.
     * @param v1
     * @return
     */
    public int parent(int v1);

    /**
     * Returns true if nodes v1 and v2 are connected.
     * @param v1
     * @param v2
     * @return
     */
    public boolean connected(int v1, int v2);
    
    /**
     * Connects two elements v1 and v2 together. v1 and v2 can be any valid elements, and a union-by-size heuristic is used. If the sizes of the sets are equal, tie break by connecting v1’s root to v2’s root. Unioning a vertex with itself or vertices that are already connected should not change the sets, but it may alter the internal structure of the data structure.
     * @param v1
     * @param v2
     */
    public void union(int v1, int v2);
    
    /**
     * Returns the root of the set v1 belongs to. Path-compression is employed allowing for fast search-time.
     * @param v1
     * @return
     */
    public int find(int v1);
}

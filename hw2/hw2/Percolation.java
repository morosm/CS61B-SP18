package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int N;
    private final int topIndex;
    private final WeightedQuickUnionUF disjointSet;
    /* default = 0, open = 1, connected = 2*/
    private final boolean[][] grids;
    private int openCount;

    /**
     * create N-by-N grid, with all sites initially blocked
     * @param N
     */
    public Percolation(int N){
        openCount = 0;
        this.N = N;
        int count = N * N + 1;
        topIndex = count - 1;
        disjointSet = new WeightedQuickUnionUF(count);

        grids = new boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                grids[i][j] = false;
                if(i == 0){
                    int loc = tryConvertLocation(i, j);
                    //connect top
                    disjointSet.union(loc, topIndex);
                }
            }
        }
    }

    /**
     * open the site (row, col) if it is not open already
     * @param row
     * @param col
     */
    public void open(int row, int col){
        if(!validateLocation(row, col))
            throw new IndexOutOfBoundsException();
        if(grids[row][col])
            return;
        grids[row][col] = true;
        openCount += 1;
        unionNeighbors(row, col);
    }

    private void unionNeighbors(int row, int col){
        int loc = tryConvertLocation(row, col);
        unionNeighbor(loc, row, col - 1);
        unionNeighbor(loc, row, col + 1);
        unionNeighbor(loc, row - 1, col);
        unionNeighbor(loc, row + 1, col);
    }

    private void unionNeighbor(int thisLoc, int thatRow, int thatCol){
        if(!validateLocation(thatRow, thatCol))
            return;
        if(!grids[thatRow][thatCol])
            return;
        int thatLoc = tryConvertLocation(thatRow, thatCol);
        disjointSet.union(thisLoc, thatLoc);
    }


    public boolean isOpen(int row, int col){
        if(!validateLocation(row, col))
            throw new IndexOutOfBoundsException();
        int loc = tryConvertLocation(row, col);
        return grids[row][col];
    }

    public boolean isFull(int row, int col){
        if(!validateLocation(row, col))
            throw new IndexOutOfBoundsException();
        int loc = tryConvertLocation(row, col);
        if(!grids[row][col])
            return false;
        if(!disjointSet.connected(loc, topIndex))
            return false;
        return true;
    }

    public int numberOfOpenSites(){
        return openCount;
    }

    public boolean percolates(){
        for (int j = 0; j < N; j ++) {
            if(isFull(N - 1, j))
                return  true;
        }
        return  false;
    }

    private boolean validateLocation(int row, int col){
        if(row < 0 || row > N - 1)
            return false;
        if(col < 0 || col > N - 1)
            return false;
        return true;
    }

    private int tryConvertLocation(int row, int col){
        int loc = row * N + col;
        return loc;
    }

    public static void main(String[] args){

    }
}

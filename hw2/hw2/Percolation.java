package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    final int N;
    final WeightedQuickUnionUF disjointSet;
    /* default = 0, open = 1, connected = 2*/
    final State[][] grids;
    int openCount;

    /**
     * create N-by-N grid, with all sites initially blocked
     * @param N
     */
    public Percolation(int N){
        openCount = 0;
        this.N = N;
        int count = N * N + 1;
        disjointSet = new WeightedQuickUnionUF(count);

        grids = new State[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                grids[i][j] = State.NotSet;
                if(i == 0){
                    int loc = tryConvertLocation(i, j);
                    //connect top
                    disjointSet.union(loc, 0);
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
        grids[row][col] = State.Open;
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
        if(grids[thatRow][thatCol] != State.Open)
            return;
        int thatLoc = tryConvertLocation(thatRow, thatCol);
        disjointSet.union(thisLoc, thatLoc);
    }


    public boolean isOpen(int row, int col){
        if(!validateLocation(row, col))
            throw new IndexOutOfBoundsException();
        int loc = tryConvertLocation(row, col);
        return grids[row][col] == State.Open;
    }

    public boolean isFull(int row, int col){
        if(!validateLocation(row, col))
            throw new IndexOutOfBoundsException();
        int loc = tryConvertLocation(row, col);
        if(!(grids[row][col] == State.Open))
            return false;
        if(!disjointSet.connected(loc, 0))
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
        return loc + 1;
    }

    public static void main(String[] args){

    }

    private enum State{
        NotSet,
        Open,
    }
}

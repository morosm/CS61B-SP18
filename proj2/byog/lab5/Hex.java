package byog.lab5;

public class Hex {
    private int seed;
    private int column;
    private int row;

    private Hex(){}
    public Hex(int seed){
        this.seed = seed;
        row = seed * 2;
        column = seed % 2 == 0 ? seed * 2 : seed * 2 + 1;
    }

    private int calColumnNum(int row){
        int rst = 0;
        row += 1;

        if(row <= seed){
            rst = column - (seed - row) * 2;
            return rst;
        }else{
            rst = column - ( row - seed - 1) * 2; //因为是对称的，但是基准还是以上半部分的最后一行。
            return rst;
        }
    }

    private int[] drawColumn(int row){
        int[] rst = new int[column];
        int num = calColumnNum(row);
        int startIndex = (column - num)/2;

        for(int i = 0; i < num; i ++){
            rst[startIndex + i] = 1;
        }

        return rst;
    }
    public void Draw(){
        var rst = new int[row][column];
        for(int r = 0; r < row; r ++){
            rst[r] = drawColumn(r);
        }

        for(int i = 0; i < row; i ++){
            for(int j = 0; j < column; j ++){
                String c = rst[i][j] == 0 ? " ":"*";
                System.out.print(c);
            }
            System.out.println();

        }
    }

    public static void main(String[] args){
        var hex = new Hex(4);
        hex.Draw();
    }
}

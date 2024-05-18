public class QuickFindDS implements DisjointSets{
    public QuickFindDS(int n){
        array = new int[n];
    }

    private int[] array;

    @Override
    public void connect(int p, int q) {
        int pid = array[p];
        int qid = array[q];
        for(int i = 0; i < array.length; i++){
            if(array[i] == qid){
                array[i] = pid;
            }
        }

    }

    @Override
    public boolean isConnected(int p, int q) {
        return array[p] == array[q];
    }
    
}
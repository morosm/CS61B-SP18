/**
 * hide the terrible truth of the nakedness within
 * https://joshhug.gitbooks.io/hug61b/content/chap2/chap22.html
 */
public class SLList {
    /*the first item is sentinal.next */
    private IntNode sentinel;
    private int size;

    public SLList(){
        sentinel = new IntNode(-1, null);
        size = 0;
    }

    public SLList(int x){
        sentinel = new IntNode(-1, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    public void addFirst(int x){
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

    public int getFirst(){
        return sentinel.next.item;
    }

    public void addLast(int x){
        IntNode p = sentinel;

        while (p.next != null) {
            p = p.next;
        }

        p.next = new IntNode(x, null);
        size += 1;
    }

    public int size(){
        return size;
    }

    public static void main(String[] args){
        SLList L = new SLList();
        int a = L.getFirst();
    }

    /**
     * If the nested class has no need to use any of the instance methods or variables of SLList, you may declare the nested class static, as follows. Declaring a nested class as static means that methods inside the static class can not access any of the members of the enclosing class. In this case, it means that no method in IntNode would be able to access first, addFirst, or getFirst.
     * 太怪了! C#中static class是不可以new的
     */
    public static class IntNode {
        public int item;
        public IntNode next;
    
        public IntNode(int i, IntNode n){
            item = i;
            next = n;
        }    
    }

}

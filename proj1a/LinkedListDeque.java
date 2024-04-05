public class LinkedListDeque<T> implements Deque<T>{
    protected class Node {
        public T value;
        public Node next;
        public Node pre;

        public Node(){
            this(null);
        }

        public Node(T item){
            value =item;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.pre = sentinel;

        size = 0;
    }

    /**
     * no looping or recursion
     */
    public void addFirst(T item) {
        size += 1;
        var current = new Node(item);
        var next = sentinel.next;
        
        current.next = next;
        next.pre = current;

        current.pre = sentinel;
        sentinel.next = current;
    }

    /**
     * no looping or recursion
     */
    public void addLast(T item) {
        size += 1;
        var current = new Node(item);
        var pre = sentinel.pre;

        current.next = sentinel;
        sentinel.pre = current;

        current.pre = pre;
        pre.next = current;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *  must take constant time.
     */
    public int size() {
        return size;
    }

    public void printDeque() {
        if(size == 0){
            System.out.println("");
        }

        var p = sentinel;
        for(int i = 0; i < size; i ++){
            p = p.next;
            System.out.println(p.value);
        }
    }

    /**
     * no looping or recursion
     */
    public T removeFirst() {
        size -= 1;
        var removed = sentinel.next;

        var next = removed.next;
        sentinel.next = next;
        next.pre = sentinel;

        return removed.value;
    }

    /**
     * no looping or recursion
     */
    public T removeLast() {
        size -= 1;
        var removed = sentinel.pre;

        var pre = removed.pre;
        pre.next = sentinel;
        sentinel.pre = pre;

        return removed.value;
    }



    /**
     * must use iteration, not recursion.
     */
    public T get(int index) {
        var p = sentinel;

        for(int i = 0; i <= index; i++){
            p = p.next;
        }

        return p.value;
    }

    /**
     * Same as get, but uses recursion.
     * @param index
     * @return
     */
    public T getRecursive(int index){
        var p = sentinel;

        while (index >= 0) {
            p = p.next;
            index -= 1;
        }

        return p.value;
    }
    
}

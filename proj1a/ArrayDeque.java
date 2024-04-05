public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int arraySize;
    private int first;
    private int size;
    private static final int REFACTOR = 2;

    public ArrayDeque(){
        /*The starting size of your array should be 8. */
        arraySize = 8;
        items = (T []) new Object[arraySize];
        first = calFirst(0, 0, arraySize);
        size = 0;
    }

    /**
     * take constant time, except during resizing operations.
     */
    @Override
    public void addFirst(T item) {
        if(first == 0){
            resize();
        }
        
        first -= 1;
        size += 1;
        items[first] = item;
    }

    /**
     * take constant time, except during resizing operations.
     */
    @Override
    public void addLast(T item) {
        if(first + size == arraySize){
            resize();
        }

        size += 1;
        items[last()] = item;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * must take constant time.
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        T rst = (T)new Object();
        for(int i = 0; i < size; i ++){
            rst = items[first + i];
            System.out.println(rst.toString());
        }
    }

    /**
     * take constant time, except during resizing operations.
     */
    @Override
    public T removeFirst() {
        var rst = items[first];
        first += 1;
        size -= 1;
        return rst;
    }

    /**
     * take constant time, except during resizing operations.
     */
    @Override
    public T removeLast() {
        var rst = items[last()];
        size -= 1;
        return rst;
    }

    /**
     * must take constant time.
     */
    @Override
    public T get(int index) {
        var rst = items[first + index];
        return rst;
    }

    private void resize(){
        var newArraySize = size * REFACTOR;
        var newItems = (T []) new Object[newArraySize];

        var newF = calFirst(first, size, newArraySize);
        System.arraycopy(items, first, newItems, newF, size);

        arraySize = newArraySize;
        first = newF;
        items = newItems;
    }

    private int calFirst(int start, int count, int length){
        var newF = (length - count)/2;
        return newF;
    }

    private int last(){
        var rst = first + size - 1;
        return rst;
    }
}

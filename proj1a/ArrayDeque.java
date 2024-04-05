public class ArrayDeque<T>{
    private T[] items;
    private int arraySize;
    private int first;
    private int size;
    private static final int REFACTOR = 2;
    private static final double RATIO = 0.25;

    public ArrayDeque(){
        /*The starting size of your array should be 8. */
        arraySize = 8;
        items = (T []) new Object[arraySize];
        first = calFirst(0, 0, arraySize);
        size = 0;
    }

    public void addFirst(T item) {
        if(first == 0){
            resize(arraySize * REFACTOR);
        }
        
        first -= 1;
        size += 1;
        items[first] = item;
    }

    public void addLast(T item) {
        if(size == 0){
            addFirst(item);
            return;
        }
        if(first + size == arraySize){
            resize(arraySize * REFACTOR);
        }

        size += 1;
        items[last()] = item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        T rst = (T)new Object();
        for(int i = 0; i < size; i ++){
            rst = items[first + i];
            System.out.println(rst.toString());
        }
    }

    public T removeFirst() {
        if(size == 0){
            return null;
        }
        var rst = items[first];
        first += 1;
        size -= 1;
        if(size/(double)arraySize < RATIO){
            resize(arraySize/REFACTOR);
        }
        return rst;
    }

    public T removeLast() {
        if(size == 0){
            return null;
        }
        var rst = items[last()];
        size -= 1;
        if(size/(double)arraySize < RATIO){
            resize(arraySize/REFACTOR);
        }
        return rst;
    }

    public T get(int index) {
        var rst = items[first + index];
        return rst;
    }

    private void resize(int newArraySize){
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

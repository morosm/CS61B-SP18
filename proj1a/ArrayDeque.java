import java.util.NoSuchElementException;

public class ArrayDeque<T>{
    private Object[] items;
    private int head;
    private int tail;
    private static final int MIN_INITIAL_CAPACITY = 8;
    private static final double MAX_RATIO = 0.25;

    public ArrayDeque(){
        /*The starting size of your array should be 8. */
        items = new Object[MIN_INITIAL_CAPACITY];
        head = tail = 0;
    }

    //use the previous slot
    public void addFirst(T item) {
        if(item == null)
            throw new NullPointerException();
        head = (head - 1) & (items.length - 1);
        items[head] = item;
        if(head == tail)
            resize(calculateSize(items.length));
    }

    //this slot can be used
    public void addLast(T item) {
        if(item == null)
            throw new NullPointerException();
        items[tail] = item;
        //tail = (tail + 1)%items.length
        tail = (tail + 1) & (items.length - 1);
        if(tail == head)
            resize(calculateSize(items.length));
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        if(head == tail)
            return 0;
        else if(tail > head)
            return tail - head;
        else
            return items.length - head + tail;
    }

    public void printDeque() {
        if(size() == 0)
            return;
        int i = head;
        do {
            System.out.print(items[i].toString());
            i = (i + 1) & (items.length - 1);
        } while(i != tail);
    }

    public T removeFirst() {
        var rst = items[head];
        head = (head + 1) & (items.length - 1);

        if((double)size()/items.length <= MAX_RATIO){
            resize(calculateSize(size()));
        }
        return (T)rst;
    }

    public T removeLast() {
        var index = (tail - 1) & (items.length - 1);
        var rst = items[index];
        tail = index;

        if((double)size()/items.length <= MAX_RATIO){
            resize(calculateSize(size()));
        }
        return (T)rst;
    }

    public T get(int index) {
        if(index >= items.length || index < 0)
            throw new IndexOutOfBoundsException();

        var realIndex = (head + index) & (items.length - 1);
        return (T)items[realIndex];
    }

    private void resize(int num){
        var newItems = new Object[num];

        int i = 0;
        int j = head;
        do {
            newItems[i] = items[j];
            i = (i + 1) & (num - 1);
            j = (j + 1) & (items.length - 1);
        } while(j != tail);

        head = 0;
        tail = i;
        items = newItems;
    }

    private int calculateSize(int numElements){
        int initialCapacity = MIN_INITIAL_CAPACITY;

        //整个操作相当于*2,
        if(numElements >= initialCapacity){
            initialCapacity = numElements;
            initialCapacity |= (initialCapacity >>> 1);
            initialCapacity |= (initialCapacity >>> 2);
            initialCapacity |= (initialCapacity >>> 4);
            initialCapacity |= (initialCapacity >>> 8);
            initialCapacity |= (initialCapacity >>> 16);
            initialCapacity ++;
        }

        //最大容量只有2的30次方
        //0100 0000 0000 0000 ....
        if(initialCapacity < 0)
            initialCapacity >>>= 1;

        return initialCapacity;
    }
}

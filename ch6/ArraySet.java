public class ArraySet<T> {

    private T[] array;
    private int size;
    
    public ArraySet() {
        array = (T[])new Object[64];
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(T x) {
        for(int i = 0; i < size; i ++){
            if (array[i] == x) {
                return true;
            }
        }
        return false;
    }

    /* Associates the specified value with the specified key in this map. 
       Throws an IllegalArgumentException if the key is null. */
    public void add(T x) {
        if(x == null) throw new IllegalArgumentException("value is null");
        if(contains(x)) return;

        array[size] = x;
        size ++;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        ArraySet<String> s = new ArraySet<>();
        //s.add(null);
        s.add("horse");
        s.add("fish");
        // s.add("house");
        // s.add("fish");        
        System.out.println(s.contains("horse"));        
        System.out.println(s.size());       
    }

    /* Also to do:
    1. Make ArraySet implement the Iterable<T> interface.
    2. Implement a toString method.
    3. Implement an equals() method.
    */
}
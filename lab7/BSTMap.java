import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V> {
    Node node;
    int size;

    private class Node{
        private K key;
        private V value;
        private Node left;
        private Node right;

        private Node(){

        }

        private Node(K key, V value){
            init(key, value);
        }

        private void init(K key, V value){
            this.key = key;
            this.value = value;
            this.left = new Node();
            this.right = new Node();
        }
    }

    public BSTMap(){
        this.node = new Node();
        this.size = 0;
    }

    /**
     * Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        this.node = new Node(null, null);
        this.size = 0;
    }

    /**
     * @param key
     * @return
     */
    @Override
    public boolean containsKey(K key){
        Node n = get(this.node, key);
        return key.equals(n.key);
    }

    /**
     * @param key
     * @return
     */
    @Override
    public V get(K key) {
        Node n = get(this.node, key);
        return n.value;
    }

    private Node get(Node n, K key){
        if(n.key == null)
            return n;

        int c = key.compareTo(n.key);
        if(c < 0)
            return get(n.left, key);
        else if(c > 0)
            return get(n.right, key);
        else
            return n;
    }

    /**
     * @return
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        put(this.node, key, value);
    }

    private Node put(Node n, K key, V value){
        if(n.key == null){
            n.init(key, value);
            size ++;
            return n;
        }

        int c = key.compareTo(n.key);
        if(c < 0)
            return put(n.left, key, value);
        else if(c > 0)
            return put(n.right, key, value);
        else{
            n.value = value;
            return n;
        }
    }

    /**
     * @return
     */
    @Override
    public Set keySet() {
        throw new UnsupportedOperationException();
    }

    /**
     * @param key
     * @return
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * @param key
     * @param value
     * @return
     */
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }

    /**
     * Performs the given action for each element of the {@code Iterable}
     * until all elements have been processed or the action throws an
     * exception.  Actions are performed in the order of iteration, if that
     * order is specified.  Exceptions thrown by the action are relayed to the
     * caller.
     * <p>
     * The behavior of this method is unspecified if the action performs
     * side-effects that modify the underlying source of elements, unless an
     * overriding class has specified a concurrent modification policy.
     *
     * @param action The action to be performed for each element
     * @throws NullPointerException if the specified action is null
     * @implSpec <p>The default implementation behaves as if:
     * <pre>{@code
     *     for (T t : this)
     *         action.accept(t);
     * }</pre>
     * In your implementation you should assume that generic keys K in BSTMap<K,V> extend Comparable.
     * @since 1.8
     */
    @Override
    public void forEach(Consumer action) {
        Map61B.super.forEach(action);
    }

    /**
     * Creates a {@link Spliterator} over the elements described by this
     * {@code Iterable}.
     *
     * @return a {@code Spliterator} over the elements described by this
     * {@code Iterable}.
     * @implSpec The default implementation creates an
     * <em><a href="../util/Spliterator.html#binding">early-binding</a></em>
     * spliterator from the iterable's {@code Iterator}.  The spliterator
     * inherits the <em>fail-fast</em> properties of the iterable's iterator.
     * @implNote The default implementation should usually be overridden.  The
     * spliterator returned by the default implementation has poor splitting
     * capabilities, is unsized, and does not report any spliterator
     * characteristics. Implementing classes can nearly always provide a
     * better implementation.
     * @since 1.8
     */
    @Override
    public Spliterator spliterator() {
        return Map61B.super.spliterator();
    }

    public void printInOrder(){

    }
}

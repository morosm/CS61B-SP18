package synthesizer;

public class GuitarString {
    private static final int SR = 44100;
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        int capacity = (int)Math.round(SR /frequency);
        buffer = new ArrayRingBuffer<Double>(capacity);
        while (!buffer.isFull()) {
            buffer.enqueue(0d);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        //deque everything
        while(!buffer.isEmpty()) {
            buffer.dequeue();
        }

        //todo, how to make elements different
        double r = 0;
        while(!buffer.isFull()) {
            r = Math.random() - 0.5;
            buffer.enqueue(r);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() {
        double f = buffer.dequeue();
        double s = buffer.peek();
        double l = (f + s) / 2 * DECAY;
        buffer.enqueue(l);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        double r = buffer.peek();
        return r;
    }
}

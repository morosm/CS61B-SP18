package byog.lab5;

import java.util.Random;

public class Pseudorandom {
    public static void main(String[] args) {
        Random r = new Random(82731);
        System.out.println(r.nextInt());
        System.out.println(r.nextInt());
        System.out.println(r.nextInt());
        System.out.println(r.nextInt());
        r = new Random(82731);
        System.out.println(r.nextInt());
        System.out.println(r.nextInt());
        System.out.println(r.nextInt());
        System.out.println(r.nextInt());
    }
}

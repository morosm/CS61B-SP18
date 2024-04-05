public class TestTime {
    public static void main(String[] args){
        int time = 0;
        for(time = 0; time < 10; time += 1){
            System.out.println(time);
        }

        System.err.println("restart");
        time = 0;
        while (time < 10) {
            System.out.println(time);
            time += 1;
        }
    }
}

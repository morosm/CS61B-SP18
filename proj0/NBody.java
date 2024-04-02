import java.util.*; 
import java.util.stream.*; 

// https://sp18.datastructur.es/materials/proj/proj0/proj0#calcnetforceexertedbyx-and-calcnetforceexertedbyy

public class NBody{
    public static double readRadius(String path){
        In in = new In(path);

        while(!in.isEmpty()){
            int amount = in.readInt();
            double radius = in.readDouble();
            return radius;
        }

        return 0d;
    }

    public static Planet[] readPlanets(String path){
        In in = new In(path);

        while(in.isEmpty()) return new Planet[0];

        int amount = in.readInt();
        double radius = in.readDouble();
        Planet[] rst = new Planet[amount];

        for(int i = 0; i < amount - 1; i ++){
            String line = in.readLine();
            String[] elements = line.split(" ");
            if(elements.length != 6) return new Planet[0];
            
            String imgFileName = elements[5];
            elements[5] = "";
            double[] dElements = Arrays.stream(elements)
                .mapToDouble(e -> Double.parseDouble(e))
                .toArray();
            Planet p = new Planet(dElements[0], dElements[1], dElements[2], dElements[3], dElements[4], imgFileName);
            rst[i] = p;
        }

        return rst;
    }
}
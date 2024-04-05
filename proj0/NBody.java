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

        for(int i = 0; i < amount; i ++){
            Planet p = new Planet(
                in.readDouble(),
                in.readDouble(),
                in.readDouble(),
                in.readDouble(),
                in.readDouble(),
                in.readString());
            rst[i] = p;
        }

        return rst;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];

        double radius = readRadius(fileName);
        Planet[] planets = readPlanets(fileName);


        double width = 512;
        double height = 512;
        StdDraw.setCanvasSize(512,512);
        StdDraw.setScale(-radius, radius);
        StdDraw.enableDoubleBuffering();

        int planetSize = planets.length;
        double[] xForces = new double[planetSize];
        double[] yForces = new double[planetSize];
        for(double time = 0; time < T; time += dt){
            //calculate all forces and then update together
            for(int j = 0; j < planetSize; j ++){
                xForces[j] = planets[j].calcNetForceExertedByX(planets);
                yForces[j] = planets[j].calcNetForceExertedByY(planets);
            }
            for(int j = 0; j < planetSize; j ++) planets[j].update(dt, xForces[j], yForces[j]);

            StdDraw.picture(0, 0, "images/starfield.jpg");
            for(Planet p : planets) p.draw();
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }

    }
}
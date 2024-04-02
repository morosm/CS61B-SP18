import java.util.*; 
import java.util.stream.*; 

public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    private static final double Gravity = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p){
        this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
    }

    public double calcDistance(Planet other){
        double x = this.xxPos - other.xxPos;
        double y = this.yyPos - other.yyPos;
        double rst = Math.sqrt(x * x + y * y);
        return rst;
    }

    public double calcForceExertedBy(Planet other){
        double dis = this.calcDistance(other);
        double rst = (Gravity * this.mass * other.mass)/(dis * dis);
        return rst;
    }

    public double calcForceExertedByX(Planet other){
        double dt = other.xxPos - this.xxPos;
        double r = this.calcDistance(other);
        double f = this.calcForceExertedBy(other);
        double fx = f * dt / r;
        return fx;
    }

    public double calcForceExertedByY(Planet other){
        double dy = other.yyPos - this.yyPos;
        double r = this.calcDistance(other);
        double f = this.calcForceExertedBy(other);
        double fy = f * dy / r;
        return fy;
    }
    
    public double calcNetForceExertedByX(Planet[] others){
        //should ignore itself
        double rst = Arrays.stream(others)
            .filter(p -> !this.equals(p))
            .mapToDouble(p -> this.calcForceExertedByX(p))
            .sum();
        return rst;
    }

    public double calcNetForceExertedByY(Planet[] others){
        //should ignore itself
        double rst = Arrays.stream(others)
            .filter(p -> !this.equals(p))
            .mapToDouble(p -> this.calcForceExertedByY(p))
            .sum();
        return rst;
    }

    public void update(double dt, double fX, double fY){
        double ax = fX/this.mass;
        double vx = this.xxVel + dt * ax;
        double px = this.xxPos + dt * vx;
        this.xxVel = vx;
        this.xxPos = px;

        double ay = fY/this.mass;
        double vy = this.yyVel + dt * ay;
        double py = this.yyPos + dt * vy;
        this.yyVel = vy;
        this.yyPos = py;
    }
}